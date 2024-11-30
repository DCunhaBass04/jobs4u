#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <errno.h>
#include <sys/stat.h>
#include "../structs/configsettings.h"
#include "../structs/infocandidatura.h"

void constructFindNamesCommand(char *inputDirectory, int application, char *command){
	sprintf(command, "find %s -type f -name \"%d-*.txt\" -exec basename {} \\;", inputDirectory, application);
	//Example: find ../../Email-Bot-Output-Example -type f -name "2-*.txt" -exec basename {} \;
}

void constructFindReferenceCommand(char *inputDirectory, int application, char *command){
	sprintf(command, "find %s -type f -name \"%d-candidate-data.txt\" -exec head -n 1 {} \\;", inputDirectory, application);
	//Example: find ../../Email-Bot-Output-Example -type f -name "2-candidate-data.txt" -exec head -n 1 {} \;
}

void constructCopyCommand(char *inputDirectory, char *outputDirectory, int application, char *command){
	sprintf(command, "cp %s/%d-*.txt %s", inputDirectory, application, outputDirectory);
	//Example: cp ../../Email-Bot-Output-Example/2-*.txt ../../output
}

void fillInfoFiles(FILE *command_output, InfoCandidatura* info){
	char buffer[100];
	int file_count = 0;
	while (fgets(buffer, sizeof(buffer), command_output)) {
		buffer[strcspn(buffer, "\n")] = 0; //Remove \n
		strcpy(info->files[file_count], strdup(buffer));
		file_count++;
	}
	info->numFiles = file_count;
}

void copyfiles(ConfigSettings *conf, int reader[2], int writer[2]){
	close(writer[0]);
	close(reader[1]);
	int application;
	char reference[25], outputPath[150], command[160];
	FILE *command_output;
	while(1){
		if(read(reader[0], &application, sizeof(int)) == -1){
			perror("read");
			exit(EXIT_FAILURE); //If the reading went wrong, exit
		}
		//printf("Got application nº %d\n", application);
		if(application == -1)
			exit(1);
		constructFindReferenceCommand(conf->inputDirectory, application, command);
		command_output = popen(command, "r");
		if (command_output == NULL) {
			perror("popen");
			exit(EXIT_FAILURE);
		}
		while (fgets(reference, sizeof(reference), command_output)) {
            reference[strcspn(reference, "\n")] = 0; //Remove \n
		}
		sprintf(outputPath, "%s/%s", conf->outputDirectory, reference);
		if(mkdir(outputPath, 0755) && errno != EEXIST){
			perror("mkdir");
			exit(EXIT_FAILURE);
		}
		sprintf(outputPath, "%s/%s/%d", conf->outputDirectory, reference, application);
		if(mkdir(outputPath, 0755) && errno != EEXIST){
			perror("mkdir");
			exit(EXIT_FAILURE);
		}
		constructCopyCommand(conf->inputDirectory, outputPath, application, command);
		command_output = popen(command, "r");
		if (command_output == NULL) {
			perror("popen");
			exit(EXIT_FAILURE);
		}
		constructFindNamesCommand(conf->inputDirectory, application, command);
		command_output = popen(command, "r");
		if (command_output == NULL) {
			perror("popen");
			exit(EXIT_FAILURE);
		}
		InfoCandidatura info;
		info.applicationNumber = application;
		strcpy(info.outputCopyPath, outputPath);
		fillInfoFiles(command_output, &info);
		if(write(writer[1], &info, sizeof(InfoCandidatura)) == -1) //Inform parent that work is done, ready to do more
			exit(EXIT_FAILURE); //If the writing went wrong, exit	
	}
}

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>
#include <limits.h>
#include <signal.h>
#include <wait.h>
#include "../structs/configsettings.h"
#include "../structs/infocandidatura.h"
#include "../ConfigFileReader/ConfigFileReader.h"
#include "../InputChecker/InputChecker.h"
#include "../ChildWork/ChildWork.h"

ConfigSettings conf;
pid_t parent;
pid_t *children;
int parentToChild[2], childToParent[2];

void handle_sigint(int sig){
	if(getpid() == parent){
		char msg[100];
		sprintf(msg, "\nI got signal nº: %d (SIGINT)\n", sig);
		write(STDOUT_FILENO, msg, strlen(msg));
		for(int i = 0; i < conf.nChildren; i++){
			kill(children[i], SIGKILL);
			wait(NULL);
		}
		printf("All child processes terminated\n");
		free(children);
		printf("\nExiting!\n");
		exit(sig);
	}
}

void write_report(InfoCandidatura *info, int size, char *outputPath){
	FILE *fp;
	char path[160];
	sprintf(path, "%s/report.txt", outputPath);
	fp = fopen(path, "w");
	for(int i = 0; i < size; i++){
		//printf("Application number %d:\n--------------------------\nOutput Directory: %s\nFiles Copied:\n", info[i].applicationNumber, info[i].outputCopyPath);
		fprintf(fp, "Application number %d:\n--------------------------\nOutput Directory: %s\nFiles Copied:\n", info[i].applicationNumber, info[i].outputCopyPath);
		for(int j = 0; j < info[i].numFiles; j++){
			//printf("%s\n", info[i].files[j]);
			fprintf(fp, "%s\n", info[i].files[j]);
		}
		//printf("\n\n");
		fprintf(fp, "\n\n");
	}
	fclose(fp);
}

void handle_sigusr1(int sig){
	//printf("Entered!\n");
	FILE* command_output;
	char command[150];
	sprintf(command, "ls %s | cut -d'-' -f1 | sort -u", conf.inputDirectory);
	if ((command_output = popen(command, "r")) == NULL) {
		perror("popen");
		exit(EXIT_FAILURE);
	}
	int nrChild = 0, application, startReading = 0, numResults = 0, numApplications = 0;
	char buffer[10];
		
	InfoCandidatura candidaturas[15];
		
	while (fgets(buffer, sizeof(buffer), command_output)){
		numApplications++;
		buffer[strcspn(buffer, "\n")] = 0; //Remove \n
		application = atoi(buffer);
		if(startReading == 1){
			if(read(childToParent[0], &candidaturas[numResults], sizeof(InfoCandidatura)) != -1){+
				numResults++;
			} else {
				perror("read");
				exit(EXIT_FAILURE);
			}
		}
		if(write(parentToChild[1], &application, sizeof(int)) == -1){
			perror("write");
			exit(EXIT_FAILURE);
		}
		nrChild++;
		if(nrChild == conf.nChildren){
			nrChild = 0;
			startReading = 1;
		}
	}
	while (numResults != numApplications) {
		if(read(childToParent[0], &candidaturas[numResults], sizeof(InfoCandidatura)) != -1)
			numResults++;
		else {
			perror("read");
			exit(EXIT_FAILURE);
		}
	}
	write_report(candidaturas, numResults, conf.outputDirectory);
}

int main(void)
{
	parent = getpid();
	
	FILE *fp;
	if((fp = fopen("config.properties", "r")) == NULL){
		perror("fopen");
		exit(EXIT_FAILURE);
	}
		
    readConfigFile(fp, &conf);
    fclose(fp);
    
	/*Format example:
    
    input=..\example\inputDirectoryExample
    output=..\example\outputDirectoryExample
    nchildren=7
    cooldown=10
    
    */

    pid_t pid = fork();
    if (pid == -1) {
        perror("fork");
        exit(EXIT_FAILURE);
    } else if (pid == 0) {
        checkInputDirectory(getppid(), &conf);
    } else { 
        struct sigaction act_usr1, act_int;
		memset(&act_usr1, 0, sizeof(struct sigaction));
		memset(&act_int, 0, sizeof(struct sigaction));
		act_usr1.sa_handler = handle_sigusr1;
		sigaction(SIGUSR1, &act_usr1, NULL);
		act_int.sa_handler = handle_sigint;
		sigaction(SIGINT, &act_int, NULL);
		
		children = (pid_t*)malloc(conf.nChildren * sizeof(pid_t));
		
		if(pipe(parentToChild) == 1 || pipe(childToParent) == 1)
			exit(EXIT_FAILURE);
		for(int i = 0; i < conf.nChildren; i++)
			if((children[i] = fork()) == 0)
				copyfiles(&conf, parentToChild, childToParent);
	
		close(parentToChild[0]);
		close(childToParent[1]);
		while(1)
			pause();
    }
    
    return 0;
}

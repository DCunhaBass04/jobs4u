#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/stat.h>
#include <signal.h>
#include <wait.h>
#include "../../../../main/2001/ConfigFileReader/ConfigFileReader.h"
#include "../../../../main/2001/ChildWork/ChildWork.h"
#include "../unity.h"

#define SIZE 4

ConfigSettings conf;
pid_t parent;
pid_t *children;
int parentToChild[2], childToParent[2];

void setUp(void){
	// Set stuff up here
}

void tearDown(void){
	// Clean stuff up here
}

void handle_sigint(int sig){
	if(getpid() == parent){
		char msg[100];
		sprintf(msg, "\nI got signal nº: %d (SIGINT)\n", sig);
		write(STDOUT_FILENO, msg, strlen(msg));
		for(int i = 0; i < conf.nChildren; i++){
			kill(children[i], SIGKILL);
			wait(NULL);
		}
		struct stat *buf;
		buf = malloc(sizeof(struct stat));
		printf("\n");
		TEST_ASSERT_EQUAL_INT(stat("output/IBM-000123/1", buf),0); //This means that Application nº 1 has been copied correctly
		TEST_ASSERT_EQUAL_INT(stat("output/IBM-000123/2", buf),0); //This means that Application nº 2 has been copied correctly
		TEST_ASSERT_EQUAL_INT(stat("output/ISEP-000557/5", buf),0); //This means that Application nº 5 has been copied correctly
		TEST_ASSERT_EQUAL_INT(stat("output/FEUP-112233/10", buf),0); //This means that Application nº 10 has been copied correctly
		free(buf);
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
		fprintf(fp, "Application number %d:\n--------------------------\nOutput Directory: %s\nFiles Copied:\n", info[i].applicationNumber, info[i].outputCopyPath);
		for(int j = 0; j < info[i].numFiles; j++){
			fprintf(fp, "%s\n", info[i].files[j]);
		}
		fprintf(fp, "\n\n");
	}
	fclose(fp);
}

void run_test(char* testName, char* configFileName, 
				char* inputDirectoryTest, char* outputDirectoryTest,
				int nChildrenTest, int cooldownTest){
					
	parent = getpid();
	
	FILE *fp;
	if((fp = fopen("config.properties", "r")) == NULL){
		perror("fopen");
		exit(EXIT_FAILURE);
	}
	
	printf("%s\n", testName);
		
    readConfigFile(fp, &conf);
    fclose(fp);

    pid_t pid = fork();
    if (pid == -1) {
        perror("fork");
        exit(EXIT_FAILURE);
    } 
    
	struct sigaction act_int;
	memset(&act_int, 0, sizeof(struct sigaction));
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
	
	sleep(conf.cooldown);
	
	int applications[SIZE] = {1, 2, 5, 10};
	int nrChild = 0, startReading = 0, numResults = 0;
		
	InfoCandidatura candidaturas[SIZE];
		
	for(int i = 0; i < SIZE; i++){
		if(startReading == 1){
			if(read(childToParent[0], &candidaturas[numResults], sizeof(InfoCandidatura)) != -1){
				numResults++;
			} else {
				perror("read");
				exit(EXIT_FAILURE);
			}
		}
		if(write(parentToChild[1], &(applications[i]), sizeof(int)) == -1){
			perror("write");
			exit(EXIT_FAILURE);
		}
		nrChild++;
		if(nrChild == conf.nChildren){
			nrChild = 0;
			startReading = 1;
		}
	}
	while (numResults != SIZE) {
		if(read(childToParent[0], &candidaturas[numResults], sizeof(InfoCandidatura)) != -1)
			numResults++;
		else {
			perror("read");
			exit(EXIT_FAILURE);
		}
	}
	
	write_report(candidaturas, numResults, conf.outputDirectory);
	sleep(conf.cooldown);
    kill(getpid(), SIGINT);
}

void test_normal_config_one(){
	run_test("Normal Config #1", "config.properties", "../../Email-Bot-Output-Example", "../../output", 5, 2);
}

int main()
{
	UNITY_BEGIN();
	RUN_TEST(test_normal_config_one);
	return UNITY_END();
}


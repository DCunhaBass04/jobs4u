#include <stdio.h>
#include <sys/types.h>
#include <limits.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include "../structs/configsettings.h"

#define MAX_FILES 512

void checkInputDirectory(pid_t parent_pid, ConfigSettings *conf)
{
    char *files[MAX_FILES];
    char buffer[100], command [100];
    strcpy(command, "ls ");
    strcat(command, conf->inputDirectory);
    int file_count = 0, newFile, exitloop;
    FILE* ls_output;
    while(1){
		ls_output = popen(command, "r");
		if (ls_output == NULL) {
			perror("popen");
			exit(EXIT_FAILURE);
		}
        exitloop = 0;
        while (fgets(buffer, sizeof(buffer), ls_output)) {
            buffer[strcspn(buffer, "\n")] = 0; //Remove \n
        
            newFile = 1;
            for (int i = 0; i < file_count; i++) {
                if (strcmp(files[i], buffer) == 0) {
                    newFile = 0;
                    break;
                }
            } //If the file is not new, newFile = 0
        
            if (newFile) {
                exitloop = 1;
                files[file_count] = strdup(buffer);
                file_count++;
            } //If the file is new, add it to array
        }
        if (exitloop)
            kill(parent_pid, SIGUSR1); //Signal used for this section
        //If there were any (even one) new files, signal parent process
        sleep(conf->cooldown);
    }
}

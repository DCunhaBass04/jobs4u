#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../structs/configsettings.h"

void readConfigFile(FILE *fp, ConfigSettings *conf) {
    char line[BUFFER_SIZE];
    char key[BUFFER_SIZE], value[BUFFER_SIZE];
    
    while (fgets(line, BUFFER_SIZE, fp) != NULL) {
        if (sscanf(line, "%[^=]=%[^\n]", key, value) == 2) 
        /*Checks if sscanf obtained two values from the line*/
        /*The first value is obtained from the beggining to the '=' symbol (which is ignored)*/
        /*The second value is obtained from the '=' symbol (which is ignored) to the '\n' symbol*/
        {
			char *newline = strchr(value, '\r'); //Remove any '\r' remains
            if (newline != NULL)
                *newline = '\0';
            switch (key[0]) {
                case 'i': //input
                    strcpy(conf->inputDirectory, value);
                    break;
                case 'o': //output
                    strcpy(conf->outputDirectory, value);
                    break;
                case 'n': //nchildren
                    conf->nChildren = atoi(value);
                    break;
                case 'c': //cooldown
                    conf->cooldown = atoi(value);
                    break;
            }
        }
    }
}

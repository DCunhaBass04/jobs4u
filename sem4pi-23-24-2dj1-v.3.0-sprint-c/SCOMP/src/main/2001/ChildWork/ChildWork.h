#ifndef CHILDWORK_H
#define CHILDWORK_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <errno.h>
#include "../structs/configsettings.h"
#include "../structs/infocandidatura.h"

void constructFindCommand(char *inputDirectory, int application, char *command);
void constructCopyCommand(char *inputDirectory, char *outputDirectory, int application, char *command);
void copyfiles(ConfigSettings *conf, int reader[2], int writer[2]);

#endif

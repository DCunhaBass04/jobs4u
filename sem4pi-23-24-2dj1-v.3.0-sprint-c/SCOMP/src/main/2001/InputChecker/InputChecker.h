#ifndef INPUTCHECKER_H
#define INPUTCHECKER_H

#include <stdio.h>
#include <sys/types.h>
#include <limits.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include "../structs/configsettings.h"

#define MAX_FILES 512

void checkInputDirectory(pid_t parent_pid, ConfigSettings *conf);

#endif

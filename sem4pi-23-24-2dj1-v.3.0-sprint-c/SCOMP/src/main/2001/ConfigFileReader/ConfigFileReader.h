#ifndef CONFIGFILEREADER_H
#define CONFIGFILEREADER_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../structs/configsettings.h"

void readConfigFile(FILE *fp, ConfigSettings *conf);

#endif

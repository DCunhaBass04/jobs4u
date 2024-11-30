#ifndef CONFIGSETTINGS_H
#define CONFIGSETTINGS_H

#define BUFFER_SIZE 100

typedef struct {
	char inputDirectory[BUFFER_SIZE];
	char outputDirectory[BUFFER_SIZE];
	int nChildren;
	int cooldown;
}ConfigSettings;

#endif

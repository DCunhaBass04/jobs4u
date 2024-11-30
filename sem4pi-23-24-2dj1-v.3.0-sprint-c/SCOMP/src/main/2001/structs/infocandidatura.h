#ifndef INFOCANDIDATURA_H
#define INFOCANDIDATURA_H

#define OUTPUT_SIZE 150

typedef struct {
	int applicationNumber;
	int numFiles;
	char outputCopyPath[OUTPUT_SIZE];
	char files[10][OUTPUT_SIZE];
}InfoCandidatura;

#endif

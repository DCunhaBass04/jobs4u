#pragma once

#include "depend.h"
#include "global.h"

#define NEW_CANDIDATES_MAX 512
#define SHM_SIZE sizeof(new_files_buffer)

typedef struct {
    char candidates[NEW_CANDIDATES_MAX][NAME_MAX];
    int read;
    int write;
} new_files_buffer;

void write_candidate(char* candidate_data, new_files_buffer* shared_buffer,
                     sem_t* mutex_sem, sem_t* produce_sem, sem_t* consume_sem);

void read_candidate(char* dest, new_files_buffer* shared_buffer, sem_t* mutex_sem,
                   sem_t* produce_sem, sem_t* consume_sem);

int extract_candidate_number(char* filename);
#include "../header/circular_buffer.h"

#include "../header/depend.h"
#include "../header/global.h"

void write_candidate(char* candidate_data, new_files_buffer* shared_buffer,
                     sem_t* mutex_sem, sem_t* produce_sem, sem_t* consume_sem) {
    sem_wait(produce_sem);

    sem_wait(mutex_sem);
    shared_buffer->write = (shared_buffer->write + 1) % NEW_CANDIDATES_MAX;
    sem_post(mutex_sem);

    strcpy(shared_buffer->candidates[shared_buffer->write], candidate_data);

    sem_post(consume_sem);
}

void read_candidate(char* dest, new_files_buffer* shared_buffer,
                    sem_t* mutex_sem, sem_t* produce_sem, sem_t* consume_sem) {
    sem_wait(consume_sem);

    sem_wait(mutex_sem);
    shared_buffer->read = (shared_buffer->read + 1) % NEW_CANDIDATES_MAX;
    sem_post(mutex_sem);

    strcpy(dest, shared_buffer->candidates[shared_buffer->read]);

    sem_post(produce_sem);
}

int extract_candidate_number(char* filename) {
    char* last_slash = strrchr(filename, '/');
    if (last_slash != NULL) {
        last_slash++;
    } else {
        last_slash = filename;
    }
    int candidate_num;
    if (sscanf(last_slash, "%d-", &candidate_num) == 1) {
        return candidate_num;
    } 
    return -1;
}
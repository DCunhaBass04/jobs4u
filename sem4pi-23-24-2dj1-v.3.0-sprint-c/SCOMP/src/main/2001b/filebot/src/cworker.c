#include "../header/circular_buffer.h"
#include "../header/depend.h"
#include "../header/global.h"

void move_candidate_data(int candidate_num, char* job_reference);
void get_candidate_job_reference(char* readed_candidate, char* dest);

int main() {
    while (true) {
        char readed_candidate[NAME_MAX];
        char readed_job_reference[NAME_MAX];

        sem_t* mutex_sem = sem_open(MUTEX_SEMAPHORE, 0);
        sem_t* consume_sem = sem_open(CONSUME_SEMAPHORE, 0);
        sem_t* produce_sem = sem_open(PRODUCE_SEMAPHORE, 0);

        int shared_fd = shm_open(SHM_NAME, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
        ftruncate(shared_fd, SHM_SIZE);
        new_files_buffer* shared_buffer = (new_files_buffer*)mmap(
            NULL, SHM_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, shared_fd, 0);

        read_candidate(readed_candidate, shared_buffer, mutex_sem, produce_sem,
                       consume_sem);
        get_candidate_job_reference(readed_candidate, readed_job_reference);
        move_candidate_data(extract_candidate_number(readed_candidate),
                            readed_job_reference);

        sem_close(mutex_sem);
        sem_close(consume_sem);
        sem_close(produce_sem);
    }

    return 0;
}

void move_candidate_data(int candidate_num, char* job_reference) {
    char output_path[PATH_MAX] = OUTPUT_DIR;
    DIR* input_dir = opendir(INPUT_DIR);
    DIR* output_dir = opendir(OUTPUT_DIR);
    struct stat st;

    strcat(output_path, job_reference);

    if (stat(output_path, &st) == -1) {
        if (mkdir(output_path, 0700) == -1) {
            perror("Failed to create output directory");
            closedir(input_dir);
            return;
        }
    }

    struct dirent* entry;
    while ((entry = readdir(input_dir)) != NULL) {
        if (extract_candidate_number(entry->d_name) == candidate_num) {
            char source_path[PATH_MAX] = INPUT_DIR;
            char dest_path[PATH_MAX] = OUTPUT_DIR;
            strcat(source_path, entry->d_name);
            snprintf(dest_path, PATH_MAX, "%s%s/%s", OUTPUT_DIR, job_reference,
                     entry->d_name);
            
            if (rename(source_path, dest_path) != 0) {
                perror("Failed to move file");
            }
        }
    }

    closedir(output_dir);
    closedir(input_dir);
}

void get_candidate_job_reference(char* readed_candidate, char* dest) {
    char path[PATH_MAX] = INPUT_DIR;
    strcat(path, readed_candidate);
    FILE* file = fopen(path, "r");

    if (file == NULL) {
        perror("candidate file");
    }

    fgets(dest, NAME_MAX, file);
    strcat(dest, "/");

    fclose(file);
}

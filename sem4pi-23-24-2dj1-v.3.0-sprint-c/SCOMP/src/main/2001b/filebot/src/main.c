#include "../header/circular_buffer.h"
#include "../header/depend.h"
#include "../header/global.h"

pid_t workers[NUMBER_CWORKERS];

sem_t* new_files_sem;
sem_t* mutex_sem;
sem_t* consume_sem;
sem_t* produce_sem;

int shared_fd;
new_files_buffer* shared_buffer;

DIR* input_dir;

struct sigaction sa;

void initialize_resources();
void clean_resources();
void sig_handler();

int main(int argc, char* argv[]) {
    clean_resources();
    initialize_resources();
    int i = 0;
    for (i = 0; i < NUMBER_CWORKERS; i++) {
        workers[i] = fork();
        if (workers[i] == 0) {
            int ret = execl(CWORKER_EXECUTABLE, "cworker", NULL);
            printf("Error execl %d\n", ret);
            exit(EXIT_FAILURE);
        }
    }

    pid_t pid = fork();

    if (pid == 0) {
        execl(DIR_SCANNER_EXECUTABLE, "dir_scanner", NULL);
        perror("execl scandir");
        exit(EXIT_FAILURE);
    }

    sem_wait(new_files_sem);
    input_dir = opendir(INPUT_DIR);
    if (input_dir == NULL) {
        perror("opening input dir");
    }

    struct dirent* dir_entity;
    dir_entity = readdir(input_dir);

    while (dir_entity != NULL) {
        if (dir_entity->d_type != DT_REG) {
            dir_entity = readdir(input_dir);
            continue;
        }
        char* filename = dir_entity->d_name;
        filename = filename + 2;
        if (strcmp(filename, CANDIDATE_DATA) == 0) {
            write_candidate(dir_entity->d_name, shared_buffer, mutex_sem,
                            produce_sem, consume_sem);
        }

        dir_entity = readdir(input_dir);
    }

    closedir(input_dir);

    for (int i = 0; i < NUMBER_CWORKERS; i++) {
        wait(NULL);
    }
    


    return 0;
}

void initialize_resources() {
    mutex_sem = sem_open(MUTEX_SEMAPHORE, O_CREAT | O_EXCL, 0644, 1);
    consume_sem = sem_open(CONSUME_SEMAPHORE, O_CREAT | O_EXCL, 0644, 0);
    produce_sem =
        sem_open(PRODUCE_SEMAPHORE, O_CREAT | O_EXCL, 0644, NEW_CANDIDATES_MAX);
    if (mutex_sem == NULL) {
        perror("mutex");
        exit(EXIT_FAILURE);
    }
    new_files_sem = sem_open(NEW_FILES_SEMAPHORE, O_CREAT | O_EXCL, 0644, 0);

    shared_fd =
        shm_open(SHM_NAME, O_CREAT | O_EXCL | O_RDWR, S_IRUSR | S_IWUSR);
    ftruncate(shared_fd, SHM_SIZE);
    shared_buffer = (new_files_buffer*)mmap(
        NULL, SHM_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, shared_fd, 0);
    if (shared_buffer == NULL) {
        perror("mmap");
        exit(EXIT_FAILURE);
    }

    memset(shared_buffer->candidates, 0, sizeof(shared_buffer->candidates));
    shared_buffer->read = -1;
    shared_buffer->write = -1;

    memset(&sa, 0, sizeof(struct sigaction));
    sa.sa_handler = sig_handler;
    sa.sa_flags = SA_RESTART;
    sigaction(SIGINT, &sa, NULL); 
    sigfillset(&sa.sa_mask); 
}

void clean_resources() {
    sem_close(mutex_sem);
    sem_close(consume_sem);
    sem_close(produce_sem);
    sem_close(new_files_sem);
    sem_unlink(MUTEX_SEMAPHORE);
    sem_unlink(CONSUME_SEMAPHORE);
    sem_unlink(PRODUCE_SEMAPHORE);
    sem_unlink(NEW_FILES_SEMAPHORE);

    close(shared_fd);
    munmap(shared_buffer, SHM_SIZE);
    shm_unlink(SHM_NAME);
}

void sig_handler() {
    puts("Generating report...");
    char path[PATH_MAX] = OUTPUT_DIR;
    strcat(path, "report.txt");
    FILE* report = fopen(path, "a");
    for (int i = 0; i < NEW_CANDIDATES_MAX; i++) {
        if (shared_buffer->candidates[i][0] == '\0') {
            break;
        }
        char candidate = shared_buffer->candidates[i][0];
        fprintf(report, "%s\n", candidate);
    }
    clean_resources();

}
#include "../header/circular_buffer.h"
#include "../header/depend.h"
#include "../header/global.h"

int main() {
    while (true) {
        sem_t* new_files_sem = sem_open(NEW_FILES_SEMAPHORE, 0);
        DIR* input_dir = opendir(INPUT_DIR);

        struct dirent* dir_entity;
        dir_entity = readdir(input_dir);
        if (dir_entity == NULL) {
            printf(
                "No files detected in this scan pausing for aprox %d min...\n",
                SCAN_INTERVAL / 60000);
            sleep(SCAN_INTERVAL);
            continue;
        }

        sem_post(new_files_sem);
        printf("New files detected, dir_scanner pausing for aprox %d min...\n",
               SCAN_INTERVAL / 60000);
        sleep(SCAN_INTERVAL);
    }
    return 0;
}

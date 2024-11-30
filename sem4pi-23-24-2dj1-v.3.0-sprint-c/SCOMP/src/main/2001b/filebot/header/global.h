#pragma once

#define NUMBER_CWORKERS 5
#define CWORKER_EXECUTABLE "/home/andre/filebot/bin/cworker"

#define CANDIDATE_DATA "a.txt"
#define OUTPUT_DIR "/home/andre/filebot/outputdir/"
#define INPUT_DIR "/home/andre/filebot/inputdir/"
#define DIR_SCANNER_EXECUTABLE "/home/andre/filebot/bin/dir_scanner"
#define NEW_FILES_SEMAPHORE "/scanned_files_sem"

#define MUTEX_SEMAPHORE "/mutex_sem"
#define CONSUME_SEMAPHORE "/consume_sem"
#define PRODUCE_SEMAPHORE "/produce_sem"
#define SHM_NAME "/filebotshm"
#define SCAN_INTERVAL 120000

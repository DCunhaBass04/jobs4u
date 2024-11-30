#!/bin/bash

gcc src/dir_scanner.c src/circular_buffer.c -o bin/dir_scanner -lpthread -lrt
gcc src/cworker.c src/circular_buffer.c -o bin/cworker -lpthread -lrt
gcc src/main.c src/circular_buffer.c -o bin/main -lpthread -lrt

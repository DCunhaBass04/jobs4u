#!/bin/bash

cd ../../test_code/FileProcessor

gcc -Wall -o prog main.c ../unity.c ../../../../main/2001/ConfigFileReader/ConfigFileReader.c ../../../../main/2001/ChildWork/ChildWork.c

exit

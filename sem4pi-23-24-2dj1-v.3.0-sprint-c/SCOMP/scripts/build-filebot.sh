#!/bin/bash

cd ../src/main/2001/FileProcessor

gcc -Wall -o FileProcessor FileProcessor.c ../ConfigFileReader/ConfigFileReader.c ../InputChecker/InputChecker.c ../ChildWork/ChildWork.c

exit

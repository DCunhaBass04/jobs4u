#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../../../../main/2001/ConfigFileReader/ConfigFileReader.h"
#include "../unity.h"

void setUp(void){
	// Set stuff up here
}

void tearDown(void){
	// Clean stuff up here
}

void run_test(char* testName, char* configFileName, 
				char* inputDirectoryTest, char* outputDirectoryTest,
				int nChildrenTest, int cooldownTest){
					
	FILE *fp;
	if((fp = fopen(configFileName, "r")) == NULL){
		perror("fopen");
		exit(EXIT_FAILURE);
	}
		
    ConfigSettings conf;
    readConfigFile(fp, &conf);

	TEST_ASSERT_EQUAL_STRING(conf.inputDirectory, inputDirectoryTest);
	TEST_ASSERT_EQUAL_STRING(conf.outputDirectory, outputDirectoryTest);
	TEST_ASSERT_EQUAL_INT(conf.nChildren, nChildrenTest);
	TEST_ASSERT_EQUAL_INT(conf.cooldown, cooldownTest);

    fclose(fp);
}

void test_empty_config(){
	run_test("Empty Config", "empty.properties", "", "", 0, 0);
}

void test_normal_config_one(){
	run_test("Normal Config #1", "config.properties", "../../Email-Bot-Output-Example", "../../output", 5, 2);
}

int main()
{
	UNITY_BEGIN();
	RUN_TEST(test_empty_config);
	RUN_TEST(test_normal_config_one);
	return UNITY_END();
}


grammar Req1;

prog: questionOne NEWLINE* questionTwo NEWLINE* questionThree NEWLINE* ;

questionOne: '# Enter the number of years of experience (integer)' NEWLINE answerOne NEWLINE*
        ;

answerOne: 'Experience-years: ' answerOneText
      ;

answerOneText: INT+  # verifyAnswerOne
                ;

questionTwo: '# Select one degree (None; Bachelor; Master; PhD)' NEWLINE answerTwo NEWLINE*
        ;

answerTwo: 'Academic-degree: ' answerTwoText
      ;

answerTwoText: ('None' | 'Bachelor' | 'Master' | 'PhD')  # verifyAnswerTwo
                ;

questionThree: '# Select one or more programming languages you are proficient in (java; javascript; python)' NEWLINE answerThree NEWLINE*
      ;

answerThree: 'Programming-languages: ' answerThreeText
    ;

answerThreeText: language (', 'language)*  # verifyAnswerThree
              ;

language: 'java'|'javascript'|'python';

INT : [0-9];

NEWLINE: [\r\n]+;

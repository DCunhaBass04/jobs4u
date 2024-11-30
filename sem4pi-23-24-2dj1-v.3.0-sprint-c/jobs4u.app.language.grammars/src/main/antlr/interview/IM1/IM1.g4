grammar IM1; // fazer esta treta

prog: questionOne NEWLINE* questionTwo NEWLINE* questionThree NEWLINE* ;

questionOne: '# ISEP was founded in the year 1910 (true/false)' NEWLINE answerOne NEWLINE*
        ;

answerOne: 'Founding-year: ' answerOneText
      ;

answerOneText: ('true' | 'false')  # verifyAnswerOne
                ;

questionTwo: '# Select one degree (None; Bachelor; Master; PhD)' NEWLINE answerTwo NEWLINE*
        ;

answerTwo: 'Academic-degree: ' answerTwoText
      ;

answerTwoText: ('None' | 'Bachelor' | 'Master' | 'PhD')  # verifyAnswerTwo
                ;

questionThree: '# Select one or more programming languages you are proficient in (Java; C#; PHP; Javascript; Typescript)' NEWLINE answerThree NEWLINE*
      ;

answerThree: 'Programming-languages: ' answerThreeText
    ;

answerThreeText: language (', 'language)*  # verifyAnswerThree
              ;

language: 'Java'|'C#'|'PHP'|'Javascript'|'Typescript';

NEWLINE: [\r\n]+;

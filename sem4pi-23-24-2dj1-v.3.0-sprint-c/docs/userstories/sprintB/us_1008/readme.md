# US 1008

**As Language Engineer, I want to deploy and configure a plugin (i.e., Job Requirement Specification or Interview Model) to be used by the system.**

## 1. Context

* This US was assigned during the second **Sprint**, as part of the *Plugin* setup.

## 2. Requirements

**Acceptance Criteria:**

* 1008.1. The team must ensure that the our program can read an outside jar file (plugin) that implements an *Interface* from our program, without knowing the specific implementation.

The team decided that, since most of this *US* happens outside our program, a **System Sequence Diagram** was not appropriate.

**Dependencies/References:**

*  The support for this functionality must follow specific technical requirements, specified in LPROG. 
   * The **ANTLR** tool should be used (**https://www.antlr.org/**).

## 3. Analysis

* Since the program needs to read jar files from outside programs, the team decided to create a separate project that would be the source of an example jar file, using **ANTLR**.
  * This separate project would support a *grammar* and an implementation for our **InterviewEvaluator** or **ReqEvaluator**. Thus, this project would need to give an implementation for their methods (**generateTemplateFile()** and **evaluateFile(file)**).

## 4. Design

* TODO

## 5. Tests

* N/A

## 6. Implementation

* TODO

## 7. Integration/Demonstration

* TODO

## 8. Observations

* TODO
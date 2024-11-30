# US 1018
### As Customer Manager, I want to execute the process that evaluates (grades) the interviews for a job opening.
## 1. Context

* This US was assigned during the third **Sprint**.

## 2. Requirements
### 2.1 Dependencies

* This *US* has a dependency on [**US 1011**](../../sprintB/us_1011/readme.md)
* This *US* has a dependency on [**US 1017**](../us_1017/readme.md).

### 2.2 Pre Conditions

The job opening must have a interview model set for evaluation (hence the dependency on **US 1011**).       

The job application of a job opening must have an interview answer settled (not necessary for all) (hence the dependency on **US 1017**)

### 2.3. Open Questions:

**Question (captured from US 1015):**

Deve ser executado o processo para todos os candidatos ou apenas para alguns (segundo algum critério)?

**Answer:** -  Os ficheiros com as respostas aos requisitos vão entrando no sistema gradualmente. Talvez seja mais “simples” que o processo execute (ou seja, faça a verificação dos requisitos) para os candidatos para os quais já foi submetido o ficheiro de requisitos. Nalgum momento o processo irá executar com todos os candidatos já com os ficheiros de requisitos submetidos.

**Question:**

- No caso de upload de um ficheiro, se a pergunta que requer um número como resposta for preenchida com um formato inválido, por exemplo, uma letra, devemos considerar isso como um formato inválido na US 1017 (e pedir para o user voltar a dar upload a um ficheiro válido) ou devemos, na US1018, considerar que está incorreta e atribuir 0 pontos automaticamente para essa resposta inválida? Isto é, na US 1017, devemos apenas verificar o formato do ficheiro ou devemos verificar também se as respostas são preenchidas com o tipo de dados correto?

**Answer:**

- O caso mencionado deve ser considerado um erro de validação do ficheiro (ou seja, o ficheiro submetido não corresponde à gramática definida).

**Question:**

- No caso de upload de um ficheiro, se a pergunta que requer um número como resposta for preenchida com um formato inválido, por exemplo, uma letra, devemos considerar isso como um formato inválido na US 1017 (e pedir para o user voltar a dar upload a um ficheiro válido) ou devemos, na US1018, considerar que está incorreta e atribuir 0 pontos automaticamente para essa resposta inválida? Isto é, na US 1017, devemos apenas verificar o formato do ficheiro ou devemos verificar também se as respostas são preenchidas com o tipo de dados correto?

**Answer:**

- **Question:**

**Question:**

- Nesta user story , as notas da entrevista têm que ter obrigatoriamente uma justificação ?
**Answer:**

- O caso mencionado deve ser considerado um erro de validação do ficheiro (ou seja, o ficheiro submetido não corresponde à gramática definida).

**Answer:**

- O caso mencionado deve ser considerado um erro de validação do ficheiro (ou seja, o ficheiro submetido não corresponde à gramática definida).



## 3. Analysis

### 3.1. Acceptance Criteria/Bussines Rules:

- The job opening must be in interview phase.
- The process of execution of evaluation of interviews is only made in job applications with interview answers but without interview points.
- The evaluation of each interview must result in a value in a number with a range of 0-100 and is automatically settled on each job application.


### 3.2 Analysis


**From reading the system description, we can conclude that:**
* This *Use Case* belongs to the ***Screening*** phase of the *Interview Model* of that *Job Opening* (See **Page 5**):
    * "**Screening** - This phase follows the application phase. In this phase, applications are verified against a set of requirements. Applications that do not meet mandatory requirements are rejected."





**The domain model includes everything that's needed in order to perform this *US* as needed.**
* The sections in red show what is related to this *US*:

![domainModel](image_files/domain_model_partial_Evaluate_Grades_For_JobOpening.png)

**Note:** No aggregate is altered during this process.


## 4. Design

**All Design decisions regarding the classes used for this user story have already been made previously.**

The relevant ones for this US are the repositories made. Out of all the classes involved in this user story, the only ones that have their own repositories are the JobApplication and the JobOpening classes, as they are the center class of each of their aggregates. The Interview and InterviewGrades classes are part of the JobApplication's attributes and are saved through the JobApplication class. As such, to save the changes made to the Interview when the InterviewGrades are evaluated, we need to save the state of the JobApplication to its respective repository after changing the Interview stored in the JobApplication.

These following decisions led us to conclude the sequence diagram bellow:

## Sequende Diagram to Do.

## 5. Tests

### 5.1. Domain Rules Verifying Tests:

- Verify The job opening must be in interview phase.
- Verify The process of execution of evaluation of interviews is only made in job applications with interview answers but without interview points.
- Verify The evaluation of each interview must result in a value in a number with a range of 0-100 and is automatically settled on each job application.

## 6. Integration/Demonstration

* TODO

## 7. Observations

* TODO


## 4. Design



## Como saber (que classes deve ter essa responsabilidade) a fase atual do processo em que se encontra este job opening?
- **Who knows the CurrentPhase?:**
  - RecruitmentProcess is the Information Expert. He has the respnsability of knowing the Recruitment Phase.


 - **Who knows Recruitment Process ?:** 
   - Job Opening , is the Information Expert. He has the respnsability of Knowing the Recruitment Process.

**Conclusion:** It is enough to have a Job Opening to determine the current phase.
    **Responsability of Verifying the CurrentPhase:**
     JobOpening has the responsability of veryfying the JoboOpening is in currentPhase:

boolean verifyJobOpeningphase(JobOpeningP){
}

## Como garantir que apenas os atributos apropriados são alaterados tendo em conta essa fase (não podes depender da UI)

### Important Classes for User story: 
  On Importance color Filtering Domain Model on "Analysys" section.


## Responsability of Editing and Saving Atributes:

Only to methods were set , since the client told us , these are the two only possible atributes scenarios on this user story. (on **"Requirements Section"**)

![Captura de ecrã 2024-06-04, às 14.53.20.png](..%2F..%2F..%2F..%2F..%2FCaptura%20de%20ecr%C3%A3%202024-06-04%2C%20%C3%A0s%2014.53.20.png)
![Captura de ecrã 2024-06-04, às 14.53.26.png](..%2F..%2F..%2F..%2F..%2FCaptura%20de%20ecr%C3%A3%202024-06-04%2C%20%C3%A0s%2014.53.26.png)


Esta especificação esta na classe Job Opening
- **JobOpening (Repository) ,Again: Information Expert:** 
   - .set for each Atribute
   - .save 
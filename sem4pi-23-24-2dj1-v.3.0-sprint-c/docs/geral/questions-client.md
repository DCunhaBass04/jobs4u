# Questions for the client

This document will house the various questions asked along the project's duration to its client and their respective answers (if they've been answered).

This is a live document, so it'll be constantly updated in the middle of a *Sprint* and in between *Sprints*.

## Question 1:

Are there **Minimum Viable Products** for each *Sprint*?

### Answer:

No. 

There are no **MVPs** for each *Sprint*. There can be minimum functionality for each *Sprint,* but it depends on the team structure and composition.

Professors from each **UC** may define minimum requirements in this context.

## Question 2:

Are the interviews done in person? If so, who is responsible for registering their answers in the system?

### Answer:

The platform used for interviews is outside the scope for this project. They can be in person or done remotely (ex: telephone or another platform). Either way, the ***Customer Manager*** is responsible for registering the answers in the system by uploading a text file with the ***candidate***'s answers.

## Question 3:

Who is responsible for analyzing the applications?

### Answer: 

It will be the ***Customer Manager***. They analyze the applications and rank the ***candidates***.

## Question 4:

Regarding the **continuous integration server**, does the **workflow** have to execute every *push* or every day at night?

### Answer:

Everytime there's a *push* done to the "*main*". The process executed by CI each *push* cannot exceed the 2-minute limit.

The process must be able to "*compile*" the system, execute tests and publish outcomes without "failures", meaning it must not fail because of "compilation" error. If there are any errors, the person "responsible" for causing it (the one that did that *commit/push*) must give an explanation (like explaining in their documentation).

## Question 5:

What's the difference between ***Company*** and ***Entity***?

### Answer:

When we say "entity" it represents **Jobs4U**'s clients that may not be a company, they can be some other type of organization.

## Question 6:

Is there only one "representative" that can access an account for each ***Customer*** (i.e., ***Customer App***)?

### Answer:

Yes, I think that's enough.

## Question 7:

When the ***Customer Manager*** registers a ***job opening***, how are its requirements defined/selected?

### Answer:

The ***Customer Manager*** registers the ***job opening*** (**US 1002**) and then selects the most adequate ***requirements specification*** (that is created by the ***Language Engineer***, and then registered in the system).

## Question 8:

The ***email bot*** is said to be "outside the scope" for this project. Is that about the **system** or about the **Business Model**?

### Answer:

Having an idea of how the applications are received and processed is important. Having that said, the automatic process described as "*email bot*" is outside the scope in terms of development, as shown in figure 4.1.

## Question 9:

Regarding **G005**, are the mentioned *scripts* only used for *building* and testing?

### Answer:

I'd say that, for this stage of the project (*Sprint A*), that the *scripts* are only for **building the apps, executing tests and the apps**. However, you should be able to maintain a couple of scripts that **allow the team, at any moment, to do the more common operations in a simple way, without using an *IDE***.

Further on this will have a bigger importance when you have a need to prepare more complex "*deployments*".

## Question 10:

Is the ***Admin*** responsible for managing only ***Customer Managers*** or are they responsible for managing other actors, like ***Operators***? What is the business meaning of such a responsibility?

### Answer:

In theory, the ***Admin*** will manage these users (and ***Operators*** as well, as a last resort).

In practice, the *USs* that recall those functions can be "replaced" by a "*bootstrap*" process that initializes data inside the database in order to support those users (as mentioned in the *USs*' descriptions).

## Question 11:

The information to collect to the ***Customers*** is not made explicit by the project document? What is the necessary data? What about the company's employees?

### Answer:

It is true that it's not explicit. However, the necessary information is mentioned in the company's name and address in the context of a ***job opening***.

Regarding the users (company's representative that accesses the ***Customer App***) I'd say that the data is similar to the ***Candidate***'s.

Regarding the company's employees, I'd say that it's important to make sure that the *email* is used to identify any system user.

I think it's important to have the full name and a **unique *short username*** for each user.


**Edited on 2024-03-21**: The ***Product Owner*** decided that the ***short username*** is dispensable, because only the email and password are used for authentication.

## Question 12:

What is the difference between job requirements specifications and interviews?

### Answer:

The questions are similar in "style", but the job requirements specifications' objective is to evaluate a candidate and conclude if they meet the minimum requirements, so the final result is either "yes" or "no".

Interviews has a score attached to each question resulting in different final scores for each candidate, which in turn lead to their ranking.

## Question 13:

Can **one** ***Customer Manager*** manage multiple clients?

### Answer:

Yes.

## Question 14:

Who is responsible for informing the ***Customer Manager*** about the interview format and type of questions?

### Answer:

That can be obtained by a dialogue between the ***Customer Manager*** and the ***Customer***. Then, with help from the ***Language Engineer***, the **Interview Model** for that **Interview** is created.

## Question 15:

Is the ***Operator*** the one responsible for registering ***Applications*** or does the system do that automatically? How is the ***Application*** verification "plugin" integrated in this process?

### Answer:

The ***Operator*** registers the ***Application*** during ***US 2002***. They initialize the process, but the system must import the ***Application File Bot***'s output automatically (View ***US 2002***'s *References*).

The plugin mentioned is implemented in this process through ***US 2003***, where the ***Operator*** generates a template file with the data to fill in order to verify the ***Application***.

During ***US 2004***, the ***Operator*** will submit the ***Application*** (after filling their specific data, based on the previously mentioned template) on the system. That system will verify/rate the ***Application***.

If the requirements are not met, the ***Application*** is automatically rejected.

## Question 16:

Regarding **Section 2.2.1** and the recruitment process's phases, do we have to close one phase to start the next, or can a certain phase be started without finishing the previous?

### Answer:

Short answer is: Phases must come one after the other. When one phase starts, the other ends.

***US 1007*** aims to set up the phases. ***US 1010*** aims to open or close phases.

The ***Customer Manager***'s decision to close a phase must acknowledge that the process will advance to the next phase automatically (without worrying about the dates defined for each phase).

## Question 17:

Regarding **Section 2.2.1**, are the interviews scored during the **Analysis** phase? And is that score that defines the candidate's ranking?

What is the **CV** used for in this phase?

Since the interviews are optional, what happens when they're not done?

### Answer:

The score is calculated during the **Interview Phase**. The **CV** and other details (like the interview score) are used by the **Customer Manager** during the **Analysis Phase** in order to rank the candidates.

However, the ranking is done by the ***Customer Manager*** (they don't have to follow the interview score order, for example).

***US 1013*** is about the manual candidate ranking done by the ***Customer Manager***. Not having any interviews does not affect the ranking of the candidates, since they do not depend on the interviews explicitly.

## Question 18:

In ***US 1011*** how does the ***Customer Manager*** select the interview model to be used for an interview?

### Answer:

The interview models are registered in the system (the "plugins" mentioned previously), identified with a name or a description.

**Example:** "Interview Model for a Supermarket Cashier" or "Interview Model for a Junior Backend Java Programmer".

In ***US 1011*** the ***Customer Manager*** has to select a model from a possible list.

## Question 19:

When creating a new user on the system:
1. Is the name defined by the user, or is it the person's real name (like their first and last names)?
2. Is the password defined by the user, or is it generated by the system?

### Answer:

On ***US 2000a***, the ***Operator*** creates profiles for the ***Candidates*** that are not in the system currently. They must do that with the data received from the ***Application*** (that contains an email and a name). 

The email will identify the person. In this context, the user must have a password. Since that way not transmitted by the ***Candidate***, the best approach would be generating a password. The method used to send that password to the user is **out of scope**, since there isn't a single **US** that mentions that.

***US 1000*** and ***US 1001*** also aim to create users. In this case, you could enter a password manually, but you could also use the same generated passwords.

**Regarding the name section, visit *Question 11***.

## Question 20:

Can one person have multiple roles in the system?

### Answer:

It would be difficult to control if a person is obligated to not have more than one way to access the system (a person that is a ***Customer Manager*** can also be a ***Candidate*** for a ***Job Opening***, for example).

Regarding the "internal" roles, I'd say that we should consider a hierarchy. The ***Admin*** can do "everything" the other users do. Next in line is the ***Customer Manager***, and then the ***Operator***.

## Question 21:

Regarding the companies and **Question 11**, what is the address mentioned?

### Answer:

That address is the company's postal code (not the email address).

## Question 22:

Regarding the ***Job Opening*** (**Section 2.2.2**), the ***Job Reference*** is generated by the system using a "***Customer Code***". What is this ***Customer Code***, and is there a rule for its creation?

### Answer:

I'd say that any ***Customer*** has a unique identification code that will be some kind of abbreviation of their name. 

**Example:** For the client "**Instituto Superior de Engenharia do Porto**", the ***Customer Code*** could be "**ISEP**", and no other ***Customer*** can have this ***Customer Code***.

We'll limit the code to 10 characters.

This code is typed in manually while creating a ***Customer*** in the system.

## Question 23:

Is the changing status related to the ***Candidate*** or the individual ***Application***? And how does that connect to the *enable/disable* of users?

### Answer:

The *enable/disable* of users is to be used only for controlling the accesses to the system.

The status is related to the ***Application***. It is not directly related to the *enable/disable* of users.

## Question 24:

In order to identify an ***Application***, do we use the ***Candidate***'s ID and the ***Job Opening***'s ID?

### Answer:

Not exactly. That ***Applications*** will enter the system by the files created by the ***Application Email Bot***. Those files are identified by the ***Job Reference***, followed by a "number" that identifies that ***Application*** to that ***Job Reference***. That "number" will, usually, be a sequential number.

Inside those files, the ***Candidate***'s details will be visible. It's possible that the ***Candidate*** does not yet exist in the system (they haven't been registered yet).

## Question 25:

Is it obligatory to fill everything in the ***Job Opening***? Or are there any optional spots?

### Answer:

The fields mention in **Section 2.2.2** are mandatory. The requirements will be dynamic, since they depend on the ***Requirement Specifications*** selected for that ***Job Opening*** (that's based on a certain language).


## Question 26:

At what moment is the recruitment process initiated? Are there more than one recruitment phase, how do you distinguish the phases, is it by the date?

### Answer:

Refers to the answer to Question 16.

## Question 27:

Does the test model score represent the candidate's ranking in the selection process?

### Answer:

Refers to the answer to Question 17 (answers indirectly).

## Question 28:

Is it intended that each Customer be identified in the system as a user?

### Answer:

Refers to the answer to Question 6 (answers indirectly).

## Question 29:

From the app file bot point of view, should it represent a service in the system?

### Answer:

I would say yes, as it is a process necessary in the system to transform received data into a format that is "recognized" by the recruitment process.

## Question 30:

US2000b, what does enabling/disabling the candidate mean?

### Answer:

(Some reference to Question 23). Refers to disabling the candidate's access to the system (i.e., Candidate App).

## Question 31:

Regarding the job specification, should the client send the requirements or is it the responsibility of the customer manager? What is the concept of a job specification?

### Answer:

(Some reference to Question 25). Typically, it will be the client who informs the customer manager of the minimum requirements for a job offer. The customer manager checks if there is already an appropriate requirement specification. If not, with the help of the Language Engineer, a new one is created.

## Question 32:

Do candidates also have associated states? As the process progresses, is the candidate's state also updated?

### Answer:

The state is of the application. Progress in the process may not lead to "progress" in an application because, for example, at the end of the screening, the application may be rejected and, in that case, that application ends there. Other applications follow the process.

## Question 33:

Does the candidate have an identification code or is it the email that identifies them?

### Answer:

The identification of the candidate is by email. There will be no need for a code.

## Question 34:

US3002, list job openings, what is position?

### Answer:

In this US, when we refer to "position," it has the same meaning as "title or function" in section 2.2.2.

## Question 35:

Does the Customer have to have an address and company name or is it enough for this information to be in the job opening?

### Answer:

We should register the name and address of the customer. For each job opening, the address can be specific (and different from the customer's address).

## Question 36:

US1021, what is "all data of an application"? What is a job application?

### Answer:

A job application is an application (from a candidate) to a job opening. Regarding "all data of an application," it refers to all the data of an application, including the files submitted by the candidate as well as data collected or generated during the process (such as interviews and requirement processing).

## Question 37:

In the job opening (section 2.2.2), in the company field, should it be the customer name or the customer code, since the customer code is unique and entered manually?

### Answer:

The information regarding the job opening that appears at the end of page 5 should be seen as something to be used in the promotion of a job offer. In that context, for the Company, it makes more sense to disclose the company name and not its code. That said, in terms of storage in a database, the code may be used.

## Question 38:

Does each question in an interview model accept x types of answers (e.g., multiple choice) or is it the interview model that accepts x types of answers in all its questions? Do we assume that a job opening only follows one interview model?

### Answer:

Yes, each question/answer accepts one type of question/answer (one of the types listed at the beginning of page 8). In US1011, the Customer manager selects the interview model to be used in the interviews for a job opening. In other words, there will only be one interview model used in the interviews for that job opening.

## Question 39:

Is the recruitment process as defined or can there be changes in the future?

### Answer:

The process is as described in section 2.2.1. Currently, the only optional phase is the interviews.

## Question 40:

Can an interview have only one question? In US1014, time and date, does it mean the start date and not the end date? Can there be interviews in parallel?

### Answer:

Regarding the number of questions in an interview, no lower or upper limit is defined. In other words, there can be an interview with only 1 question (although having no questions wouldn't make sense). US1014 refers to scheduling the date of an interview with a candidate. Something like indicating the day and time (e.g., April 23 at 2:00 PM). Regarding scheduling "overlapping" interviews (with the same start date), at this time, it would be something allowed (for example, the customer manager may delegate another person to conduct the interview). However, this does not invalidate the need to validate if the interviews occur within the interview phase.

## Question 41:

How does the Language Engineer create the interview model and job requirements? Is it text? Or does he select questions for the interview and requirements for the job opening? And is this when creating an interview or a job opening, or does he stop halfway to do this and then continue?

### Answer:

The language engineer, with information provided by the customer manager (obtained from the customer), will develop a corresponding jar module/plugin in Java. For this development, techniques of grammar/language development such as ANTLR will be used. This code will be in a jar which the language engineer then "installs/registers" in the application (US1008, for example, associating a name to the jar in a configuration file – "5 years Java experience", "req-model-5-years-java.jar"). The application loads this jar dynamically with that information. The structure of the questions to be used in that model and its evaluation will be reflected in the grammar used in the jar. These activities must be done before the US1008 can be performed. This work is done "outside" the system, only registering the model (when it is ready) in US1008. US 1009 and US1011 allow selecting models to use (those that have been properly registered in the system).

## Question 42:

US1006, What information of the candidate's name should appear (full name, first and last name, etc.)?

### Answer:

In principle, it would be the name as received in the application that was made (page 8).

## Question 43
For the candidates and for system users, what information is necessary?

### Answer:
Some previous information is mentioned in Q11. Besides that, section 2.2.3 states that we have the following information about candidates: email of the candidate, name of the candidate, phone number of the candidate.

## Question 44
Regarding section 2.2.3 and the fact that the application email bot is out of scope but produces the information described in this section, will examples of this information be provided?

### Answer:
Yes. An example of the information produced by the application email bot will be provided.

## Question 45
Regarding the possible statuses of an application, what are the possible statuses?

### Answer:
The status should reflect at which stage of the process it is and the possible final outcome. From the customer's point of view, it should be something that makes sense to them. That is, it might not be sensible for the candidate to know details that may not make sense to them, as they may not be aware of all the internal details of managing an application process.

## Question 46
Can the recruitment process identifier be an automatic number or does it aim to be a more specific

### Answer:
The job opening has an identifier. The recruitment process for a job opening is an "attribute" of that job opening. Initially, there is no need for a "special" identifier for the recruitment process (i.e., phases of the recruitment process for that job opening).

## Question 47
Regarding the modules of interviews and requirements, can their identifiers be automatic or specific (i.e., manual)?

### Answer:
Each module will be recorded in the system using 2 pieces of data, for example, associating a name to a jar in a configuration file – "5 years experience java", "req-model-5-years-java.jar". It is assumed that each module will have a unique name/designation and to this name will be associated the jar file name (probably a complete path) that implements that module. Thus, this name/designation can be considered a specific/manual identifier.

## Question 48
Regarding the sending of email notifications, is it necessary to save that this sending has been done?

### Answer:
The document does not state anything explicit about this matter. However, from the management perspective, it seems appropriate that this information be recorded.

## Question 49
In a requirements validation, should a failure be justified? In an interview as well?

### Answer:
No. In interviews, the aim is not to accept or reject a candidate but to evaluate them on a scale from 0 to 100. The way this is done is described in section 2.2.4.

## Question 50
Regarding the files generated by the email file bot, what identifies what each file contains?

### Answer:
Each file has a unique prefix that identifies an application (an integer before the first "-"). All files with the same prefix belong to the same application. After the prefix, "cv" indicates a curriculum, "email" is the email sent by the candidate, and "candidate-data" are the 4 identification data of the application, as described in 2.2.3. All other files with the same prefix are considered attachments.

## Question 51
What is the format for this publication?

### Answer:
The publication is the process of informing candidates and the client via email. Selected candidates will receive an email indicating that they have been selected for the job opening and will be contacted by the company. The company will receive an email with a list of selected candidates including their names and contact details.

## Question 52
Does a job opening only have one customer manager?

### Answer:
Yes, typically a customer manager manages all job offers from a client. Consequently, there is only one customer manager for each job opening.

## Question 53
Can the recruitment process be identified by the job reference?

### Answer:
There is no need for an explicit identifier for the recruitment process, as there is only one for each job reference, and it is specific to each job reference. If needed, the ID for the recruitment process could be the same as the job reference.

## Question 54
How will a candidate be registered in the system and notified of their username and password?

### Answer:
There is no need for a username. As for the password, the system can generate a unique password. The candidate will be informed of their password by means outside the scope of the current system development.


## Question 55
Following the point "2.2.1 Recruitment Process", the customer manager is responsible for setting up the process, defining the phases, and indicating whether it includes an interview. We request clarification on whether the phases of the recruitment process will always be the same "application; screening; interviews; analysis; result;" (interview not mandatory), or can the phases be dynamic? Additionally, in the case of an interview, will there only be one for a JobOpening?

### Answer:
The first issue refers to question Q39, which has been answered. As for interviews, it is assumed that the same interview model will be used for all interviews of a "job opening," meaning all interviews will have the same questions. Therefore, it is appropriate to assume that there will only be one interview for a "job opening."

## Question 56
Are the validations for mobile phone numbers and postal codes done only for Portuguese domains, or must we program according to the country concerned?

### Answer:
For Portuguese cases only, the validation is sufficient.

## Question 57
What are the business policies for registering a candidate, and what are the characteristics of the password, email, and mobile phone?

### Answer:
For mobile phones, follow Q56. For email, any valid email will suffice. For the password, the policy could be: at least 8 characters, including upper and lower case letters, digits, and at least one non-alphanumeric character.

## Question 58
When listing candidates in US 2000c, is it enough to list their emails?

### Answer:
It would make sense to present each candidate's email and name.

## Question 59
Is it intended for all users to access the same application and, depending on their credentials, have access to different functionalities, or are they different applications (that access the same database)?

### Answer:
From the product owner's perspective, it is sensible to have separate applications. For example, when a user runs the "Candidate App," even if they identify themselves as a valid "Customer" type user, the application should not accept that login.

## Question 60
In US 2000c, are there business policies related to listing candidates?

### Answer:
The listing should follow any conditions related to the potential status of the candidate.

## ~~Question 61~~
~~For US 1008, are there business policies related to listing candidates?~~

### ~~Answer:~~
~~The listing should follow any conditions related to the potential status of the candidate.~~

## Question 62
According to US1007/US1010 and Q16, each Job Opening must have defined phases. The status of the application is mentioned in Q23, Q32, and Q45. Are the phase of the Job Opening and the status of the application separate concepts or do they refer to the same thing?

### Answer:
As mentioned before, they are related but different concepts.

## Question 63
Regarding the criteria for listing applications in US1005: Should ongoing applications be shown, or can past applications appear? Can any applications appear, or only those that have been accepted? What information should be displayed for each application?

### Answer:
All applications for a job opening should be listed as described in the US. It makes sense to show all applications, regardless of their status. Therefore, the candidate and the status of their application should be identified for each application.

## Question 64
What is the expected behavior when the Application File Bot is restarted? If the requirements state that files should be copied and not moved, how does the bot determine which files have been previously copied? Also, how should the bot handle duplicate files, and what is the expected behavior regarding the report system?

### Answer:
The system should maintain a consistent state. There should not be any duplicate files. Regarding reports, multiple report files can be created, each uniquely identified by a timestamp.

## Question 65
What is the expected flow for executing US2002 (application registration and files import, by the operator)? Can you detail the steps the operator should follow?

### Answer:
There are no specific UI/UX requirements, but it would be more user-friendly if the Operator could start the process by selecting the shared folder for importing the application.

## Question 66
In US 2000c, should the candidates be listed in a particular order, such as alphabetical by name?

### Answer:
Yes, listing can be done in alphabetical order by name.

## Question 67
Concerning EmailFileBot in US2001, you mentioned that "All other files with the same prefix should be considered attachments." Is there a maximum number of files allowed as attachments, or do we not control the number of files submitted per application?

### Answer:
No maximum number is defined, but a limit can be configured, for example, in a configuration file with a limit in size (Mb) or in the number of attachments.

## Question 68
In US1003, when listing job openings, is there any criterion to define which to list? Or are all from the entire system included?

### Answer:
Filtering by Customer and date would be useful. It could also be useful to filter by active job openings or all job openings.

## Question 69
Regarding US1016 - "As Customer Manager, I want the system to notify candidates, by email, of the result of the verification process" - what is the process through which that notification will be generated? After the evaluation of the Requirement Specification module, this process creates a result which is either "Approved" or "Rejected". Does this result automatically trigger a notification, send to the candidate or is it the responsibility of the Customer Manager to inform the candidate about the result of the verification through the system (i.e. After a negative result is generated, the Customer Manager opens the system to reject the candidate, so that the email is sent)?

### Answer:
It's the second option. US1015 allows for the Customer Manager to invoke the process of verifying the requisites. After that, all the applications will be either rejected or accepted. With that said, is is then possible for the Customer Manager to invoke the notification through US1016.

## Question 70
US1009 - Regarding the user story about the selection of the job requirement specification for a job opening, could the customer manager chose a job opening that already has a set job requirement specification?

## Answer:
I admit that this situation might be possible for any similar user stories. With this in mind, the situation you are descbring is equivalent to any and all situations in which is is necessary to make a selection, but the user has made a mistake and might wish to opt for another option. However, it must be garanteed that the system stays in a consistent state.

## Question 71
Justification of an interview's grade - I would like to ask you: what's the format and type of justification that is needed for giving an interview grade?

## Answer:
The justification would be a list of the grades obtained in each question of the interview.

## Question 72
Multiple enable/disable (US1000) – Can a user (from the backoffice, for example) be enabled/disabled multiple times? 

## Answer:
Yes.

## Question 73
US1007 - In terms of the user interface, in this case, for the customer manager, how should the setup of the recruitment process be made?

## Answer:
I do not have any specific requisites for the user interface. Good practices and principles should be used in the design of the user interfaces, taking into account that "The customer manger is responsible to setup the process, defining the dates for the phases and if the process includes interviews" (section 2.2.1)

## Question 74
US2000a - Regarding the registration of the candidates, should the data be inserted manually or imported from a file with the candidate's data? 

## Answer
It makes sense for the data to be imported from a file, within the scope of the US2002. Eventually, there could be the possibility for the user to make changes, in case it is necessary. **US2000a refers to a functionality for the Operator to manually register candidates.** (Note: answer updated in 2024/04/18, in bold)

## Question 75
US1006 - Regarding the listing of the personal data of a determined candidate, will a customer manager

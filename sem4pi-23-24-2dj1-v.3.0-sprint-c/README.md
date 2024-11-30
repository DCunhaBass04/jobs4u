# Project Jobs4U

## 1. Description of the Project

Jobs4U is a company specialized in talent acquisition. The company provides recruitment services for job positions
in its clients.

The company’s clients are other companies or entities that need to recruit human resources.
In response to requests from its clients, **Jobs4U** develops all activities that allow it to select a
set of candidates for job offers (from its clients). At the end of the process, **Jobs4U** must deliver
to its client an ordered list of candidates for each job offer. The final recruitment decision is
the responsibility of the client

The aim of this project is to develop, in an exploratory way, a solution that allows automating the main activities of
the company while allowing the staff to continue their work in a digital format, simple but still as effective.

## 2. Planning and Technical Documentation

[Planning and Technical Documentation](docs/readme.md)

## 3. Framework and important technical notes

In this project we opted to use the spring framework. The application was built using spring boot.
We had some issues with the integration of the eapli.base project namely when it comes to the authentication and
authorization module so we also opted to implement our own authentication module: jobs4u.infrastructure.auth.
The main persisting mechanism used is spring data jpa.

When it comes to the architectural structure of the project it follows a typical hexagonal architecture. We tried to our best efforts 
to decopule the domain model from the rest of the layers: presentation and infrastructure. This was eased through the use of spring.

## 3. How to Build

Open the file [build-all.bat](build-all.bat) if you're on *Windows* or [build-all.sh](build-all.sh) if you're on
*Linux*.

**Note:** The **Application File Bot** can only be used by *UNIX based OSs* because of technical constraints. In order
to build this, you need to run [build-filebot.sh](scripts/build-filebot.sh).

## 4. How to Execute Tests

In order to run the **Application File Bot** tests (only on *UNIX based OSs*), you must go to its folder, run the build
file and then the run file

* Example: [build.sh](SCOMP/src/test/2001/scripts/src/test/scripts/ConfigFileReader/build.sh) and
  then [run.sh](SCOMP/src/test/2001/scripts/src/test/scripts/ConfigFileReader/run.sh)

## 5. How to Run

Pick a role (**Backoffice**, **Candidate** or **Customer**) and open the respective **.bat** if you're on *Windows* or *
*.sh* if you're on *Linux*.

**Note 1:** The **Application File Bot** can only be used by *UNIX based OSs* because of technical constraints. In order
to build this, you need to run [run-filebot.sh](scripts/run-filebot.sh).

**Note 2:** The **Follow Up Server** must be running in order for the **Candidate** and **Customer** apps to run. 

## 6. How to Generate PlantUML Diagrams

To generate plantuml diagrams for documentation execute the script (for the moment, only for linux/unix/macos):

    ./generate-plantuml-diagrams.sh



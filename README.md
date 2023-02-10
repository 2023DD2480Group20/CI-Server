# DD2480 Assignment 2: CI Server

### About

This is an assignment for the course DD2480 Software Engineering Fundamentals on KTH. The assignment's goal is to implement a Continuous Integration (CI) server that can automatically build and test code that was committed to a project's GitHub repository. The CI server can then report the results back in the form of setting the commit status. It can only perform these actions on this repository.

### Features

* Can clone a project after a push triggers a GitHub webhook push event (*Note:* the webhook's content-type needs to be "application/json"). The HTTP payload recieved by the server in the push event is stored in a JSON object where each value is easily accessible. It then clones the repo from the branch where the commit was made. There are unit tests that, by using an example payload, assert that the methods for extracting branch name and creating the JSON object are done correctly. Additionally, there is a test that asserts that a repository can be properly cloned by the usage of an example repository. 

* When building the project, it also runs the test automatically. The build process is done by performing a gradle build task on the earlier cloned repo, which makes both the compilation and the testing occur. The result of this process is stored as the commit status.

* The server can notify about the results of the building and testing by setting the commit status. The commit is updated by sending a POST request updating the status of the commit with the result of the earlier building stage. This feature is tested by assuring that a commit's sha is able to be extracted from the JSON object, and by asserting that the commit status can be set on an example commit.


### Build and Run

The project can be built, tested, and run with the commands:

```
gradle build
gradle run
```

To only run the tests, one can run the command:

```
gradle test
```

This requires the system to have **gradle version 7.2** or higher installed. Additionally, the project requires **Java 11** or newer. 
  
The program also requires a file called `auth_token.key` in the  `app` directory.
This file must contain the personal access token of someone who has write access to the repo being tested.
### Credits

* Gradle for building the project
* Junit for the testing
* JSON In Java and Jackson Databind for handling JSON files
* Jetty and ngrok for the server handling
* JGit Core
* Hamcrest

### Contributions
Marcus: My contributions are the following:
* Implementation of notifications on the CI server through commit statuses
* Implemented test for the notifications

Ronan: My contributions are the following:
* Author of Repository Builder
* Co-Author of Build
* Documentation
* Some bug fixing
* Contributed to creating issues, reviewing pull requests and discussing implementations in the Github repo

Pontus: My contributions are the following:
* Handled the incoming HTTP request from the triggering of the webhook
* Created JSON Object that holds the HTTP payload data, created functions that extract relevant data from the JSON and made tests for it
* Wrote most of README file and the Essence paragraph
* Helped out in the Github repo by creating issues, reviewing pull requests etc.


Yagiz: My contributions are the following:
* Set up the build environment using gradle 
* Worked on testing and bug fixing
* Co-Author of Build
* Put the different components together in the server
* Helped with code reviews
### Essence

By assessing our team according to the Essence standard, we can say that we are currently in the state of "Collaborating". The team is past the states of "Seeded" and "Formed" as the team itself has been defined already. There are set ways of working the team follows and the organizing is clear. Now, our team is also able to communicate openly about questions, issues, etc., and is also able to work on separate topics to form a cohesive unit. Furthermore, our team now knows a bit about each other and people can trust their teammates. Some parts of the state of "Performing" are reached; the team does what it is supposed to and can work without external help. But to fully reach it, our team might need to become a bit more flexible to adapt to different situations that might arise. The team should also make sure to not do any work that might go to waste to reach full efficiency.

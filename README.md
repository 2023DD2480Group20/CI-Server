# DD2480 Assignment 2: CI Server

### About

This is an assignment for the course DD2480 Software Engineering Fundamentals on KTH. The assignment's goal is to implement a Continuous Integration (CI) server that can automatically build and test code that was committed to a project's GitHub repository. The CI server can then report the results back in the form of setting the commit status. It can only perform these actions on this repository.

### Features

* Can build a project after being triggered by a GitHub webhook push event (*Note:* the webhook's Content type needs to be "application/json"). It then clones the repo from the branch where the commit was made.

* When building the project, it also runs the test automatically.

* The server can notify about the results of the building and testing by setting the commit status.


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

### Credits

* Gradle for building the project
* Junit for the testing
* JSON In Java and Jackson Databind for handling JSON files
* Jetty and ngrok for the server handling
* JGit Core
* Hamcrest

### Contributions



### Essence

By assessing our team according to the Essence standard, we can say that we are currently in the state of "Collaborating". The team is past the states of "Seeded" and "Formed" as the team itself has been defined already. There are set ways of working the team follows and the organizing is clear. Now, our team is also able to communicate openly about questions, issues, etc., and is also able to work on separate topics to form a cohesive unit. Furthermore, our team now knows a bit about each other and people can trust their teammates. Some parts of the state of "Performing" are reached; the team does what it is supposed to and can work without external help. But to fully reach it, our team might need to become a bit more flexible to adapt to different situations that might arise. The team should also make sure to not do any work that might go to waste to reach full efficiency.

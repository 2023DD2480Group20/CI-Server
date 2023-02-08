# DD2480 Assignment 2: CI-Server

### About

This is an assignment for the course DD2480 Software Engineering Fundamentals on KTH. This assignment's goal is to implement a Continuous Integration (CI) server that has the ability to automatically test a build and test code that was commited to a project's Github repository. The CI-server can then report the results back in the form of setting the commit status. It can only perform these actions on this repository.

### Features

* Can build a project after being triggered by a Github webhook push event (Note: the webhook Content type needs to be "application/json"). It then clones the repo from the branch where the commit was made.

* When builing the project, it also runs the test automatically.

* The server can notify about the results of the building and testing by setting the commit status.


### Build and Run

The project can be built and run with the commands (which also runs the test):

```
gradle build
gradle run
```

In order to only run the tests, one can run the command:
```
gradle test
```

This requires your system to have a valid gradle installation.

### Credits

* Gradle for building the project
* Junit for the testing
* JSON In Java and Jackson Databind for handling JSON files
* Jetty and ngrok for the server handling
* JGit Core
* Hamcrest

### Contributions

### Essence
package ci.server;


import java.io.*;

import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.*;
/**
 * Find build config file in root directory and run commands
 * 
 */
public final class Build{
    private final File buildFolder;

    public Build(File buildFolder){
        this.buildFolder = buildFolder;
    }

    //TODO should return the results needed by Notify
    public void build(){
        ProjectConnection connection = GradleConnector.newConnector()
                .forProjectDirectory(buildFolder)
                .connect();

    }

}
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
    public final FileOutputStream stdErr = new FileOutputStream("buildStderr.txt");
    public final FileOutputStream stdOut = new FileOutputStream("buildStdout.txt");

    public Build(File buildFolder) throws FileNotFoundException {
        this.buildFolder = buildFolder;
    }

    //TODO should return the results needed by Notify
    public CommitStatus build(){
        CommitStatus result = CommitStatus.SUCCESS;

        try(ProjectConnection connection = GradleConnector.newConnector().forProjectDirectory(buildFolder).connect()) {

            BuildProgressListener listener = new BuildProgressListener();

            connection.newBuild()
                    .forTasks("build")
                    .addProgressListener(listener)
                    .setStandardError(stdErr)
                    .setStandardOutput(stdOut)
                    .run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BuildException e) {
            result = CommitStatus.FAIL;
        } finally {
            try {
                stdErr.close();
                stdOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
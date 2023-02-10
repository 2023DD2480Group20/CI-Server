package ci.server;

import org.gradle.tooling.GradleConnectionException;
import org.gradle.tooling.ProgressEvent;
import org.gradle.tooling.ProgressListener;

import java.io.FileWriter;
import java.io.IOException;

public class BuildProgressListener implements ProgressListener{

    FileWriter logFile = new FileWriter("progressListener.log");
    boolean buildSuccessful = false;

    public BuildProgressListener() throws IOException {
    }

    @Override
    public void statusChanged(ProgressEvent event) {
        System.out.println(event.getDescription());
        try {
            logFile.write(event.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

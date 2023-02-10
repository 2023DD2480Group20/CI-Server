package ci.server;

import org.gradle.tooling.GradleConnectionException;
import org.gradle.tooling.ProgressEvent;
import org.gradle.tooling.ProgressListener;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A listener which is notified as some long running operation makes progress.
 */
public class BuildProgressListener implements ProgressListener{

    FileWriter logFile = new FileWriter("progressListener.log");
    boolean buildSuccessful = false;

    public BuildProgressListener() throws IOException {
    }

    /**
     * Called when the progress status changes.
     * @param event An event describing the status change
     */
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

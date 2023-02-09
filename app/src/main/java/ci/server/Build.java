package ci.server;


import java.io.*;
import java.util.*;

/**
 * Find build config file in root directory and run commands
 * 
 */
public final class Build{
    private final File local_directory;

    public Build(File local_directory){
        this.local_directory = local_directory;
    }

    //TODO should return the results needed by Notify
    public void build(){
        try {
            // In the continuous server, cd to the directory of the local file and build the project
            String command = "cd " + local_directory.getAbsolutePath();
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();

            // wait for the process to finish
            int exitCode = process.waitFor();
            if(exitCode != 0){
                return;
            }

            command = "gradle build";
            builder = new ProcessBuilder(command);
            process = builder.start();

            exitCode = process.waitFor();
            if(exitCode != 0){
                return;
            }

            // Read the output of the command line
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            FileWriter writer = new FileWriter("result.txt");
            String line;

            // Write the output of the command line to the result.txt
            while ((line = reader.readLine()) != null){
                writer.write(line + System.lineSeparator());
            }
            writer.close();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
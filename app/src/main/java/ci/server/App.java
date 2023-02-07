package ci.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args){
        try {
        ContinuousIntegrationServer.startServer();
        } catch (Exception e) {
        e.printStackTrace();
        }
    /*try{
        String s;
        Process p;
        try {
            // The -B flag creates the branch if it doesn't already exist, and resets it if it exists
            p = Runtime.getRuntime().exec("git checkout -B assessment");
            BufferedReader brOut = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader brError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while ((s = brOut.readLine()) != null)
                System.out.println("line: " + s);

            while ((s = brError.readLine()) != null)
                System.out.println("Eline: " + s);

            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}

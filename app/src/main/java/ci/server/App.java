package ci.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Run the CI server
 */
public class App {

    public static void main(String[] args) {
        try {
            ContinuousIntegrationServer.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

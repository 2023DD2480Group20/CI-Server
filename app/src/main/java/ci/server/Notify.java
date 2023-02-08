package ci.server;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * Notify
 * Commit status: the CI server sets the commit status or
 * Email: the CI server sends an email to the project member about the build result.
 *
 */
public class Notify {
    static String getStatus (String owner, String repo, String ref) {
        try {
            HttpRequest getRequest = HttpRequest.newBuilder().uri(new URI("https://api.github.com/repos/" + owner + "/" + repo +"/commits/" + ref + "/status")).build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());

            return getResponse.body(); 
        } catch (Exception e) {
            return "Error: " + e;
        } 
    }

    //Used to change a commit based on the sha of the commit to the state parameter
    //(failure, error, pending, success)
    static String changeStatus (String owner, String repo, String sha, String state) {
        try {
            String json = "{\"state\":\"" + state + "\"}";
            
            //Authentication token is required to change the commit status
            String authToken = "";

            HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI("https://api.github.com/repos/" + owner + "/" + repo + "/statuses/" + sha)).header("Authorization", "Bearer " + authToken).POST(BodyPublishers.ofString(json)).build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> getResponse = httpClient.send(postRequest, BodyHandlers.ofString());

            return getResponse.body(); 
        } catch (Exception e) {
            return "Error: " + e;
        } 
    }

    public static void main(String[] args) {

        String own = "2023DD2480Group20";
        String rep = "Ci-Server";
        String ref = "79e52d36ead03613d4c4c3e2df1d41e1663cd3a3";

        System.out.println(getStatus(own, rep, ref) + "\n\n");
        System.out.println(changeStatus(own, rep, ref, "success")  + "\n\n");
        System.out.println(getStatus(own, rep, ref));
    }
}
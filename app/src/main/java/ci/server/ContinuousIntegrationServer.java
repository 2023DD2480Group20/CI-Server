package ci.server;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import org.json.*;

/**
 * Skeleton of a ContinuousIntegrationServer which acts as webhook
 * See the Jetty documentation for API documentation of those classes.
 * 
 * FEATURES:
 * 
 * core CI feature #1 - compilation: the CI server supports compiling the group
 * project,
 * a static syntax check is to be performed for languages without compiler.
 * Compilation is
 * triggered as webhook, the CI server compiles the branch where the change has
 * been made,
 * as specified in the HTTP payload.
 * 
 * core CI feature #2 - testing: the CI server supports executing the automated
 * tests of
 * the group project. Testing is triggered as webhook, on the branch where the
 * change has
 * been made, as specified in the HTTP payload.
 * 
 * core CI feature #3 - notification): the CI server supports notification of CI
 * results.
 * At least one notification mechanism of the following list is implemented:
 * 
 * Commit status: the CI server sets the commit status
 * 
 * Email: the CI server sends an email to the project member about the build
 * result.
 * 
 * 
 * Technical documentation:
 * 
 * 1- Run the CI server on ngrok and add the webhook on github
 * 2- Try to commit/push to github repo
 * 3- The CI server receives the webhook notifications and then launch the
 * automated build
 * 4- Notifiy the results
 * 
 */
public class ContinuousIntegrationServer extends AbstractHandler {
    // String that holds the branch name
    String branch = "";
    // String that holds the commit's sha
    static String sha = "";

    final String contentTypeError = "Webhook content-type not application/json, change it to the correct type in the webhook's settings!";

    // Object to hold the json fields from the webhook
    JSONObject webhookData;

    /**
     * called to handle the webhook
     *
     * @param target
     * @param baseRequest
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void handle(String target,
            Request baseRequest,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        System.out.println(target);

        // here you do all the continuous integration tasks
        //
        // 1. Clone repo
        // 2. Find build config file in root directory
        // 3. Parse and run commands
        // 4. Notify github of result
        // 5. Update database

        try {
            //Webhooks can have different types, ping for the first connection and then push for commit events

            String eventType = request.getHeader("X-GitHub-Event");

            //Gets the webhook's content-type setting
            String contentType = request.getHeader("content-type");

            if(contentType.equals("application/json")){
                BufferedReader reader = request.getReader();
                webhookData = getJSON(reader);
                branch = extractBranchName(webhookData, eventType);
                sha = extractCommitSha(webhookData, eventType);
                System.out.println("The extracted branch name is: " + branch);
                System.out.println("The extracted sha is: " + sha);

            } else{
                //If content-type is wrong, send error message in response and print in server's terminal¨
                if(eventType.equals("push")){
                    response.getWriter().println(contentTypeError);
                    System.out.println(contentTypeError);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommitStatus cs = CommitStatus.SUCCESS;
        try {
            // Repo URL should perhaps not be given hardcoded, we can try extracting it from the json.
            RepositoryBuilder clone = new RepositoryBuilder("https://github.com/2023DD2480Group20/CI-Server",
                    "refs/heads/" + branch, "temporary");
            cs = new Build(clone.local_directory).build();
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("The evaluation for this commit is: " + commmitStatusToString(cs));

        notifyResults(commmitStatusToString(cs));


        response.getWriter().println("CI job done");
        response.getWriter().println("name: " + branch);
        response.getWriter().println("sha: " + sha);
    }

    /**
     * Creates a JSONObject from a BufferedReader
     * @param r BufferedReader to extract the JSONObject from
     * @return JSONObject with data from Reader, null if it fails
     */
    public JSONObject getJSON(BufferedReader r) {
        try {
            String line = r.readLine();
            System.out.println(line);
            return new JSONObject(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Extract a branch name from JSONObject holding github webhook data
     * @param json The JSONObject holding the data
     * @param type The event type, usually "push" or "ping"
     * @return The name of the branch in String format
     */
    public static String extractBranchName(JSONObject json, String type) {
        if(type.equals("push")){
            String temp = json.getString("ref");
            return temp.split("/")[2];
        } else{
            return "";
        }  
    }

    /**
     * Extract a the commit sha from JSONObject holding github webhook data
     * @param json The JSONObject holding the data
     * @param type The event type, usually "push" or "ping"
     * @return The commit sha in String format
     */
    public static String extractCommitSha(JSONObject json, String type) {
        if(type.equals("push")){
            return json.getString("after");
        } else{
            return "";
        }  
    }


    public static void notifyResults (String res) {
        Notify.changeStatus(sha, res);
    }

    /**
     * Convert the commit status to string
     * @param commitStatus the commit status
     * @return the string conversion
     */
    public static String commmitStatusToString(CommitStatus commitStatus){
        switch (commitStatus){
            case SUCCESS:
                return "success";
            case FAILURE:
                return "failure";
            case ERROR:
                return "error";
            case PENDING:
                return "pending";
            default:
                return "INVALID_COMMIT_STATUS";
        }
    }


    /**
     * Used to start the CI server in command line
     * @throws Exception
     */
    public static void startServer() throws Exception {
        Server server = new Server(8080);
        server.setHandler(new ContinuousIntegrationServer());
        server.start();
        server.join();
    }
}

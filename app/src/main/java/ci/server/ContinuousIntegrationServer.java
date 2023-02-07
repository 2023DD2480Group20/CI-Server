package ci.server;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 
 Skeleton of a ContinuousIntegrationServer which acts as webhook
 See the Jetty documentation for API documentation of those classes.

FEATURES:

core CI feature #1 - compilation: the CI server supports compiling the group project, 
a static syntax check is to be performed for languages without compiler. Compilation is 
triggered as webhook, the CI server compiles the branch where the change has been made, 
as specified in the HTTP payload.

core CI feature #2 - testing: the CI server supports executing the automated tests of 
the group project. Testing is triggered as webhook, on the branch where the change has 
been made, as specified in the HTTP payload.

core CI feature #3 - notification): the CI server supports notification of CI results. 
At least one notification mechanism of the following list is implemented:

    Commit status: the CI server sets the commit status 

    Email: the CI server sends an email to the project member about the build result.


Technical documentation:

 1- Run the CI server on ngrok and add the webhook on github
 2- Try to commit/push to github repo
 3- The CI server receives the webhook notifications and then launch the automated build
 4- Notifiy the results
 
*/
public class ContinuousIntegrationServer extends AbstractHandler
{
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
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



        response.getWriter().println("CI job done");
    }
 
    // used to start the CI server in command line
    public static void startServer() throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new ContinuousIntegrationServer()); 
        server.start();
        server.join();
    }
}

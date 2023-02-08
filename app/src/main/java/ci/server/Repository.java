package ci.server;


/**
 * All functions related to Git Repository (cloning and updating)
 *
 */
public final class Repository{


    public void clone(){
        String repoUrl = "https://github.com/onecompiler/tutorials.git";
        String cloneDirectoryPath = "/path/to/directory/"; // Ex.in windows c:\\gitProjects\SpringBootMongoDbCRUD\
        try {
            System.out.println("Cloning "+repoUrl+" into "+repoUrl);
            Git.cloneRepository()
                .setURI(repoUrl)
                .setDirectory(Paths.get(cloneDirectoryPath).toFile())
                .call();
            System.out.println("Completed Cloning");
        } catch (GitAPIException e) {
            System.out.println("Exception occurred while cloning repo");
            e.printStackTrace();
        }
    }
    

}


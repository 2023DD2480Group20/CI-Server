package main.java.ci.server;


import java.nio.file.*;


/**
 * All functions related to Git Repository (cloning and updating)
 *
 */
public final class Repository{
    private final String cloneDirectoryPath = "/path/to/directory/"; // Ex.in windows c:\\gitProjects\SpringBootMongoDbCRUD\
    private final String repoUrl;
    private final String branchRef;

    /**
     * Class constructor
     *
     * @param repoUrl url to clone the github repository from
     * @param branchRef the branch where the change has been made
     */
    public Repository(String repoUrl, String branchRef){
        this.repoUrl = repoUrl;
        this.branchRef = branchRef;
        git_clone_repository();
    }

    public void git_clone_repository(){
        Git git_repository;
        try {
            System.out.println("Cloning "+repoUrl+" into "+repoUrl);
            git_repository = Git.cloneRepository()
                                .setURI(repoUrl)
                                .setDirectory(Paths.get(cloneDirectoryPath).toFile())
                                .setBranch(branchRef)
                                .call();
            System.out.println("Completed Cloning");
        } catch (GitAPIException e) {
            System.out.println("Exception occurred while cloning repo");
            e.printStackTrace();
        }

        Repository repository = git_repository.get
    }
    

}


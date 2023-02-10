package ci.server;


import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.*;
import org.eclipse.jgit.lib.*;

import java.io.*;
import java.nio.file.*;

import org.apache.commons.io.FileUtils;

/**
 * All functions related to Git RepositoryBuilder (cloning and updating)
 *
 */
public final class RepositoryBuilder {
    public final Repository repository;
    public final File local_directory;


    /**
     * Class constructor
     *
     * @param repoUrl url to clone the github repository from
     * @param branchRef the branch where the change has been made
     * @param ID ID to characterize the continuous integration
     */
    public RepositoryBuilder(String repoUrl, String branchRef, String ID){
        File cloneDirectoryPath = new File("./" + ID);
        if(cloneDirectoryPath.length() != 0){
            deleteClone(cloneDirectoryPath);
        }
        this.repository = git_clone_repository(repoUrl, branchRef, cloneDirectoryPath);
        //TODO not sure if I should getDirectory or other
        assert repository != null;
        this.local_directory = cloneDirectoryPath;
    }

    /**
     * clone the repo
     *
     * @param repoUrl
     * @param branchRef
     * @param cloneDirectoryPath
     * @return the cloned repository
     */
    private Repository git_clone_repository(String repoUrl, String branchRef, File cloneDirectoryPath){
        Git git_repository;
        try {
            System.out.println("Cloning "+repoUrl+" into "+repoUrl);
            git_repository = Git.cloneRepository()
                                .setURI(repoUrl)
                                .setDirectory(cloneDirectoryPath)
                                .setBranch(branchRef)
                                .call();
            System.out.println("Completed Cloning");
        } catch (GitAPIException e) {
            System.out.println("Exception occurred while cloning repo");
            e.printStackTrace();
            return null;
        }
        return git_repository.getRepository();
    }

    public void deleteClone(){
        if(this.repository != null){
            try{
                FileUtils.deleteDirectory(this.repository.getDirectory());
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void deleteClone(File file){
        if(file != null){
            try{
                FileUtils.deleteDirectory(file);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}


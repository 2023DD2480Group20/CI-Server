package ci.server;

import org.eclipse.jgit.lib.Repository;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RepositoryBuilderTest {
    @Test void cloneBranchTest(){
        Repository repo = new RepositoryBuilder("https://github.com/YavizGuldalf/TestRepo",
                "refs/heads/newbranch", "cloneTest").repository;
        try{
            assertThat(repo.getBranch(), equalTo("newbranch"));
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}

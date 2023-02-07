/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ci.server;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;


class AppTest {
    // Tests whether the assessment branch has been created and checked out to after the app is run
    @Test void appCreatesAssessmentBranch() {
        App.main(new String[]{});

        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("git branch");
            BufferedReader brOut = new BufferedReader(new InputStreamReader(p.getInputStream()));
            ArrayList<String> outputs = new ArrayList<>();

            while ((s = brOut.readLine()) != null)
                outputs.add(s.trim());
            p.waitFor();
            p.destroy();

            assertThat(outputs, hasItem("* assessment"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

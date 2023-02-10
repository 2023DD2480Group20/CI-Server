package ci.server;

import DTO.TunnelsDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.C;
import org.eclipse.jetty.util.log.Log;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;

import org.json.*;

public class NotifyTest {
    @Test void notifyTest(){
        String sha = "79e52d36ead03613d4c4c3e2df1d41e1663cd3a3";
        String test = "";

        File auth_token = new File("auth_token.key");

        // The below is an extremely unsafe thing to do as someone malicious could just push a commit that will
        // expose the auth token used by the CI server. It is only here for demonstration purposes.
        if(!auth_token.isFile()){
            File real_auth_token = new File("../../auth_token.key");
            try {
                FileWriter fw = new FileWriter(auth_token);
                Scanner sc = new Scanner(real_auth_token);
                fw.write(sc.nextLine());
                sc.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            JSONObject json = new JSONObject(Notify.changeStatus(sha,"pending"));
            test = json.getString("state");
        } catch(Exception e) {
            e.printStackTrace();
        }

        assertThat(test, equalTo("pending"));
    }
}
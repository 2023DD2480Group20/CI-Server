package ci.server;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.json.*;

public class ContinuousIntegrationServerTest {
    @Test void jsonNotNullTest(){
        BufferedReader read;
        JSONObject json = null;
        try{
          //  payload = new String(Files.readAllBytes(Paths.get("examplePayload.json")));
            read = new BufferedReader(new FileReader("examplePayload.json"));
            json = new JSONObject(read);
        } catch(Exception e) {
            e.printStackTrace();
        }

        assertNotNull(json);
    }

    @Test void branchNameTest(){
        String payload = "";
        JSONObject json = null;
        try{
            payload = new String(Files.readAllBytes(Paths.get("examplePayload.json")));
            json = new JSONObject(payload);
        } catch(Exception e) {
            e.printStackTrace();
        }

        assertThat(ContinuousIntegrationServer.extractBranchName(json,"push"), equalTo("readbranch"));
    }

    @Test void branchNamePingEventTest(){
        String payload = "";
        JSONObject json = null;
        try{
            payload = new String(Files.readAllBytes(Paths.get("examplePayload.json")));
            json = new JSONObject(payload);
        } catch(Exception e) {
            e.printStackTrace();
        }

        assertThat(ContinuousIntegrationServer.extractBranchName(json,"ping"), equalTo(""));
    }

    @Test void extractCommitShaTest(){
        String payload = "";
        JSONObject json = null;
        try{
            payload = new String(Files.readAllBytes(Paths.get("examplePayload.json")));
            json = new JSONObject(payload);
        } catch(Exception e) {
            e.printStackTrace();
        }

        assertThat(ContinuousIntegrationServer.extractCommitSha(json,"push"), equalTo("22a43014e5d79a491990291a201d0f2afcbf61a5"));
    }
}

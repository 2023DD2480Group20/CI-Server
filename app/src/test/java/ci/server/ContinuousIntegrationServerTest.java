package ci.server;

import DTO.TunnelsDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

}

package ci.server;

import DTO.TunnelsDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.C;
import org.eclipse.jetty.util.log.Log;
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

public class NotifyTest {
//    @Test void notifyTest(){
//        String sha = "79e52d36ead03613d4c4c3e2df1d41e1663cd3a3";
//        String test = "";
//
//
//        try{
//            JSONObject json = new JSONObject(Notify.changeStatus(sha,"pending"));
//            test = json.getString("state");
//            //test = Notify.changeStatus(sha,"pending");
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        assertThat(test, equalTo("pending"));
//    }
}
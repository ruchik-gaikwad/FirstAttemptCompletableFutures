package io.stackroute;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.stackroute.model.GitUserRepos;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.http.HttpClient;
import java.util.concurrent.CompletableFuture;

public class ReusableFutures {

    public static CompletableFuture getPublicRepos(String username) {
        return  CompletableFuture.supplyAsync(() -> {
            if (username != null ) {
                return CompletableFuture.completedFuture("Here we need to get all the repos !");
            } else {
                return CompletableFuture.completedFuture("No UserName Provided");
            }

        });
    }

   public static final CompletableFuture<?> repos = CompletableFuture.supplyAsync(() -> {
        System.out.println("Is this running at all ??");
        String url = "http://api.github.com/users/ruchik-gaikwad/repos";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
            System.out.println(response.getEntity());
            HttpEntity dataEntity = response.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(dataEntity.getContent()));
            String data = br.readLine();
            GitUserRepos[] allRepos = mapper.readValue(data, GitUserRepos[].class);
            CompletableFuture.completedStage(allRepos);
            return allRepos;
        }
        catch (Exception e) {
            System.out.println(e);
            return "sdf";
        }

//        return "some thing";

    });

    public static final CompletableFuture<?> getAllRepos (String username) {
        return CompletableFuture.supplyAsync(() -> {
            String url = "http://api.github.com/users/" + username + "/repos";

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                HttpEntity dataEntity = response.getEntity();
                BufferedReader br = new BufferedReader(new InputStreamReader(dataEntity.getContent()));
                String data = br.readLine();
                GitUserRepos[] allRepos = mapper.readValue(data, GitUserRepos[].class);
                CompletableFuture.completedStage(allRepos);
                return allRepos;
            }
            catch (Exception e) {
                System.out.println(e);
                return "sdf";
            }

        });
    }
}

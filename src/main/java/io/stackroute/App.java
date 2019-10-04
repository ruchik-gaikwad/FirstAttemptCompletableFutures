package io.stackroute;

import io.stackroute.ReusableFutures;
import io.stackroute.model.GitUserRepos;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ExecutionException, InterruptedException {

//        ArrayList<CompletableFuture> futures = new ArrayList<>();
//        ReusableFutures.repos.thenApply((e) -> {
//            System.out.println(e);
//            System.out.println("Lets check the Sequence !");
//            return "??";
//        });

        ArrayList<String> allUserNames = new ArrayList<>();

        allUserNames.add("ruchik-gaikwad");
        allUserNames.add("nish97");
        allUserNames.add("chhavikishore");

        // This is Creating the List of Completable Futures
        List<CompletableFuture> futures = allUserNames.stream()
                .map(username -> ReusableFutures.getAllRepos(username))
                .collect(Collectors.toList());


        //
        CompletableFuture allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        CompletableFuture extractAllContent = allFutures.thenApply(e -> {
            return futures.stream()
                    .map(k -> k.join())
                    .collect(Collectors.toList());
        });

        extractAllContent.thenAccept(e -> {
            System.out.println(e);
            List data = (List) e;
            data.forEach(j -> {
                GitUserRepos[] eachUserRepos = (GitUserRepos[]) j;
                for (GitUserRepos eachUserRepo : eachUserRepos) {
                    System.out.println(eachUserRepo.getUrl());
                }
            });
        }).join();


        // TODO: This was the attempt Shorthen the above writen code, and to return a List of all the repos instead of Completable Future
//        long startTime = System.nanoTime();
//        CompletableFuture result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
//                .supplyAsync(() -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
//                .thenApply(e -> e.stream().map(eachUseRepoList -> eachUseRepoList).map(eachRepo -> (GitUserRepos) eachRepo));
//        result.join(); // FIXME: Thread is sleeping
//        long endTime = System.nanoTime();
//        long duration = ((endTime - startTime)/1000000);
//        System.out.println(duration);


    }
}

package ru.kovalev.datingApp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class DatingAppClientRunner {
    public static void main(String[] args) throws IOException, InterruptedException {

        try(HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()){

            HttpRequest request = HttpRequest.newBuilder(URI.create("http://yandex.ru"))
                    .setHeader("My-Token", "4354-5674-0530-2859")
                    .POST(HttpRequest.BodyPublishers.ofString("message for post"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, List<String>> map = httpResponse.headers().map();

            System.out.println(httpResponse.statusCode());
            System.out.println();
            System.out.println(map);
            System.out.println(httpResponse.body());

        }

    }
}
package ru.kovalev.datingApp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatingAppHttpServer {

    private ExecutorService executorService;

    public DatingAppHttpServer(int poolSize){
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(8080)){
            while (true){
                Socket connection = serverSocket.accept();
                System.out.println("---- Client connect ----");
                executorService.submit(()->processConnection(connection));
/* Сервер "слушает, как только к нему кто-то подключается - закидываем его в отдельный поток, который берем из executorService" */
            }
        }
    }

    private void processConnection(Socket connection){
        try(connection;
            BufferedReader rqReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream rsStream = new DataOutputStream(connection.getOutputStream())
        ) {
            while (!rqReader.ready());

            while (rqReader.ready()){
                System.out.println(rqReader.readLine());
            }

            byte[] body = "Hi from Server".getBytes();
            byte[] startString = "HTTP/1.1 200 OK\n".getBytes();
            byte[] headers = "Content-type: text/plain\nContent-Length: %s\n\n".formatted(body.length).getBytes();

            rsStream.write(startString);
            rsStream.write(headers);
            rsStream.write(body);
            System.out.println("---- Client disconnect ----");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

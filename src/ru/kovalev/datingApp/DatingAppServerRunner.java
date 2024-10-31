package ru.kovalev.datingApp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class DatingAppServerRunner {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080); /* "Розетка" для соединения с клиентом (для TCP-сервера). */
                                                                         /* указываем тольеко порт, хост - компьютер */
             Socket socket = serverSocket.accept(); /* "слушаем, чтобы кто-то подключился" */
             DataOutputStream rsStream = new DataOutputStream(socket.getOutputStream()); // response
             DataInputStream rqStream = new DataInputStream(socket.getInputStream()); // request
             Scanner scanner = new Scanner(System.in);

        ) {
            String request = rqStream.readUTF();

            while (!"stop".equals(request)){
                System.out.println("Client request " + request);
                String response = scanner.nextLine();

                rsStream.writeUTF(response);
                request = rqStream.readUTF();
            }
        }
    }
}

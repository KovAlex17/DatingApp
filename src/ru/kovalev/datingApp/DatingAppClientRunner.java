package ru.kovalev.datingApp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DatingAppClientRunner {
    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket("localhost", 8080);  // "Розетка", для соединения с сервером (нужна для клиента). 80 - стандартный порт HTTP
             DataOutputStream rqStream = new DataOutputStream(socket.getOutputStream()); // request
             DataInputStream rsStream = new DataInputStream(socket.getInputStream()); // response
             Scanner scanner = new Scanner(System.in)){
            while (scanner.hasNextLine()){
                String request = scanner.nextLine();
                rqStream.writeUTF(request);
                String response = rsStream.readUTF();  //byte [] bytes = rsStream.readAllBytes();
                System.out.println(response);
            }

        }

    }
}
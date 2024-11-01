package ru.kovalev.datingApp;

import ru.kovalev.datingApp.controller.ProfileController;
import ru.kovalev.datingApp.dao.ProfileDao;
import ru.kovalev.datingApp.service.ProfileService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static ru.kovalev.datingApp.model.Commands.*;

public class DatingAppServerRunner {
    public static void main(String[] args) throws IOException {

        ProfileController controller = new ProfileController(new ProfileService(new ProfileDao()));

        try (ServerSocket serverSocket = new ServerSocket(8080); /* "Розетка" для соединения с клиентом (для TCP-сервера). */
                                                                         /* указываем тольеко порт, хост - компьютер */
             Socket socket = serverSocket.accept(); /* "слушаем, чтобы кто-то подключился" */
             DataOutputStream rsStream = new DataOutputStream(socket.getOutputStream()); // response
             DataInputStream rqStream = new DataInputStream(socket.getInputStream()); // request

        ) {
            String request = rqStream.readUTF();

            while (!"stop".equals(request)){
                String response;
                if (request.startsWith(SAVE.getPrefix())){
                    response = controller.save(request.split(SAVE.getPrefix())[1]);
                } else if (request.startsWith(FIND_BY_ID.getPrefix())){
                    response = controller.findById(request.split(FIND_BY_ID.getPrefix())[1]);
                } else if (request.startsWith(FIND_ALL.getPrefix())){
                    response = controller.findAll();
                } else if (request.startsWith(UPDATE.getPrefix())){
                    response = controller.update(request.split(UPDATE.getPrefix())[1]);
                } else if (request.startsWith(DELETE.getPrefix())){
                    response = controller.delete(request.split(DELETE.getPrefix())[1]);
                } else {
                    response = "Unsupported command";
                }

                System.out.println("Client request " + request);
                rsStream.writeUTF(response);
                request = rqStream.readUTF();
            }
        }
    }
}

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
        DatingAppHttpServer server = new DatingAppHttpServer(5);
        server.start();

    }
}

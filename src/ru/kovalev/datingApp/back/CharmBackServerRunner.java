package ru.kovalev.datingApp.back;

import ru.kovalev.datingApp.back.controller.ProfileController;
import ru.kovalev.datingApp.back.dao.ProfileDao;
import ru.kovalev.datingApp.back.service.ProfileService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static ru.kovalev.datingApp.back.model.Commands.*;


public class CharmBackServerRunner {
    public static void main(String[] args) throws IOException {
        ProfileController controller = new ProfileController(new ProfileService(new ProfileDao()));

        CharmHttpServer server = new CharmHttpServer(8080, 5, controller);
        server.run();


    }
}
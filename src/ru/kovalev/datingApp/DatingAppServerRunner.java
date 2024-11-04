package ru.kovalev.datingApp;

import ru.kovalev.datingApp.controller.LikeController;
import ru.kovalev.datingApp.controller.ProfileController;
import ru.kovalev.datingApp.dao.ProfileDao;
import ru.kovalev.datingApp.service.ProfileService;




public class DatingAppServerRunner {
    public static void main(String[] args) {

        ProfileController profileController = new ProfileController(new ProfileService(new ProfileDao()));
        LikeController likeController = new LikeController();

        DatingAppHttpServer server = new DatingAppHttpServer(8080, 5, profileController, likeController);
        server.start();

    }
}

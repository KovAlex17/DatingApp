package ru.kovalev.datingApp.back.controller;

import ru.kovalev.datingApp.back.service.ProfileService;

public class ProfileController {
    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }


}

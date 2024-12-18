package ru.kovalev.datingApp.controller;

import ru.kovalev.datingApp.model.Profile;
import ru.kovalev.datingApp.service.ProfileService;

import java.util.List;
import java.util.Optional;
import static java.lang.Long.parseLong;

public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    public String work(String request){
        return "s";
    }

    public String save(String save) {
        String[] params = save.split(",");
        if (params.length < 4) return "Bad request: need 4 parameters for save profile.";

        Profile profile = new Profile();
        profile.setEmail(params[0]);
        profile.setName(params[1]);
        profile.setSurname(params[2]);
        profile.setAbout(params[3]);

        return service.save(profile).toString();
    }

    public Optional<Profile> findById(Long id){
        return service.findById(id);
    }

    public List<Profile> findAll(){return service.findAll();}

    public String update(String request){
        String[] strings = request.split(",");
        if (strings.length != 5) return "Bad request: need 5 parameters to update profile.";

        long id;
        try {
            id = parseLong(strings[0]);
        } catch (NumberFormatException e){
            return "Bad request: can't parse string [" + strings[0] + "] to long.";
        }

        Profile profile = new Profile();
        profile.setEmail(strings[1]);
        profile.setName(strings[2]);
        profile.setSurname(strings[3]);
        profile.setAbout(strings[4]);

        service.update(profile);

        return "Update success.";
    }

    public String delete(String request){
        String[] strings = request.split(",");
        if (strings.length != 1) return "Bad request: need 1 number parameter.";

        long id;
        try {
            id = parseLong(strings[0]);
        } catch (NumberFormatException e){
            return "Bad request: can't parse string [" + strings[0] + "] to long.";
        }

        boolean result = service.delete(id);

        if (!result) return "Not found";

        return "Delete success.";
    }
}

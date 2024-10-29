package ru.kovalev.datingApp.service;

import ru.kovalev.datingApp.dao.ProfileDao;
import ru.kovalev.datingApp.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfileService {

    private final ProfileDao dao;

    public ProfileService(ProfileDao dao) {
        this.dao = dao;
    }

    public Profile save(Profile profile){
        return dao.save(profile);
    }

    public Optional<Profile> findById(Long id){
        if (id == null) return  Optional.empty();
        return dao.findById(id);
    }

    //TODO delete, update, findAll

    public List<Profile> findAll(){
        return new ArrayList<>(dao.findAll());
    }

    void update(Profile profile){
        dao.update(profile);
    }

    boolean delete(Long id){
        if(id == null) return false;
        return dao.delete(id);
    }
}

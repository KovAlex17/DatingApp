package ru.kovalev.datingApp.dao;

import ru.kovalev.datingApp.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileDao {
    private final ConcurrentHashMap<Long, Profile> storage;
    private final AtomicLong idStorage;

    public ProfileDao() {
        this.storage = new ConcurrentHashMap<Long, Profile>();
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setEmail("san@yandex.ru");
        profile.setName("Alex");
        profile.setSurname("Ivanov");
        profile.setAbout("I'm a man");
        this.storage.put(1L, profile);
        this.idStorage = new AtomicLong(1L);
    }

    public Profile save (Profile profile){
        long id = idStorage.incrementAndGet();
        profile.setId(id);
        storage.put(id, profile);

        return profile;
    }

    public Optional<Profile> findById(Long id){
        return Optional.ofNullable(storage.get(id));
    }

    // delete, update,findAll

    public List<Profile> findAll(){
        return new ArrayList<>(storage.values());
    }

    public void update(Profile profile){
        Long id = profile.getId();
        if (profile == null) return;
        storage.put(id, profile);
    }

    public boolean delete(Long id){
        return storage.remove(id) != null;
    }

}

package ru.kovalev.datingApp.back.dao;

import ru.kovalev.datingApp.back.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileDao {

    private final ConcurrentHashMap<Long, Profile> storage;
    private final AtomicLong idStorage;


    public ProfileDao() {
        this.storage = new ConcurrentHashMap<>();
        this.idStorage = new AtomicLong();
    }

    public Profile save(Profile profile) {
        long id = idStorage.getAndIncrement();
        profile.setId(id);
        storage.put(id, profile);
        return profile;
    }

    public Optional<Profile> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Profile> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(Profile profile) {
        Long id = profile.getId();
        if (id == null) return;
        storage.put(id, profile);
    }

    public boolean delete(Long id) {
        return storage.remove(id) != null;
    }
}

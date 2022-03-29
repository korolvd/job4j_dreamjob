package ru.job4j.dream.service;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.City;
import ru.job4j.dream.store.CityDBStore;

import java.util.List;

@Service
@ThreadSafe
public class CityService {

    private final CityDBStore store;

    public CityService(CityDBStore store) {
        this.store = store;
    }

    public List<City> getAllCities() {
        return store.findAll();
    }

    public City findById(int id) {
        return store.findById(id);
    }
}

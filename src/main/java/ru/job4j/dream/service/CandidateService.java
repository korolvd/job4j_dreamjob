package ru.job4j.dream.service;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.CandidateStore;
import ru.job4j.dream.store.PostStore;

import java.time.LocalDate;
import java.util.Collection;

public class CandidateService {
    private static final CandidateService INST = new CandidateService();
    private final CandidateStore store = CandidateStore.instOf();

    public static CandidateService instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public void add(Candidate candidate) {
        candidate.setCreated(LocalDate.now());
        store.add(candidate);
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }
}

package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.CandidateBDStore;
import ru.job4j.dream.store.CandidateStore;

import java.time.LocalDate;
import java.util.Collection;

@Service
@ThreadSafe
public class CandidateService {
    private final CandidateBDStore store;

    public CandidateService(CandidateBDStore store) {
        this.store = store;
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

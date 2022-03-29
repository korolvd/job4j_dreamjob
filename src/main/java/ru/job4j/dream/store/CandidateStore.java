package ru.job4j.dream.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CandidateStore {

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(1);

    public CandidateStore() {
        add(new Candidate(1, "Ivan Ivanov", "Junior dev",
                LocalDate.of(2022, 1, 16)));
        add(new Candidate(2, "Vasiliy Ivanov", "Middle dev",
                LocalDate.of(2022, 2, 23)));
        add(new Candidate(3, "Igor Banchenko", "Senior dev",
                LocalDate.of(2022, 3, 2)));
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidate.setId(ids.get());
        candidates.put(ids.incrementAndGet(), candidate);
    }

    public void update(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }
}

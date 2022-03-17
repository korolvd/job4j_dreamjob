package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    public CandidateStore() {
        candidates.put(1, new Candidate(1, "Ivan Ivanov", "Junior dev",
                LocalDate.of(2022, 1, 16)));
        candidates.put(2, new Candidate(2, "Vasiliy Ivanov", "Middle dev",
                LocalDate.of(2022, 2, 23)));
        candidates.put(3, new Candidate(3, "Igor Banchenko", "Senior dev",
                LocalDate.of(2022, 3, 2)));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}

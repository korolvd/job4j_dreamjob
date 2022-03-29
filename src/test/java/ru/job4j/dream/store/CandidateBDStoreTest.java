package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CandidateBDStoreTest {

    private static BasicDataSource pool;
    private static CandidateBDStore store;

    @BeforeClass
    public static void init() {
        pool = new Main().loadPool();
        store = new CandidateBDStore(pool);
    }

    @After
    public void deleteTable() throws SQLException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from candidate")) {
            statement.execute();
        }
    }

    @Test
    public void whenCreateCandidate() {
        LocalDate date = LocalDate.now();
        Candidate candidate = new Candidate(0, "Java Dev", "Junior", date, null);
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenFindByIdPost() {
        LocalDate date = LocalDate.now();
        Candidate candidate1 = new Candidate(0, "Java Dev", "Junior", date, null);
        Candidate candidate2 = new Candidate(0, "Fullstack", "Junior", date, null);
        Candidate candidate3 = new Candidate(0, "DevOps", "Junior", date, null);
        store.add(candidate1);
        store.add(candidate2);
        store.add(candidate3);
        Candidate candidateInDb = store.findById(candidate2.getId());
        assertThat(candidateInDb.getName(), is(candidate2.getName()));
    }

    @Test
    public void whenFindAllPosts() {
        LocalDate date = LocalDate.now();
        Candidate candidate1 = new Candidate(0, "Java Dev", "Junior", date, null);
        Candidate candidate2 = new Candidate(0, "Fullstack", "Junior", date, null);
        Candidate candidate3 = new Candidate(0, "DevOps", "Junior", date, null);
        store.add(candidate1);
        store.add(candidate2);
        store.add(candidate3);
        List<Candidate> candidates = List.of(candidate1, candidate2, candidate3);
        List<Candidate> caindidatesInDb = store.findAll();
        assertThat(caindidatesInDb, is(candidates));
    }
}
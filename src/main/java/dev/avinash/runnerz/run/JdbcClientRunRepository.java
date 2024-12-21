package dev.avinash.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class JdbcClientRunRepository {
    public static final Logger logger = LoggerFactory.getLogger(JdbcClientRunRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    void createRun(Run run) {
        jdbcClient.sql("INSERT INTO Run(id, title, started_on, completed_on, miles, location) VALUES (?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().name()))
                .update();
    }

    List<Run> findAll() {
        return jdbcClient.sql("select * from run")
                       .query(Run.class)
                       .list();
    }

    void update(Run run, Integer id) {
        jdbcClient.sql("UPDATE run SET title = ?, started_on = ?, completed_on = ?,miles = ?, location = ? WHERE id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().name(), id))
                .update();
    }

    Optional<Run> findById(Integer id) {
        return jdbcClient.sql("select id,title,started_on,completed_on,miles,location from run where id = ?")
                       .param(id)
                       .query(Run.class)
                       .optional();
    }

    void delete(Integer id) {
        jdbcClient.sql("DELETE FROM run WHERE id = ?").param(id).update();
    }


//    private List<Run> runs = new ArrayList<Run>();
//    List<Run> findAll() {
//        return runs;
//    }
//
//    void createRun(Run run) {
//        runs.add(run);
//    }
//
//    void update(Run run, Integer id) {
//        Optional<Run> existingRun = findById(id);
//        if (existingRun.isPresent()) {
//            runs.set(runs.indexOf(existingRun.get()), run);
//        }
//    }
//
//    Optional<Run> findById(Integer id) {
//        return runs.stream().filter(run -> run.id().equals(id))
//                       .findFirst();
//    }
//
//    void delete(Integer id) {
//        runs.removeIf(run -> run.id().equals(id));
//
//    }
//
//    @PostConstruct
//    private void init() {
//        runs.add(new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 1, Location.OUTDOOR));
//        runs.add(new Run(2, "Next Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 2, Location.INDOOR));
//    }
}

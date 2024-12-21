package dev.avinash.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {
    @Autowired
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("/hello")
    String home() {
        return "Hello Runnerz";
    }

    @GetMapping()
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) throws NoRunFoundException {
        Optional<Run> run = runRepository.findById(id);
        if (run.isEmpty()) {
            throw new NoRunFoundException();
        }
        return run.get();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void createRun(@Valid @RequestBody Run run) {
        System.out.println("Current run is " + run);
        //runRepository.createRun(run);
        runRepository.save(run);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateRun(@Valid @RequestBody Run run, @PathVariable Integer id) {
        //runRepository.update(run, id);
        runRepository.save(run);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteRun(@PathVariable Integer id) {
        //runRepository.delete(id);
        runRepository.delete(runRepository.findById(id).get());
    }
}

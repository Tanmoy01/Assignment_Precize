package com.example.demo_Precize.Controller;

import com.example.demo_Precize.Entity.SATResult;
import com.example.demo_Precize.Service.SATResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/results")
public class SATResultController {
    @Autowired
    SATResultService satResultService;

    @PostMapping("/save")
    public ResponseEntity<SATResult> saveResult(@RequestBody SATResult result) {
        return new ResponseEntity<>(satResultService.saveResult(result), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<SATResult>> getAllResults() {
        List<SATResult> results = satResultService.getAllResults();
        return ResponseEntity.ok(results);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Optional<?>> updateScore(@PathVariable String name,
                                                   @RequestParam(name = "score") int newScore) {
        Optional<?> response = satResultService.updateScore(name, newScore);

        if (response.isEmpty())
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{name}/rank")
    public ResponseEntity<?> getRankByName(@PathVariable String name) {
        int rank = satResultService.getRankByName(name);
        if (rank > 0)
            return ResponseEntity.ok(rank);
        return new ResponseEntity<>("Name is not present", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteResult(@PathVariable String name) {
        String result = satResultService.deleteResultByName(name);
        System.out.println(result + " result");
        if (result == null)
            return new ResponseEntity<>("Name is not present", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.ACCEPTED);
    }
}

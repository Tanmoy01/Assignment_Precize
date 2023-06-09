package com.example.demo_Precize.Service;

import com.example.demo_Precize.Entity.SATResult;
import com.example.demo_Precize.Repository.SATResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Service
public class SATResultService {
    @Autowired
    SATResultRepository satResultRepository;

    public SATResult saveResult(SATResult result) {
        result.setResult(result.getScore() > 30 ? "Pass" : "Fail");
        return satResultRepository.save(result);
    }

    public List<SATResult> getAllResults() {
        return satResultRepository.findAll();
    }

    public Optional<SATResult> updateScore(String name, int newScore) {
        Optional<SATResult> optionalResult = satResultRepository.findByName(name);
        optionalResult.ifPresent(results -> {
            results.setScore(newScore);
            results.setResult(results.getScore() > 30 ? "Pass" : "Fail");
            satResultRepository.save(results);
        });
        return optionalResult;
    }

    public int getRankByName(String name) {
        List<SATResult> allResults = satResultRepository.findAllByDesc();

        OptionalInt rank = IntStream.range(0, allResults.size())
                .filter(i -> allResults.get(i).getName().equals(name))
                .findFirst();

        return rank.orElse(-1) + 1;
    }

    public String deleteResultByName(String name) {
        Optional<?> optional = satResultRepository.findByName(name);
        if (optional.isEmpty())
            return null;
        satResultRepository.deleteByName(name);
        return "Deleted Successfully";
    }
}

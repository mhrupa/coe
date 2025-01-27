package com.technivaaran.coe.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technivaaran.coe.entities.Candidate;
import com.technivaaran.coe.services.CandidateService;
import com.technivaaran.coe.utils.AppConstants;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
        Candidate createdCandidate = candidateService.createCandidate(candidate);
        return ResponseEntity.ok(createdCandidate);
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getCandidates() {
        //TODO: Need pagination
        List<Candidate> candidateList = candidateService.getCandidates();
        return ResponseEntity.ok(candidateList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(
            @PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate updatedCandidate = candidateService.updateCandidate(id, candidate);
        return ResponseEntity.ok(updatedCandidate);
    }
}

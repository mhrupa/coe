package com.technivaaran.coe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.technivaaran.coe.entities.Candidate;
import com.technivaaran.coe.repositories.CandidateRepository;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        Optional<Candidate> existingCandidate = candidateRepository.findById(id);
        if (existingCandidate.isPresent()) {
            Candidate candidate = existingCandidate.get();
            candidate.setFirstName(updatedCandidate.getFirstName());
            candidate.setLastName(updatedCandidate.getLastName());
            candidate.setEmail(updatedCandidate.getEmail());
            candidate.setPhoneNumber(updatedCandidate.getPhoneNumber());
            candidate.setDateOfBirth(updatedCandidate.getDateOfBirth());
            candidate.setSkills(updatedCandidate.getSkills());
            candidate.setExperienceYears(updatedCandidate.getExperienceYears());
            candidate.setResumeUrl(updatedCandidate.getResumeUrl());
            return candidateRepository.save(candidate);
        } else {
            throw new RuntimeException("Candidate not found with id: " + id);
        }
    }
}

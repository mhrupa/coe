package com.technivaaran.coe.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.technivaaran.coe.dtos.RandomQuestionListRequest;
import com.technivaaran.coe.entities.Question;
import com.technivaaran.coe.services.QuestionService;
import com.technivaaran.coe.utils.AppConstants;
import com.technivaaran.coe.utils.JsonUtils;

@RestController
@RequestMapping(AppConstants.BASE_URL)
public class QuestionController {
    private static Logger logger = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/questions/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping("/random-question")
    public ResponseEntity<List<Question>> getRandomQuestion(
            @RequestBody RandomQuestionListRequest randomQuestionListRequest)
            throws IOException {
        logger.info("Started processing GET random question list {} ", JsonUtils.toJson(randomQuestionListRequest));
        return questionService.getRandQuestions(randomQuestionListRequest);
    }

    @PostMapping("/upload")
    // @Transactional
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            // Parse the uploaded Excel file and save data to DB
            return questionService.processExcelFile(file);

        } catch (IOException e) {
            return new ResponseEntity<>("Failed to process file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

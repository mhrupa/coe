package com.technivaaran.coe.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.technivaaran.coe.dtos.RandomQuestionListRequest;
import com.technivaaran.coe.entities.Question;
import com.technivaaran.coe.repositories.QuestionRepository;

@Service
public class QuestionService {
    private static Logger logger = LoggerFactory.getLogger(QuestionService.class);
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public ResponseEntity<List<Question>> getRandQuestions(RandomQuestionListRequest randomQuestionListRequest) {
        List<String> topics = randomQuestionListRequest.getTopicList();
        List<Question> responseList = new ArrayList<>();
        for (String topic : topics) {
            List<Question> questionList = questionRepository.findRandomQuestionsByTopicAndLevel(topic,
                    randomQuestionListRequest.getLevel(),
                    randomQuestionListRequest.getNoOfQuestions());
            responseList.addAll(questionList);
        }
        return ResponseEntity.ok(responseList);
    }

    public ResponseEntity<String> processExcelFile(MultipartFile file) throws IOException {
        logger.info("excel processing started");
        try {
            // Read the uploaded Excel file
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Iterator<Sheet> sheetIterator = workbook.iterator();

            // Iterate through each sheet in the file
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                Iterator<Row> rowIterator = sheet.iterator();

                // Skip header row
                rowIterator.next();

                // Iterate through rows of the sheet
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Question question = new Question();

                    // Assuming columns are: Topic, Level, Question, Answer
                    question.setTopic(row.getCell(1).getStringCellValue());
                    question.setLevel(row.getCell(2).getStringCellValue());
                    question.setQuestionString(row.getCell(3).getStringCellValue());
                    question.setAnswer(row.getCell(4).getStringCellValue());

                    // Save to database
                    questionRepository.save(question);
                }
            }

            // Close the workbook
            workbook.close();
            return ResponseEntity.ok("File processed and data inserted successfully");
        } catch (Exception ex) {
            logger.error("Error occurred while processing the file, {}", ex.getMessage());
            return ResponseEntity.internalServerError().body("Error occurred while processing the file.");
        }
    }
}

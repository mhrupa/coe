package com.technivaaran.coe.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.technivaaran.coe.utils.AppConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(AppConstants.BASE_URL)
@Slf4j
public class ErrorController {

    @PostMapping("/error")
    public ResponseEntity<Object> publishErrorMessage() {
        @SuppressWarnings("null")
        Object errorMsg = RequestContextHolder.getRequestAttributes().getAttribute("errorMsg", RequestAttributes.SCOPE_REQUEST);
        log.info("error received, {}", errorMsg);
        return ResponseEntity.badRequest().body(errorMsg);
    }
}

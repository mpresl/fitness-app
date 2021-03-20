package com.running.app.controllers;

import com.running.app.dto.TestDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@AllArgsConstructor
@Slf4j
public class TestController {

  @PostMapping
  public ResponseEntity<?> create(@RequestBody TestDto testDto) {
    return new ResponseEntity<>(testDto, HttpStatus.OK);
  }
}

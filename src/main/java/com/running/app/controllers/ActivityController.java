package com.running.app.controllers;


import com.running.app.dto.ActivityRequest;
import com.running.app.dto.ActivityResponse;
import com.running.app.model.Activity;
import com.running.app.services.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/activity")
@AllArgsConstructor
public class ActivityController {

  private final ActivityService activityService;

  @GetMapping
  public ResponseEntity<List<ActivityResponse>> getAllActivities() {
    return new ResponseEntity<>(activityService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getActivity(@PathVariable Long id) {
    return new ResponseEntity<>(activityService.findById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody @Valid ActivityRequest activityRequest) {
    activityService.save(activityRequest);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("by-user/{name}")
  public ResponseEntity<?> getActivitiesByUsername(@PathVariable String name){
    return new ResponseEntity<>(activityService.getAllByUser(name), HttpStatus.OK);
  }

  @GetMapping("by-user/{name}/{type}")
  public ResponseEntity<?> getAllActivitiesByUserAndType(@PathVariable String name, @PathVariable String type) {
    return new ResponseEntity<>(activityService.getAllTypeByUser(name, type), HttpStatus.OK);
  }

}

package com.running.app.controllers;


import com.running.app.dto.ActivityRequest;
import com.running.app.dto.ActivityResponse;
import com.running.app.services.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/activity")
@AllArgsConstructor
public class ActivityController {

  private final ActivityService activityService;

  // @GetMapping
  // public List<RunResponse> getAllActivities() {
  //   return activityService.getAll();
  // }
  // //
  // @GetMapping("/{id}")
  // public RunDto getRun(@PathVariable Long id) {
  //   return activityService.getRun(id);
  // }
  //
  @PostMapping
  public ActivityResponse create(@RequestBody @Valid ActivityRequest activityRequest) {
    return activityService.save(activityRequest);
  }

}

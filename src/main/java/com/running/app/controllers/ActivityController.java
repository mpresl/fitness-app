package com.running.app.controllers;

import com.running.app.model.Activity;
import com.running.app.services.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ActivityController {

  private final ActivityService activityService;

  public ActivityController(ActivityService activityService) {
    this.activityService = activityService;
  }

  @GetMapping("/home")
  public String home(Model model) {
    List<Activity> activityList = activityService.findAllActivities();
    if (activityList != null) {
      model.addAttribute("activities", activityList);
    }
    return "main";
  }

  @PostMapping("/addActivity")
  public String addActivity(Model model, @RequestBody Activity activity) {
    activityService.save(activity);
    return "redirect:/home";
  }
}

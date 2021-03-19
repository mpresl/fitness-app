package com.running.app.services;

import com.running.app.model.Activity;
import com.running.app.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{

  private final ActivityRepository activityRepository;

  public ActivityServiceImpl(ActivityRepository activityRepository) {
    this.activityRepository = activityRepository;
  }

  @Override
  public List<Activity> findAllActivities() {
    return activityRepository.findAll();
  }

  @Override
  public void save(Activity activity) {
    activityRepository.save(activity);
  }
}

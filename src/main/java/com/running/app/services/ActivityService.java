package com.running.app.services;

import com.running.app.model.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {

  public List<Activity> findAllActivities();

  void save(Activity activity);
}

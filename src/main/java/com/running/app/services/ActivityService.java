package com.running.app.services;

import com.running.app.dto.ActivityRequest;
import com.running.app.dto.ActivityResponse;
import com.running.app.exceptions.MikeRunningException;
import com.running.app.model.Activity;
import com.running.app.model.ActivityType;
import com.running.app.model.User;
import com.running.app.repositories.ActivityRepository;
import com.running.app.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActivityService {

  private final ActivityRepository activityRepository;
  private final UserRepository userRepository;
  private final AuthService authService;

  public void save(ActivityRequest activityRequest) {

    Activity activity = Activity.builder()
        .distance(activityRequest.getDistance())
        .imgUrl(activityRequest.getImgUrl())
        .type(ActivityType.RUN)
        .user(authService.getCurrentUser())
        .date(LocalDate.parse(activityRequest.getDate()))
        .duration(convertToDuration(activityRequest.getDuration()))
        .build();

    activityRepository.save(activity);
    }

  private ActivityResponse activityToResponse (Activity activity) {
    return ActivityResponse.builder()
        .activityId(activity.getActivityId())
        .username(activity.getUser().getUsername())
        .date(activity.getDate())
        .distance(activity.getDistance())
        .duration(convertDurationToString(activity.getDuration()))
        .imgUrl(activity.getImgUrl())
        .type(activity.getType().toString())
        .build();
  }

  private String convertDurationToString(Duration duration) {
    return duration.toHoursPart() + ":" + duration.toMinutesPart() + ":" +duration.toSecondsPart();
  }

  private Duration convertToDuration(String time) {
    String[] values = time.split(":");

    Duration duration = Duration.ofHours(Integer.parseInt(values[0]));
    duration = duration.plusMinutes(Integer.parseInt(values[1]));
    duration = duration.plusSeconds(Integer.parseInt(values[2]));

    return  duration;
  }

  public List<ActivityResponse> getAll() {
    return activityRepository.findAll().stream()
        .map(this::activityToResponse)
        .collect(Collectors.toList());
  }

  public ActivityResponse findById(Long id) {
    return activityToResponse(activityRepository.findById(id).orElseThrow(() -> new MikeRunningException("No Activity" +
        " with ID: " + id + " exists!"))) ;
  }

  public List<ActivityResponse> getAllByUser(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));

    return activityRepository.findAllByUser(user).stream()
        .map(this::activityToResponse)
        .collect(Collectors.toList());
  }

  public List<ActivityResponse> getAllTypeByUser(String name, String type) {
    User user = userRepository.findByUsername(name)
        .orElseThrow(() -> new UsernameNotFoundException(name));

    if (type.equalsIgnoreCase("run")) {
      return activityRepository.findAllByUser(user).stream()
          .filter(activity -> activity.getType().equals(ActivityType.RUN))
          .map(this::activityToResponse)
          .collect(Collectors.toList());
    } else if (type.equalsIgnoreCase("swim")) {
      return activityRepository.findAllByUser(user).stream()
          .filter(activity -> activity.getType().equals(ActivityType.SWIM))
          .map(this::activityToResponse)
          .collect(Collectors.toList());
    }else {
      throw new MikeRunningException("Invalid Activity Type");
    }
  }
}

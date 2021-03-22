package com.running.app.services;

import com.running.app.dto.ActivityRequest;
import com.running.app.dto.ActivityResponse;
import com.running.app.model.Activity;
import com.running.app.model.ActivityType;
import com.running.app.repositories.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ActivityService {

  private final ActivityRepository activityRepository;
  private final AuthService authService;

  public ActivityResponse save(ActivityRequest activityRequest) {


    Activity activity = Activity.builder()
        .distance(activityRequest.getDistance())
        .imgUrl(activityRequest.getImgUrl())
        .type(ActivityType.RUN)
        .user(authService.getCurrentUser())
        .date(LocalDate.parse(activityRequest.getDate()))
        .duration(convertToDuration(activityRequest.getDuration()))
        .build();

    activityRepository.save(activity);
    return activityToResponse(activity);
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

  // public List<RunResponse> getAll() {
  //   return activityRepository.findAll()
  //       .stream()
  //       .filter(activity -> activity.getType().equals(ActivityType.RUN))
  //       .map(a -> runToRunResponse(a))
  //       .collect(Collectors.toList());
  // }


  // @Transactional(readOnly = true)
  // public List<RunDto> getAll() {
  //   return activityRepository.findAll()
  //       .stream()
  //       .collect(toList());
  // }
}

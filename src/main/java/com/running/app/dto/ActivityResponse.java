package com.running.app.dto;

import com.running.app.model.ActivityType;
import com.running.app.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityResponse {
  private Long activityId;
  private String username;
  private LocalDate date;
  private String type;
  private double distance;
  private String duration;
  private String imgUrl;
}

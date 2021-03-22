package com.running.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long activityId;
  private Duration duration;
  private double distance;
  private String imgUrl;
  private LocalDate date;
  private ActivityType type;
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private User user;


}

package com.running.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public abstract class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long activityId;
  private LocalDate date;
  private ActivityType type;
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private User user;

  public Activity(ActivityType type) {
    this.type = type;
  }

}

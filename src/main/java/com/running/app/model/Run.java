package com.running.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Run extends Activity{
  private Long duration;
  private double distance;
  private String imgUrl;

  public Run() {
    super(ActivityType.RUN);
  }
}

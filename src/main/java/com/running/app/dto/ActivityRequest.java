package com.running.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {
  //as String "hh:mm:ss"
  private String duration;
  private double distance;
  private String imgUrl;
  //"yyyy-mm-dd"
  private String date;
}

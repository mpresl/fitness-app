package com.running.app.repositories;

import com.running.app.model.Activity;
import com.running.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository <Activity, Long> {
  List<Activity> findAllByUser(User user);
}

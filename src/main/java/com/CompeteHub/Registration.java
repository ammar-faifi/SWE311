package com.CompeteHub;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Registration {
  @Id @GeneratedValue private Long id;

  private String name;
  private Long tourId;

  public Registration() {}

  public Registration(String name, Long tourId) {
    this.name = name;
    this.tourId = tourId;
  }
}

package com.CompeteHub;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Todo {

  public Todo() {}

  public Todo(String description, String details, boolean done) {
    this.description = description;
    this.details = details;
    this.done = done;
  }

  @Id @GeneratedValue private Long id;

  private String description;

  private String details;

  private boolean done;
}

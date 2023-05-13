package com.CompeteHub;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepo extends JpaRepository<Tour, Long> {

    List<Tour> findAllBySupervisor(String supervisor);
}

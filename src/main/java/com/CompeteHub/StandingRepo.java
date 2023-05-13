package com.CompeteHub;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StandingRepo extends JpaRepository<Standing, Long> {

    public Standing findByTourId(Long tourId);

}

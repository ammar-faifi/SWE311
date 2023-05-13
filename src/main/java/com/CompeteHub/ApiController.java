package com.CompeteHub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.utils.ByteConversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
  private static final String template = "You gave %s";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/randRR")
  public List<List<String>> generateRoundRobin(@RequestParam(value = "num", defaultValue = "4") int num) {
    System.out.println(num);
    return RoundRobin.generate(num, null);
  }

  @Autowired
  TourRepo tourRepository;

  @Autowired
  RegistrationRepo registrationRepo;

  @GetMapping("/getTour")
  public List<Tour> getTour(@RequestParam(value = "supervisor", defaultValue = "") String supervisor) {
    return tourRepository.findAllBySupervisor(supervisor);
  };

  @GetMapping("/getAllTour")
  public List<Tour> getAllTour() {
    return tourRepository.findAll();
  };

  @GetMapping("/registerTour")
  public boolean registerTour(@RequestParam(value = "name") String name,
      @RequestParam(value = "tourId") Long id) {
    registrationRepo.save(new Registration(name, id));
    return true;
  };

  @PostMapping("/createTour")
  public List<List<String>> createTour(@RequestBody TournamentModel body) {
    System.out.println(">>>>>>> Creating Tour.");
    List<List<String>> roundTable;
    try {
      if (body.getTeams() == "") {
        // Assuming numOfTeams not null
        roundTable = RoundRobin.generate(body.getNumOfTeams(), null);
      } else {
        // Assume it has correct length, type, and format
        List<String> teams = Arrays.asList(body.getTeams().split(","));
        roundTable = RoundRobin.generate(teams.size(), teams);
      }

      Tour tour = new Tour(body.getName(), body.getType(), body.getParticipationType(),
          body.getSport(), body.getStartDate(), body.getEndDate(),
          body.getTeams(), body.getNumOfTeams(), body.getSupervisor(),
          ByteConversion.convertObjectToByteArray(roundTable));
      // save data passed by tour
      Tour tourSaved = tourRepository.save(tour);

      return roundTable;

    } catch (Throwable e) {
      System.out.println("Error " + e.getMessage());
      return null;
    }
  }

  @GetMapping("/getRoundTable")
  public List<List<String>> getRoundTable(@RequestParam(value = "tourId") Long tourId) {
    try {
      byte[] rounds = tourRepository.findById(tourId).orElseThrow().getRounds();
      return (List<List<String>>) ByteConversion.convertByteArrayToObject(rounds, Object.class);

    } catch (Throwable e) {
      System.out.println("Error " + e.getMessage());
    }
    return null;
  }

}

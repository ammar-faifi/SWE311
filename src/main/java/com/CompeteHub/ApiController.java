package com.CompeteHub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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

    @GetMapping("/getAllTour")
    public List<Tour> getAllTour(@RequestParam(value = "supervisor") String supervisor) {
        return tourRepository.findAllBySupervisor(supervisor);
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

            Tour tour = new Tour(
                    body.getName(),
                    body.getType(),
                    body.getParticipationType(),
                    body.getSport(),
                    body.getStartDate(),
                    body.getEndDate(),
                    body.getTeams(),
                    body.getNumOfTeams(),
                    body.getSupervisor());
            // save data passed by tour
            Tour tourSaved = tourRepository.save(tour);
            return roundTable;

        } catch (Throwable e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/addTour")
    public ResponseEntity<?> registerUser(@RequestBody TournamentModel tournamentModel) {
        // get the data passed by user/passed to postman
        Tour tour = new Tour(
                tournamentModel.getName(),
                tournamentModel.getType(),
                tournamentModel.getParticipationType(),
                tournamentModel.getSport(),
                tournamentModel.getStartDate(),
                tournamentModel.getEndDate(),
                tournamentModel.getTeams(),
                tournamentModel.getNumOfTeams(),
                tournamentModel.getSupervisor());
        // save data passed by tour
        Tour tourSaved = tourRepository.save(tour);
        // return the saved data and an Okay.
        return new ResponseEntity(tourSaved, HttpStatus.OK);
    }

}

package com.CompeteHub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.utils.ByteConversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/randRR")
    public List<List<String>> generateRoundRobin(@RequestParam(value = "num", defaultValue = "4") int num) {
        System.out.println(num);
        return RoundRobin.generate(num, null);
    }

    @Autowired
    TourRepo tourRepository;

    @Autowired
    RegistrationRepo registrationRepo;

    @Autowired
    StandingRepo standingRepo;

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
        // create default Score
        Score score = new Score();

        // create a list of player names
        List<String> playerNames = new ArrayList<>();
        //
        //
        try {
            if (body.getTeams() == "") {
                for (int i = 0; i < body.getNumOfTeams(); i++) {
                    playerNames.add("Team " + i);
                }
            } else {
                // Assume it has correct length, type, and format
                playerNames = Arrays.asList(body.getTeams().split(","));
            }
            roundTable = RoundRobin.generate(playerNames.size(), playerNames);
            score.printInitialStandings(playerNames.size(), playerNames);

            Tour tour = new Tour(body.getName(), body.getType(), body.getParticipationType(),
                    body.getSport(), body.getStartDate(), body.getEndDate(),
                    body.getTeams(), body.getNumOfTeams(), body.getSupervisor(),
                    ByteConversion.convertObjectToByteArray(roundTable));
            // save data passed by tour
            Tour tourSaved = tourRepository.save(tour);

            Standing standingSaved = standingRepo.save(
                    new Standing(tourSaved.getId(), ByteConversion.convertObjectToByteArray(score.getStandings())));

            return roundTable;

        } catch (Throwable e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
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

    @RequestMapping("/updateScore")
    @ResponseBody
    public String updateScore(@RequestParam(value = "tourId") Long tourId,
            @RequestParam(value = "p1score") int p1score,
            @RequestParam(value = "p2score") int p2score) {
        // Assuming `standings` is already good, and `tour` not new
        //
        Tour tour = tourRepository.findById(tourId).orElseThrow();
        Standing standingsEntity = standingRepo.findByTourId(tourId);
        Score cc = new Score();

        if (standingsEntity != null) {
            List<String> playerNames = Arrays.asList(tour.getTeams().split(","));
            try {
                Map<String, Score.Standings> standings = (Map<String, Score.Standings>) ByteConversion
                        .convertByteArrayToObject(standingsEntity.getStandings(), Object.class);
                cc.setStandings(standings);
            } catch (Throwable e) {
                System.out.println("Error " + e.getMessage());
            }
        }

        for (List<String> match : getRoundTable(tourId)) {
            for (String fixture : match) {
                String[] players = fixture.split(" vs. ");
                String player1 = players[0];
                String player2 = players[1];
                cc.updateStandings(player1, player2, p1score, p2score);
            }
        }

        try {
            standingsEntity.setStandings(ByteConversion.convertObjectToByteArray(cc.getStandings()));
            standingRepo.save(standingsEntity);
        } catch (Throwable e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }

        return cc.printStandings();
    }

}

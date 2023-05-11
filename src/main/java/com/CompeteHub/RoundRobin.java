package com.CompeteHub;

import java.util.*;

public class RoundRobin {
    public static List<List<String>> generate(int numOfPlayers) {
        // create a list of player names
        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            playerNames.add("Team " + i);
        }

        System.out.println(playerNames);
        List<List<String>> rounds = new ArrayList<>(); // create a list of rounds

        // create a round-robin schedule
        if ((numOfPlayers % 2 == 0)) { // if even number of players
            for (int i = 0; i < numOfPlayers - 1; i++) { // for each round
                List<String> round = new ArrayList<>(); // create a list of matches
                for (int j = 0; j < numOfPlayers / 2; j++) { // for each match
                    int player1Index = (i + j) % (numOfPlayers - 1); // get player 1 index
                    int player2Index = (numOfPlayers - 1 - j + i) % (numOfPlayers - 1); // get player 2 index
                    if (j == 0) { // if first match
                        player2Index = numOfPlayers - 1; // set player 2 index to last player
                    }
                    round.add(playerNames.get(player1Index) + " vs. " + playerNames.get(player2Index)); // add match to
                                                                                                        // round
                }
                rounds.add(round); // add round to list of rounds
            }
            System.out.println(rounds); // print list of rounds
        } else {
            // ################# does not work correctly for odd number of players
            for (int i = 0; i < numOfPlayers; i++) {
                List<String> round = new ArrayList<>();
                for (int j = 0; j < numOfPlayers / 2; j++) {
                    int player1Index = (i + j) % (numOfPlayers - 1);
                    int player2Index = (numOfPlayers - 1 - j + i) % (numOfPlayers - 1);
                    if (j == 0) {
                        player2Index = numOfPlayers - 1;
                    }
                    round.add(playerNames.get(player1Index) + " vs. " + playerNames.get(player2Index));
                }
                rounds.add(round);
            }
            System.out.println(rounds);
        }

        return rounds;
    }
}

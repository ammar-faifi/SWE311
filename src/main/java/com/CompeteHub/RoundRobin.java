package com.CompeteHub;

import java.util.*;

public class RoundRobin {

    public static List<List<String>> generate(int numOfPlayers, List<String> playerNames) {

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
            numOfPlayers += 1; // add a bye
            playerNames.add("bye"); // add a bye
            Collections.shuffle(playerNames); // shuffle the list of player names
            // List<List<String>> rounds = new ArrayList<>(); // create a list of rounds
            for (int i = 0; i < numOfPlayers - 1; i++) { // for each round
                List<String> round = new ArrayList<>(); // create a list of matches
                for (int j = 0; j < numOfPlayers / 2; j++) { // for each match
                    int player1Index = (i + j) % (numOfPlayers - 1); // get player 1 index
                    int player2Index = (numOfPlayers - 1 - j + i) % (numOfPlayers - 1); // get player 2 index
                    if (j == 0) { // if first match
                        player2Index = numOfPlayers - 1; // set player 2 index to last player
                    }
                    // if any player is bye ignore the match
                    if (playerNames.get(player1Index).equals("bye") || playerNames.get(player2Index).equals("bye")) {
                        continue;
                    }
                    round.add(playerNames.get(player1Index) + " vs. " + playerNames.get(player2Index)); // add match to
                                                                                                        // round
                }
                rounds.add(round); // add round to list of rounds
            }
            playerNames.remove("bye"); // remove bye from list of player names
            numOfPlayers -= 1; // remove bye
        }

        return rounds;
    }
}

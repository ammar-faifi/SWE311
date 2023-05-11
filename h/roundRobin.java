import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class roundRobin {
    public static void main(String[] args) {

        // get number of players
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        int numOfPlayers = scanner.nextInt();
        scanner.nextLine();
        // enter the tournament name
        System.out.print("Enter tournament name: ");
        String tournamentName = scanner.nextLine();
        // create a list of player names
        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.print("Enter player name " + (i + 1) + ": ");
            playerNames.add(scanner.nextLine());
        }

        List<List<String>> rounds = new ArrayList<>(); // create a list of rounds

        // create a round-robin schedule
        if ((numOfPlayers % 2 == 0)) { // if even number of players
            Collections.shuffle(playerNames); // shuffle the list of player names
//            List<List<String>> rounds = new ArrayList<>(); // create a list of rounds
            for (int i = 0; i < numOfPlayers - 1; i++) { // for each round
                List<String> round = new ArrayList<>(); // create a list of matches
                for (int j = 0; j < numOfPlayers / 2; j++) { // for each match
                    int player1Index = (i + j) % (numOfPlayers - 1); // get player 1 index
                    int player2Index = (numOfPlayers - 1 - j + i) % (numOfPlayers - 1); // get player 2 index
                    if (j == 0) { // if first match
                        player2Index = numOfPlayers - 1; // set player 2 index to last player
                    }
                    round.add(playerNames.get(player1Index) + " vs. " + playerNames.get(player2Index)); // add match to round
                }
                rounds.add(round); // add round to list of rounds
            }
        } else {
            numOfPlayers += 1; // add a bye
            playerNames.add("bye"); // add a bye
            Collections.shuffle(playerNames); // shuffle the list of player names
//            List<List<String>> rounds = new ArrayList<>(); // create a list of rounds
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
                    round.add(playerNames.get(player1Index) + " vs. " + playerNames.get(player2Index)); // add match to round
                }
                rounds.add(round); // add round to list of rounds
            }
            playerNames.remove("bye"); // remove bye from list of player names
            numOfPlayers -= 1; // remove bye
        }
        List<String> finalStandings = new Score().score(rounds, playerNames, numOfPlayers);
        // print final standings
        System.out.println("Final Standings:");
        for (int i = 0; i < finalStandings.size(); i++) {
            System.out.println((i + 1) + ": " + finalStandings.get(i));
        }

//        // save final standings to file in current directory
//        new Save().save(finalStandings, tournamentName);

    }
}


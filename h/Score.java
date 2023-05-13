import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score {
    private Map<String, Standings> standings;

    public Score() {
        standings = new HashMap<>();
    }

    public void updateStandings(String player1, String player2, int score1, int score2) {
        Standings player1Standings = standings.getOrDefault(player1, new Standings());
        Standings player2Standings = standings.getOrDefault(player2, new Standings());

        player1Standings.gamesPlayed++;
        player2Standings.gamesPlayed++;

        player1Standings.goalsFor += score1;
        player2Standings.goalsFor += score2;

        if (score1 > score2) {
            player1Standings.wins++;
            player1Standings.points += 3;
            player2Standings.losses++;
        } else if (score1 < score2) {
            player2Standings.wins++;
            player2Standings.points += 3;
            player1Standings.losses++;
        } else {
            player1Standings.draws++;
            player1Standings.points++;
            player2Standings.draws++;
            player2Standings.points++;
        }

        standings.put(player1, player1Standings);
        standings.put(player2, player2Standings);
    }

    public void printStandings() {
        System.out.println("Current Standings:");
        System.out.println("#\tP\tPL\tW\tL\tD\tG\tPTS");
        List<Map.Entry<String, Standings>> sortedStandings = new ArrayList<>(standings.entrySet());

        sortedStandings.sort((entry1, entry2) -> {
            Standings standings1 = entry1.getValue();
            Standings standings2 = entry2.getValue();

            // Compare points
            int pointsComparison = Integer.compare(standings2.points, standings1.points);
            if (pointsComparison != 0) {
                return pointsComparison;
            }

            // Compare goals scored
            int goalsComparison = Integer.compare(standings2.goalsFor, standings1.goalsFor);
            if (goalsComparison != 0) {
                return goalsComparison;
            }

            // Compare names alphabetically
            return entry1.getKey().compareTo(entry2.getKey());
        });
        int rank = 1;
        for (Map.Entry<String, Standings> entry : sortedStandings) {
            String player = entry.getKey();
            Standings playerStandings = entry.getValue();
            // print the standings for each player based on points if draw then goals if
            // draw then alphabetical
            System.out.print("" + rank);
            System.out.print("\t" + player);
            System.out.print("\t" + playerStandings.gamesPlayed);
            System.out.print("\t" + playerStandings.wins);
            System.out.print("\t" + playerStandings.losses);
            System.out.print("\t" + playerStandings.draws);
            System.out.print("\t" + playerStandings.goalsFor);
            System.out.print("\t" + playerStandings.points);
            System.out.println();
            rank++;
        }

    }

    public void printInitialStandings(int numPlayers, List<String> playerNames) {
        System.out.println("Initial Standings:");
        System.out.println("#\tP\tPL\tW\tL\tD\tG\tPTS");
        // print the initial standing for each player and their data is all 0
        for (int i = 0; i < numPlayers; i++) {
            String playerName = playerNames.get(i);
            Standings playerStandings = standings.getOrDefault(playerName, new Standings());
            standings.put(playerName, playerStandings);
        }
        int rank = 1;
        for (Map.Entry<String, Standings> entry : standings.entrySet()) {
            String player = entry.getKey();
            Standings playerStandings = entry.getValue();
            // print the standings for each player based on points if draw then goals if
            // draw then alphabetical
            System.out.print("" + rank);
            System.out.print("\t" + player);
            System.out.print("\t" + playerStandings.gamesPlayed);
            System.out.print("\t" + playerStandings.wins);
            System.out.print("\t" + playerStandings.losses);
            System.out.print("\t" + playerStandings.draws);
            System.out.print("\t" + playerStandings.goalsFor);
            System.out.print("\t" + playerStandings.points);
            System.out.println();
            rank++;
        }
    }

    private static class Standings {
        private int gamesPlayed;
        private int wins;
        private int losses;
        private int draws;
        private int goalsFor;
        private int points;
    }
}

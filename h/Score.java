import java.util.*;

public class Score {
    public static List<String> score(List<List<String>> rounds,List<String> playerNames, int numOfPlayers){
        List<Integer> playerScores = new ArrayList<>(Collections.nCopies(numOfPlayers, 0)); // create a list of player scores
        List<Integer> playerGoals = new ArrayList<>(Collections.nCopies(numOfPlayers, 0)); // create a list of player goals
        Scanner scanner = new Scanner(System.in);
        for (List<String> round : rounds) {
            for (String match : round) {
                String[] players = match.split(" vs. ");
                int player1Index = playerNames.indexOf(players[0]);
                int player2Index = playerNames.indexOf(players[1]);

                System.out.print("Enter the score for " + match + ": ");
                int player1Score = scanner.nextInt();
                int player2Score = scanner.nextInt();
                scanner.nextLine();

                if (player1Score > player2Score) {
                    playerScores.set(player1Index, playerScores.get(player1Index) + 3);
                } else if (player2Score > player1Score) {
                    playerScores.set(player2Index, playerScores.get(player2Index) + 3);
                } else {
                    playerScores.set(player1Index, playerScores.get(player1Index) + 1);
                    playerScores.set(player2Index, playerScores.get(player2Index) + 1);
                }

                playerGoals.set(player1Index, playerGoals.get(player1Index) + player1Score);
                playerGoals.set(player2Index, playerGoals.get(player2Index) + player2Score);
            }
        }

        List<String> finalStandings = new ArrayList<>();

        for (int i = 0; i < numOfPlayers; i++) {
            finalStandings.add(playerNames.get(i) + "\tGoals: " + playerGoals.get(i) + "\tPoints: " + playerScores.get(i));
        }

        // sort final standings by points if points are equal sort by goals if goals are equal sort by name alphabetically
        Collections.sort(finalStandings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] player1 = o1.split("\t");
                String[] player2 = o2.split("\t");
                int player1Points = Integer.parseInt(player1[2].split(": ")[1]);
                int player2Points = Integer.parseInt(player2[2].split(": ")[1]);
                int player1Goals = Integer.parseInt(player1[1].split(": ")[1]);
                int player2Goals = Integer.parseInt(player2[1].split(": ")[1]);
                if (player1Points == player2Points) {
                    if (player1Goals == player2Goals) {
                        return player1[0].compareTo(player2[0]);
                    }
                    return player2Goals - player1Goals;
                }
                return player2Points - player1Points;
            }
        });
        return finalStandings;
    }
}

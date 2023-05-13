import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class EliminationTournament {
    private int numOfPlayers;
    List<Match> matches;
    List<Player> players;
    List<Match> allMatches;
    public EliminationTournament(int numOfPlayers, List<Player> players) {
        if (!isPowerOf2(numOfPlayers)) {
            throw new UnsupportedOperationException("The program only supports tournaments with a power of 2 number of players");
        }
        this.numOfPlayers = numOfPlayers;
        this.players = players;
        matches = new ArrayList<>();
        allMatches = new ArrayList<>();
        generateBracket();
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void generateBracket() {
        ArrayList<Player> arrLst = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            arrLst.add(players.get(i));
        }
        for (int j = 0; j < players.size() / 2; j++) {
            int rngInd = (int) (Math.random() * arrLst.size());
            Player p1 = arrLst.remove(rngInd);

            rngInd = (int) (Math.random() * arrLst.size());
            Player p2 = arrLst.remove(rngInd);
            Match match = new Match(p1, p2);
            matches.add(match);
            allMatches.add(match);
        }
    }
    public Player getWinner() {
        if (players.size() != 1) {
            throw new UnsupportedOperationException("The tournament isn't decided yet");
        }
        return players.get(0);
    }

    public void decideRound() {
        for (int i = 0; i < matches.size(); i++) {
            if (!matches.get(i).isDecided()) {
                throw new UnsupportedOperationException("There is an undecided match in this round");
            }
        }
        if (matches.size() == 1) {
            Match match = matches.remove(0);
            Player winner = match.getWinner();
            Player loser = match.getLoser();
            players.remove(loser);
            winner.incrementWins();
        }
        else {
            List<Match> nextMatches = new ArrayList<>();
            while (matches.size() != 0) {
                Match match = matches.remove(0);
                Player winner = match.getWinner();
                Player loser = match.getLoser();
                players.remove(loser);
                Match match2 = matches.remove(0);
                Player winner2 = match2.getWinner();
                Player loser2 = match2.getLoser();
                players.remove(loser2);
                Match newMatch = new Match(winner, winner2);
                nextMatches.add(newMatch);
                allMatches.add(newMatch);
            }
            for (int i = 0; i < nextMatches.size(); i++) {
                matches.add(nextMatches.get(i));
            }
        }
    }
    public void printBracket() {
        for (int i = 0; i < matches.size(); i++) {
            System.out.println(matches.get(i));
        }
    }
    private boolean isPowerOf2(int n) {
        while (n % 2 == 0 && n > 1) {
            n /= 2;
        }
        return n == 1;
    }

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("A")); players.add(new Player("B")); players.add(new Player("C")); players.add(new Player("D"));
        players.add(new Player("E")); players.add(new Player("F")); players.add(new Player("G")); players.add(new Player("H"));
        EliminationTournament tourney = new EliminationTournament(8, players);
        int numOfRounds = (int) (Math.log(players.size()) / Math.log(2)); // equivalent to log base 2
        tourney.printBracket();
        Scanner input = new Scanner(System.in);
        List<Match> matches = tourney.getMatches();
        for (int r = 0; r < numOfRounds; r++) {
            System.out.println("Round " + (r+1) + ":");
            for (int i = 0; i < matches.size(); i++) {
                Match match = matches.get(i);
                System.out.println("Match " + (i + 1) + ": " + match);
                System.out.print("Enter " + match.getPlayer1() + "'s score: ");
                int score1 = input.nextInt();
                System.out.print("Enter " + match.getPlayer2() + "'s score: ");
                int score2 = input.nextInt();
                match.decideMatch(score1, score2);
                System.out.println("\n");
            }
            System.out.println("-------------------------------------------------------\n");

            tourney.decideRound();
            tourney.printBracket();
        }
        System.out.println("The Winner is: " + tourney.getWinner());
    }

}

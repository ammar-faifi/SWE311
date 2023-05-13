public class Match {
    private Player player1;
    private Player player2;
    private Player winner;
    private Player loser;
    private boolean decided;
    private String score;
    public Match(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
    }

    public boolean isDecided() {
        return decided;
    }

    public void decideMatch(int p1_score, int p2_score) {
        if (!decided) {
            score = p1_score + " - " + p2_score;
            if (p1_score > p2_score) {
                winner = player1;
                loser = player2;
            }
            else if (p2_score > p1_score) {
                winner = player2;
                loser = player1;
            }
            else { // tie-breaker
                int rng = (int) (Math.random() * 2) + 1;
                if (rng == 1) {
                    winner = player1;
                    loser = player2;
                }
                else {
                    winner = player2;
                    loser = player1;
                }
            }
            this.decided = true;
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getWinner() {
        if (!decided) {
            throw new UnsupportedOperationException("The match is not decided yet");
        }
        return winner;
    }
    public Player getLoser() {
        if (!decided) {
            throw new UnsupportedOperationException("The match is not decided yet");
        }
        return loser;
    }

    public String getScore() {
        if (!decided) {
            throw new UnsupportedOperationException("The match is not decided yet");
        }
        return score;
    }

    public String toString() {
        return player1.toString() + " vs " + player2.toString();
    }
}

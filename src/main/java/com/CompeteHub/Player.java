public class Player {
    private String name;
    private int numOfWins;
    public Player(String name) {
        this.name = name;
        this.numOfWins = 0;
    }

    public String getName() {
        return name;
    }
    public int getNumOfWins() {
        return numOfWins;
    }
    public void incrementWins() {
        numOfWins++;
    }
    public String toString() {
        return name;
    }
}

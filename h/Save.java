import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Save {
    // this class is used to save the data of the tournament into a file
    // the file will be named (the name of the tournament).txt
    // the file will be saved in the same directory as the program
    // the metod will take the final standings as a parameter and save them to the file
    public static void save(List<String> finalStandings, String tournamentName) {
        try (PrintWriter writer = new PrintWriter(tournamentName + ".txt")) {
            for (int i = 0; i < finalStandings.size(); i++) {
                writer.println((i + 1) + ": " + finalStandings.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}

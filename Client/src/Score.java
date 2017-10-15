import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Score {

    // List of Scores
    private List<Integer> highscores;
    private String file;

    // Constructor Creates List of Integers from Text File
    public Score(String filename) {
        file = filename;
        try {
            highscores = new ArrayList<Integer>();
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String r = in.readLine();
            int j = 0;
            while (r != null && j < 10) {
                r.trim();
                highscores.add(Integer.parseInt(r));
                j++;
                r = in.readLine();
            }

        } catch (IOException e) {
            highscores = new ArrayList<Integer>();
        }
    }

    // adds a new score into the list, sorts the list,
    // and updates the .txt file
    public void addHighScore(int i) throws IOException {
        highscores.add(i);
        List<Integer> sorted = new LinkedList<Integer>(highscores);
        Collections.sort(sorted);
        Collections.reverse(sorted);
        while (sorted.size() > 10) {
            sorted.remove(sorted.size() - 1);
        }
        highscores = sorted;

        try {
            PrintStream out = new PrintStream(new File(file));
            for (int j = 0; j < highscores.size(); j++) {
                out.print(highscores.get(j));
                if (j != highscores.size() - 1) {
                    out.print('\n');
                }
            }
        } catch (IOException e) {
        }
    }

    // returns the list of high scores
    public List<Integer> getHighScores() {
        return highscores;
    }
}
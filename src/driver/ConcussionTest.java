/**
 * Created by: Jake Stephens
 * Main driver class for the application
 */
package driver;

import gui.Gui;
import scores.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConcussionTest {

    /**
     * Main method of the application
     * @param args
     */
    public static void main(String[] args) {

        Gui gui = new Gui();
        gui.start();

        System.out.println("About to end");


        // Createing 3 scores (right now for testing purposes)
        Score imageScore = new ImageScore(10, 3);
        Score gridScore = new GridScore(10, 10);
        Score cardScore = new CardScore(2, 3, "Queen of hearts");

        // Creating a list of Scores and adding them to the list
        List<Score> scores = new ArrayList<>();
        scores.add(imageScore);
        scores.add(gridScore);
        scores.add(cardScore);

        // Calculating the percentage scores for each score
        try {
            calculateAllScores(scores);
        } catch (ScoreCalculationException e) {
            e.printStackTrace();
            System.out.println("Score calculation error, one of the scores must have had a divide by 0 error");
        }
        // Printing the scores to the user
        printScores(scores);
        printScoresWithPercent(scores);
        // Saving the scores to a text file
        saveScores(scores);
    }

    /**
     * Method that calculates all the percentage scores
     * @param scores List of Scores
     * @throws ScoreCalculationException
     */
    public static void calculateAllScores(List<Score> scores) throws ScoreCalculationException {
        // Looping through each score in the list and calculating the percentage score
        for (Score score : scores) {
            score.calculateScore();
            if (Double.isInfinite(score.getPercentageScore())) {
                // Unchecked exception occurred, time to throw the exception
                // If score is instance of CardScore and throw a ScoreCalculationException
                // with the appropriate message
                if (score instanceof CardScore) {
                    if (((CardScore) score).getTotalRounds() == 0) {
                        throw new ScoreCalculationException("total rounds is equal to 0, cannot divide by 0 score");
                    }
                }
                // If score is instance of GridScore and throw a ScoreCalculationException
                // with the appropriate message
                if (score instanceof GridScore) {
                    if ((((GridScore) score).getTotalPossible() == 0)) {
                        throw new ScoreCalculationException("total possible is equal to 0, cannot divide by 0 to calculate score");
                    }
                }
                // If score is instance of ImageScore and throw a ScoreCalculationException
                // with the appropriate message
                if (score instanceof ImageScore) {
                    if ((((ImageScore) score).getNumCorrect() + ((ImageScore) score).getNumMissed()) == 0) {
                        throw new ScoreCalculationException("numCorrect plus numMissed is equal to 0, cannot divide by 0 to calculate score");
                    }
                }
            }
        }
    }

    /**
     * Method that loops through each score and displays it to the user
     * @param scores
     */
    public static void printScores(List<Score> scores) {
        for (Score score : scores) {
            System.out.println(score.printScore());
            if (score instanceof CardScore) {
                System.out.println("Card Picked: " + ((CardScore)score).getCard());
            }
        }
    }

    /**
     * Method that loops through each score and displays the percentage scores to the user
     * @param scores List of Scores
     */
    public static void printScoresWithPercent(List<Score> scores) {
        DecimalFormat df = new DecimalFormat(" #,##0.00 '%'");
        for (Score score : scores) {
            System.out.print(score.printScore());
            System.out.println(" with percent score of " + df.format(score.getPercentageScore()));
            if (score instanceof CardScore) {
                System.out.println("Card Picked: " + ((CardScore)score).getCard());
            }
        }
    }

    /**
     * Method that takes in a list of Scores and saved them to a text file
     * @param scores List of Scores
     */
    public static void saveScores(List<Score> scores) {

        // Getting the date and formatting it nicely
        String date = String.valueOf(System.currentTimeMillis());
        DecimalFormat df = new DecimalFormat(" #,##0.00 '%'");

        // Writing to the file
        try {
            // Creating the file writer and writing the header to the text file
            FileWriter fr = new FileWriter("scores.txt");
            BufferedWriter br = new BufferedWriter(fr);
            br.write("User's scores on " + date);
            br.write(System.lineSeparator());
            // Looping through each score and saving the scores to the text file
            for (Score score : scores) {
                br.write(df.format(score.getPercentageScore()));
                br.write(System.lineSeparator());
                if (score instanceof CardScore) {
                    br.write("Card Picked: " + ((CardScore) score).getCard());
                }
            }
            br.write(System.lineSeparator());
            br.flush();
            br.close();
            fr.close();
        } catch (java.io.IOException e) {
            // In case there is an error writing to the file, we catch the exception and continue
            // normal program execution
            System.out.println("Error writing scores to file");
            e.printStackTrace();
        }
    }
}

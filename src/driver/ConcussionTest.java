/**
 * CS-622 HW 2
 * ConcussionTest.java
 * Purpose: Main driver class for the application
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
 */
package driver;

import data.ConcussionDataRow;
import gui.Gui;
import scores.*;

import java.io.*;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConcussionTest {

    static String outputObjectScoresFile = "score_objects_scores.dat";
    static String outputObjectPassFailFile = "score_objects_pass_fail.dat";
    static String inputConcussionDataFile = "concussion_data.txt";

    /**
     * Main method of the application
     * @param args
     */
    public static void main(String[] args) {

        // Createing 3 scores (right now for testing purposes)
        Score imageScore = new ImageScore(10, 3);
        Score gridScore = new GridScore(10, 10);
        Score cardScore = new CardScore(2, 3, "Queen of hearts");

        // Creating a list of Scores and adding them to the list
        List<RoundCalculator> scores = new ArrayList<>();
        scores.add(new RoundCalculator(imageScore));
        scores.add(new RoundCalculator(gridScore));
        scores.add(new RoundCalculator(cardScore));

        // Main program loop
        while (true) {
            // Pringing out the main menu
            System.out.println("============Concussion Test Score App============");
            System.out.println("1. Enter scores for tests");
            System.out.println("2. Enter number of rounds for score (default 1)");
            System.out.println("3. Calculate percentage scores");
            System.out.println("4. Print out the scores");
            System.out.println("5. Print out the scores with percentages");
            System.out.println("6. Save scores to text file");
            System.out.println("7. Save scores to binary file");
            System.out.println("8. Save scores with pass/fail to binary file");
            System.out.println("9. Perform aggregations on concussion data from file");
            System.out.println("10. Exit");

            Scanner in = new Scanner(System.in);
            String option = in.nextLine();
            switch (option) {
                case "1": enterScoresMenu();
                case "2": enterRoudnsMenu();
                case "3":
                    // Calculating the percentage scores for each score
                    try {
                        calculateAllScores(scores);
                    } catch (ScoreCalculationException e) {
                        e.printStackTrace();
                        System.out.println("Score calculation error, one of the scores must have had a divide by 0 error");
                    }
                case "4": printScores(scores);
                case "5": printScoresWithPercent(scores);
                case "6": saveScores(scores);
                case "7": saveScoresAsObjects(scores);
                case "8": saveObjectPassFailScores();
                case "9":
                    // Reading in the concussion data file
                    List<ConcussionDataRow> concussionData = readInConcussionData();
                    // Performing the aggregations
                    performAggregations(concussionData);
                case "10": System.exit(0);
            }
        }



        // Creating instances of the generic class and testing it out
        RoundCalculator imageRounds = new RoundCalculator(imageScore);
        RoundCalculator gridRounds = new RoundCalculator(gridScore);
        gridRounds.increaseRounds();
        gridRounds.increaseRounds();
        gridRounds.increaseRounds();
        System.out.println(imageRounds.toString());
        System.out.println(gridRounds.toString());
    }

    /**
     * Method that calculates all the percentage scores
     * @param scores List of Scores
     * @throws ScoreCalculationException
     */
    public static void calculateAllScores(List<RoundCalculator> scores) throws ScoreCalculationException {
        // Looping through each score in the list and calculating the percentage score
        for (RoundCalculator rc : scores) {
            ((Score)rc.getObject()).calculateScore();
            if (Double.isInfinite(((Score)rc.getObject()).getPercentageScore())) {
                // Unchecked exception occurred, time to throw the exception
                // If score is instance of CardScore and throw a ScoreCalculationException
                // with the appropriate message
                if (rc.getObject() instanceof CardScore) {
                    if (((CardScore) rc.getObject()).getNumCorrect() + ((CardScore) rc.getObject()).getNumWrong() == 0) {
                        throw new ScoreCalculationException("total rounds is equal to 0, cannot divide by 0 score");
                    }
                }
                // If score is instance of GridScore and throw a ScoreCalculationException
                // with the appropriate message
                if (rc.getObject() instanceof GridScore) {
                    if ((((GridScore) rc.getObject()).getTotalPossible() == 0)) {
                        throw new ScoreCalculationException("total possible is equal to 0, cannot divide by 0 to calculate score");
                    }
                }
                // If score is instance of ImageScore and throw a ScoreCalculationException
                // with the appropriate message
                if (rc.getObject() instanceof ImageScore) {
                    if ((((ImageScore) rc.getObject()).getNumCorrect() + ((ImageScore) rc.getObject()).getNumMissed()) == 0) {
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
    public static void printScores(List<RoundCalculator> scores) {
        for (RoundCalculator rc : scores) {
            Score score = (Score) rc.getObject();
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
    public static void printScoresWithPercent(List<RoundCalculator> scores) {
        DecimalFormat df = new DecimalFormat(" #,##0.00 '%'");
        for (RoundCalculator rc : scores) {
            Score score = (Score) rc.getObject();
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
    public static void saveScores(List<RoundCalculator> scores) {

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
            for (RoundCalculator rc : scores) {
                Score score = (Score) rc.getObject();
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

    /**
     * Method that takes in a list of Scores and saved them to a binary file
     * @param scores List of Scores
     */
    public static void saveScoresAsObjects(List<RoundCalculator> scores) {
        /*
         * Precondition: scores is an ArrayList of Score objects (either CardScore, GridScore, or ImageScore.
         *
         * Postcondition: All Score objects are written out to the binary file outputObjectScoresFile.
         */
        try {
            try (ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(outputObjectScoresFile));) {
                // Looping through each score and saving it to the binary file
                for (RoundCalculator rc : scores)
                    outFile.writeObject(rc.getObject());
                }
            // Catching any file IO Exception and printing the stack trace
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Printing that the object write out is complete
            finally {
            System.out.println("Writing raw objects to dat file completed");
        }
    }

    /**
     * Method that reads in a binary file written out by the saveScoresAsObjects method
     * and computes whether or not it is a passing or failing score and writes a PassFail
     * object out to another binary file
     */
    public static void saveObjectPassFailScores() {
        /*
         * Precondition 1: Score objects are stored in the outputObjectScoresFilenames
         * Precondition 2: All score objects saved in the outputObjectPassFailFile file have to
         * have their percentageScores calculated
         *
         * Postcondition 1: Score objects will be wrapped in a PassFail object and set as either a pass or fail
         * Postcondition 2: PassFail objects will be written successfully to the outputObjectPassFailFile binary file
         */

        // Opening up a binary file writer
        try (ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(outputObjectScoresFile));)
        {
            // Opening up a binary file reader
            try (ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(outputObjectPassFailFile));)
            {
                while (true)
                {
                    // Creating a PassFail object and reading in a Score object from the binary file
                    PassFail pf;
                    Score score = (Score) (inputFile.readObject());
                    // If the percentage score for that score is greater than 60, create a new PassFail
                    // object with pass set to true, otherwise set pass to false
                    if (score.getPercentageScore() > 60) {
                        pf = new PassFail(score, true);
                    } else {
                        pf = new PassFail(score, false);
                    }
                    // Writing the PassFail object out to the binary file
                    outputFile.writeObject(pf);
                }
            // Catching the end of file exception that occurs when the entire file has been read
            } catch (EOFException e) {
                System.out.println("End of file reached, breaking out of loop");
            // Catching a class not found exception if there is an error when casting
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            // Catching any file IO Exception that may occur during writing
            } catch (IOException e) {
                e.printStackTrace();
            }
        // Catching any file IO Exception that may occur during reading
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that reads in a text file which contains many rows of concussion data and parses
     * it all into a List.
     */
    public static List<ConcussionDataRow> readInConcussionData() {
        /*
         * Precondition: inputConcussionDataFile is a file that contains concussion data separated by white space
         *
         * Postcondition: Score objects will be wrapped in a PassFail object and set as either a pass or fail
         */

        // Creating an empty list of ConcussionDataRows
        List<ConcussionDataRow> data = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(inputConcussionDataFile));
            // While a new line exists in the data file, read the next line
            while (scan.hasNextLine()) {
                // Split the line on white space and create a new ConcussionDataRow object and insert it into the list
                String[] tokens = scan.nextLine().split("\\s+");
                data.add(new ConcussionDataRow(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]));
            }
        // Catching a file not found exception
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Returning the fully populated list of ConcussionDataRows
        return data;
    }

    /**
     * Method that performs stream transformations on the concussion dataset
     * @param data
     */
    public static void performAggregations(List<ConcussionDataRow> data) {
        /*
         * Precondition: data is a fully populated list of ConcussionDataRow objects
         *
         * Postcondition: Results of the below calculations are printed on the screen
         */

        // Calculating number of people concussed in 2000
        long numConcussed = data.stream()
                .filter(i -> i.isConcussed())
                .mapToInt(i -> i.getCount())
                .sum();

        // Calculating number of males concussed
        long numMalesConcussed = data.stream()
                .filter(i -> i.getGender().equals("Male"))
                .filter(i -> i.isConcussed())
                .mapToInt(i -> i.getCount())
                .sum();

        // Printing out the results of the calculations
        System.out.println("Number of people concussed in the year 2000: " + numConcussed);
        System.out.println("Total number of males concussed in dataset: " + numMalesConcussed);
    }
}
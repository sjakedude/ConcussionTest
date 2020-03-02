/**
 * CS-622 HW 2
 * ConcussionTest.java
 * Purpose: Main driver class for the application
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
 */
package driver;

import data.*;
import scores.*;
import scores.ScoreCalculationException;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConcussionTest {


    // Createing 3 scores (right now for testing purposes)
    static Score imageScore = new ImageScore();
    static Score gridScore = new GridScore();
    static Score cardScore = new CardScore();

    // Creating a list of Scores
    static List<RoundCalculator> scores = new ArrayList<>();

    // Creating lists of Data from text files
    static List<ConcussionDataRow> concussionData;
    static List<PersonDataRow> personData;
    static List<PatientDataRow> patientData;


    // Creating a global scanner object to grabbing the user's inputs
    static Scanner in = new Scanner(System.in);


    /**
     * Main method of the application
     * @param args
     */
    public static void main(String[] args) {

        // Adding the Score objects to the list of scores
        scores.add(new RoundCalculator(imageScore));
        scores.add(new RoundCalculator(gridScore));
        scores.add(new RoundCalculator(cardScore));

        // Reading in the datasets from the files
        concussionData = DataIO.importConcussionData();
        personData = DataIO.importPersonData();
        patientData = DataIO.importPatientData();

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
            System.out.println("10. Enter database menu");
            System.out.println("11. Exit");

            // Getting the user's input
            String option = in.nextLine();
            switch (option) {
                case "1": enterScoresMenu();
                    break;
                case "2": enterRoundsMenu();
                    break;
                case "3":
                    // Calculating the percentage scores for each score
                    try {
                        calculateAllScores(scores);
                        System.out.println("Scores successfully calculated");
                    } catch (ScoreCalculationException e) {
                        e.printStackTrace();
                        System.out.println("Score calculation error, one of the scores must have had a divide by 0 error");
                    }
                    break;
                case "4": printScores(scores);
                    break;
                case "5": printScoresWithPercent(scores);
                    break;
                case "6": saveScores(scores);
                    break;
                case "7": DataIO.saveScoresAsObjects(scores);
                    break;
                case "8": DataIO.saveObjectPassFailScores();
                    break;
                case "9":
                    performAggregations();
                    break;
                case "10":
                    // Ending the program
                    enterDatabaseMenu();
                    break;
                case "11":
                    // Ending the program
                    System.exit(0);
            }
        }
    }

    /**
     * Method for printing out the database menu for performing actions
     * withing the postgres database
     */
    public static void enterDatabaseMenu() {
        /*
         * Precondition: None
         * Postcondition: May have connected to the database and created tables or inserted data
         */

        // Initializing done to false so the while loop can be broken
        boolean done = false;

        // Main loop
        while (!done) {
            // Pringing out the database scores menu
            System.out.println("============Database Actions============");
            System.out.println("1. Connect to database");
            System.out.println("2. Create the college_athletes table");
            System.out.println("3. Insert data into college_athletes table");
            System.out.println("4. Create the person table");
            System.out.println("5. Insert data into person table");
            System.out.println("6. Create the patient table");
            System.out.println("7. Insert data into patient table");
            System.out.println("8. Print out list of the 5 youngest people");
            System.out.println("9. Print out list of 5 people born after 12/05/2019 @ 5:21pm (UTC) who are concussed");
            System.out.println("10. Return to main menu");

            // Grabbing user input
            String option = in.nextLine();

            // Calling the appropriate action
            switch (option) {
                case "1":
                    Database.connect();
                    break;
                case "2":
                    Database.createConcussionTable();
                    break;
                case "3":
                    Database.insertDataIntoConcussionTable(concussionData);
                    break;
                case "4":
                    Database.createPersonTable();
                    break;
                case "5":
                    Database.insertPersonDataIntoPersonTable(personData);
                    break;
                case "6":
                    Database.createPatientTable();
                    break;
                case "7":
                    Database.insertPatientDataIntoPersonTable(patientData);
                    break;
                case "8":
                    Database.selectOrderedPersonTable();
                    break;
                case "9":
                    Database.selectPatientsWhoHaveLateBirthday();
                    break;
                case "10":
                    done = true;
                    break;
            }

        }
    }

    /**
     * Method for printing out the main menu for entering scores
     */
    public static void enterScoresMenu() {
        /*
         * Precondition: None
         * Postcondition: The user will have enters an integer between or including 1 and 4.
         */

        // Initializing done to false so the while loop can be broken
        boolean done = false;

        // Main loop
        while (!done) {
            // Pringing out the enter scores menu
            System.out.println("============Enter Scores============");
            System.out.println("1. Enter scores for image test");
            System.out.println("2. Enter scores for grid test");
            System.out.println("3. Enter scores for card test");
            System.out.println("4. Return to main menu");

            // Grabbing user input
            String option = in.nextLine();

            // Calling the appropriate action
            switch (option) {
                case "1": enterImageScore();
                    break;
                case "2": enterGridScore();
                    break;
                case "3": enterCardScores();
                    break;
                case "4": done = true;
                    break;
            }
        }
    }

    /**
     * Method for entering the scores from the image test
     */
    public static void enterImageScore() {
        /*
         * Precondition: An array list of RoundCalculator objects is initialized with a ImageScore object within
         * the [0] index of the array.
         * Postcondition: The user will have entered 2 integers those values will be saved within the CardScore object.
         */

        System.out.println("Enter number user got correct: ");
        String numCorrect = in.nextLine();
        System.out.println("Enter number user missed: ");
        String numMissed = in.nextLine();
        ((ImageScore)scores.get(0).getObject()).setNumCorrect(Integer.parseInt(numCorrect));
        ((ImageScore)scores.get(0).getObject()).setNumMissed(Integer.parseInt(numMissed));
    }

    /**
     * Method for entering the scores from the grid test
     */
    public static void enterGridScore() {
        /*
         * Precondition: An array list of RoundCalculator objects is initialized with a GridScore object within
         * the [1] index of the array.
         * Postcondition: The user will have entered 2 integers and they will be saved within the CardScore object.
         */

        System.out.println("Enter number user got correct: ");
        String numCorrect = in.nextLine();
        System.out.println("Enter total number of trials: ");
        String totalPossible = in.nextLine();
        ((GridScore)scores.get(1).getObject()).setNumCorrect(Integer.parseInt(numCorrect));
        ((GridScore)scores.get(1).getObject()).setTotalPossible(Integer.parseInt(totalPossible));
    }

    /**
     * Method for entering the scores from the card test
     */
    public static void enterCardScores() {
        /*
         * Precondition: An array list of RoundCalculator objects is initialized with a CardScore object within
         * the [2] index of the array.
         * Postcondition: The user will have entered 2 integers and 1 string and those values will be saved in
         * the CardScore object.
         */

        System.out.println("Enter number user got correct: ");
        String numCorrect = in.nextLine();
        System.out.println("Enter number user got wrong: ");
        String numWrong = in.nextLine();
        System.out.println("Enter the card the user choose: ");
        String card = in.nextLine();
        ((CardScore)scores.get(2).getObject()).setNumCorrect(Integer.parseInt(numCorrect));
        ((CardScore)scores.get(2).getObject()).setNumWrong(Integer.parseInt(numWrong));
        ((CardScore)scores.get(2).getObject()).setCard(card);
    }

    /**
     * Method for modifying the number of rounds for each test
     */
    public static void enterRoundsMenu() {
        /*
         * Precondition: There exists a scores ArrayList which contains 3 objects, one of an ImageScore object,
         * another of a GridScore object and a third of a CardScore object.
         * Postcondition: The user will have enters an integer between or including 1 and 7 and the number of rounds
         * for each test may or may not have been modified depending on the users inputs.
         */

        // Setting done to false so the loop will continue
        boolean done = false;

        // Main loop for the rounds modification menu
        while (!done) {
            // Pringing out the enter rounds menu
            System.out.println("============Enter Rounds============");
            System.out.println("1. Increment rounds for image test");
            System.out.println("2. Decrease rounds for image test");
            System.out.println("3. Increment rounds for grid test");
            System.out.println("4. Decrement rounds for grid test");
            System.out.println("5. Increment rounds for card test");
            System.out.println("6. Decrement rounds for card test");
            System.out.println("7. Return to main menu");

            // Grabbing user input
            String option = in.nextLine();

            // Calling the appropriate action
            switch (option) {
                case "1": scores.get(0).increaseRounds();
                    System.out.println("Current rounds for Image Test: " + scores.get(0).getRounds());
                    break;
                case "2": scores.get(0).decreaseRounds();
                    System.out.println("Current rounds for Image Test: " + scores.get(0).getRounds());
                    break;
                case "3": scores.get(1).increaseRounds();
                    System.out.println("Current rounds for Grid Test: " + scores.get(1).getRounds());
                    break;
                case "4": scores.get(1).decreaseRounds();
                    System.out.println("Current rounds for Grid Test: " + scores.get(1).getRounds());
                    break;
                case "5": scores.get(2).increaseRounds();
                    System.out.println("Current rounds for Card Test: " + scores.get(2).getRounds());
                    break;
                case "6": scores.get(2).decreaseRounds();
                    System.out.println("Current rounds for Card Test: " + scores.get(2).getRounds());
                    break;
                case "7": done = true;
            }
        }
    }

    /**
     * Method that calculates all the percentage scores
     * @param scores List of Scores
     * @throws ScoreCalculationException
     */
    public static void calculateAllScores(List<RoundCalculator> scores) throws ScoreCalculationException {
        // Looping through each score in the list and calculating the percentage score
        for (RoundCalculator rc : scores) {
            Score score = (Score) rc.getObject();
            score.calculateScore();
            if (((Double)score.getPercentageScore()).isNaN()) {
                // Unchecked exception occurred, time to throw the exception
                // If score is instance of CardScore and throw a ScoreCalculationException
                // with the appropriate message
                if (score instanceof CardScore) {
                    if (((CardScore) score).getNumCorrect() + ((CardScore) score).getNumWrong() == 0) {
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
     * Functional interface used to make lambda
     */
    interface LambdaInterface {
        long compute(long x, long y);
    }

    /**
     * Method that implements the compute method from the above interface
     * @param x
     * @param y
     * @param intf
     * @return
     */
    private long compute(long x, long y, LambdaInterface intf) {
        return intf.compute(x, y);
    }

    /**
     * Method that performs stream transformations on the concussion dataset
     */
    public static void performAggregations() {
        /*
         * Precondition: data is a fully populated list of ConcussionDataRow objects
         *
         * Postcondition: Results of the below calculations are printed on the screen
         */

        // Calculating number of people concussed in 2000
        long numFemalesConcussed = concussionData.stream()
                .filter(i -> i.getGender().equals("Female"))
                .filter(i -> i.isConcussed())
                .mapToInt(i -> i.getCount())
                .sum();

        // Calculating number of males concussed
        long numMalesConcussed = concussionData.stream()
                .filter(i -> i.getGender().equals("Male"))
                .filter(i -> i.isConcussed())
                .mapToInt(i -> i.getCount())
                .sum();

        // Using a lambda to calculate the total number of people concussed
        LambdaInterface additionOperation = (long x, long y) -> x + y;
        long totalConcussed = additionOperation.compute(numFemalesConcussed, numMalesConcussed);

        // Printing out the results of the calculations
        System.out.println("Number of people concussed in the year 2000: " + totalConcussed);
        System.out.println("Total number of females concussed in dataset: " + numFemalesConcussed);
        System.out.println("Total number of males concussed in dataset: " + numMalesConcussed);
    }
}
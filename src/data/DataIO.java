/**
 * CS-622 HW 2
 * DataIO.java
 * Purpose: Interacting with external text files
 *
 * @author Jake Stephens
 * @version 1.0 2/28/2020
 */
package data;

import scores.PassFail;
import scores.RoundCalculator;
import scores.Score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DataIO {

    // Initializing the file locations
    static String outputObjectScoresFile = "score_objects_scores.dat";
    static String outputObjectPassFailFile = "score_objects_pass_fail.dat";
    static String inputConcussionDataFile = "concussion_data.txt";
    static String inputPersonDataRowFile = "person_data.txt";
    static String inputPatientDataRowFile1 = "patient_data_1.txt";
    static String inputPatientDataRowFile2 = "patient_data_2.txt";

    // Initializing the synchronized patient list
    static List<PatientDataRow> patientDataRows = Collections.synchronizedList(new ArrayList<>());

    /**
     * Method that reads in a text file which contains many rows of concussion data and parses
     * it all into a List.
     */
    public static List<ConcussionDataRow> importConcussionData() {
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
     * This method imports the person data into an array list of PersonDataRow objects
     * @return Array list of PersonDataRow objects
     */
    public static List<PersonDataRow> importPersonData() {
        /*
         * Precondition: inputPersonDataRowFile is a file that contains person data separated by |
         *
         * Postcondition: An array list of PersonDataRow objects will be returned. The size of the
         * array will be the same as the number of rows in the text file
         */

        // Creating an empty list of PersonDataRow
        List<PersonDataRow> data = new ArrayList();
        try {
            Scanner scan = new Scanner(new File(inputPersonDataRowFile));
            // While a new line exists in the data file, read the next line
            while (scan.hasNextLine()) {
                // Split the line on white space and create a new PersonDataRow object and insert it into the list
                String[] tokens = scan.nextLine().split("\\s+");
                data.add(new PersonDataRow(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3]));
            }
            // Catching a file not found exception
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Returning the fully populated list of ConcussionDataRows
        return data;
    }

    /**
     * This method orchestrates the parsing of data from text files into 1 synchronized array list
     */
    public static List<PatientDataRow> importPatientData() {

        DataImportThread thread = new DataImportThread(inputPatientDataRowFile1);
        thread.start();
        importPatientData(inputPatientDataRowFile2);
        return patientDataRows;
    }

    /**
     * This method imports the patient data into an array list of PatientDataRow objects
     */
    public static void importPatientData(String fileName) {
        /*
         * Precondition: inputPersonDataRowFile is a file that contains person data separated by |
         *
         * Postcondition: An array list of PatientDataRow objects will be returned. The size of the
         * array will be the same as the number of rows in the text file
         */

        try {
            Scanner scan = new Scanner(new File(fileName));
            // While a new line exists in the data file, read the next line
            while (scan.hasNextLine()) {
                // Split the line on white space and create a new PersonDataRow object and insert it into the list
                String[] tokens = scan.nextLine().split("\\s+");
                patientDataRows.add(new PatientDataRow(Integer.parseInt(tokens[0]), tokens[1], Boolean.parseBoolean(tokens[2])));
            }
            // Catching a file not found exception
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Private Thread class for reading in a text file of data and
     * populating an array list with PatientDataRow objects
     */
    private static class DataImportThread extends Thread {

        // Instance variable for the filename
        private String fileName;

        // Constructor for the private class
        public DataImportThread(String fileName) {
            this.fileName = fileName;
        }

        // The implementation of the run method
        @Override
        public void run() {
            try {
                Scanner in = new Scanner(new File(this.fileName));
                // While a new line exists in the data file, read the next line
                while (in.hasNextLine()) {
                    // Split the line on white space and create a new PersonDataRow object and insert it into the list
                    String[] tokens = in.nextLine().split("\\s+");
                    patientDataRows.add(new PatientDataRow(Integer.parseInt(tokens[0]), tokens[1], Boolean.parseBoolean(tokens[2])));
                }
                // Catching a file not found exception
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
}

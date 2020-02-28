/**
 * CS-622 HW 2
 * Database.java
 * Purpose: Interacting with an external database using JDBC
 *
 * @author Jake Stephens
 * @version 1.0 2/28/2020
 */
package data;

import java.sql.*;
import java.util.List;

public class Database {

    // Initializing the variables used to connect to the external database
    private static final String url = "jdbc:postgresql://localhost/concussion";
    private static final String user = "jake";
    private static final String password = "";

    /**
     * This method is specifically for connecting to the postgres database
     * @return Connection to postgres
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * This method is for creating the concussion table in the database
     * if it doesn't already exist
     */
    public static void createConcussionTable() {
        /*
         * Precondition: The connect() method has already been called and the connection to the
         * database was successful
         * Postcondition: The college_athletes table will have been created successfully in the database
         */

        // Attempting to connect to the database and initialize a statement object
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            // The sql to be executed
             String sql = "CREATE TABLE IF NOT EXISTS college_athletes (" +
                            "gender VARCHAR(10) NOT NULL," +
                            "sport VARCHAR(30) NOT NULL," +
                            "year INTEGER NOT NULL," +
                            "concussed BOOL NOT NULL," +
                            "count INTEGER NOT NULL)";

             // Executing the sql
            statement.executeUpdate(sql);
            System.out.println("Created the college_athletes table successfully");
        }
        // Catching any SQL exception
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is for creating the person table in the database
     * if it doesn't already exist
     */
    public static void createPersonTable() {
        /*
         * Precondition: The connect() method has already been called and the connection to the
         * database was successful
         * Postcondition: The person table will have been created successfully in the database
         */

        // Attempting to connect to the database and initialize a statement object
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            // The sql to be executed
            String sql = "CREATE TABLE IF NOT EXISTS person (" +
                    "pid INTEGER NOT NULL," +
                    "name VARCHAR(50) NOT NULL," +
                    "email VARCHAR(100) NOT NULL," +
                    "birthday VARCHAR(30) NOT NULL)";

            // Executing the sql
            statement.executeUpdate(sql);
            System.out.println("Created the person table successfully");
        }
        // Catching any SQL exception
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is for creating the patient table in the database
     * if it doesn't already exist
     */
    public static void createPatientTable() {
        /*
         * Precondition: The connect() method has already been called and the connection to the
         * database was successful
         * Postcondition: The patient table will have been created successfully in the database
         */

        // Attempting to connect to the database and initialize a statement object
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            // The sql to be executed
            String sql = "CREATE TABLE IF NOT EXISTS patient (" +
                    "pid INTEGER NOT NULL," +
                    "last_visit VARCHAR(10) NOT NULL," +
                    "concussed BOOL NOT NULL)";

            // Executing the sql
            statement.executeUpdate(sql);
            System.out.println("Created the college_athletes table successfully");
        }
        // Catching any SQL exception
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for inserting data from the dataset in the external text file, into
     * the college_athletes table in the database
     * @param data
     */
    public static void insertDataIntoConcussionTable(List<ConcussionDataRow> data) {
        /*
         * Precondition1: The connect() method has already been called and the connection to the
         * database was successful
         * Precondition2: There exists a college_athletes table in the database already
         * Precondition3: data is a list of ConcussionDataRow objects
         * Postcondition: The college_athletes table will have been inserted into as many
         * times as there are ConcussionDataRow objects passed in
         */


        // Declaring the sql to be used to insert the data
        String sql = "INSERT INTO college_athletes VALUES(?, ?, ?, ?, ?)";
        int insertCount = 0;

        // Attempting to connect to the database and initialize the prepared statement object
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Looping through every row in the dataset
            for (ConcussionDataRow row : data) {

                // Setting the value for each field to be inserted
                ps.setString(1, row.getGender());
                ps.setString(2, row.getSport());
                ps.setInt(3, row.getYear());
                ps.setBoolean(4, row.isConcussed());
                ps.setInt(5, row.getCount());

                // Inserting the row into the table
                ps.executeUpdate();

                // Incrementing the count
                insertCount++;
            }
            // Printing out that the insertions were successful
            System.out.println("Inserted " + insertCount + " rows successfully");
        }
        // Catching any SQL exception
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for inserting data from the person dataset in the external text file, into
     * the person table in the database
     * @param data
     */
    public static void insertPersonDataIntoPersonTable(List<PersonDataRow> data) {
        /*
         * Precondition1: The connect() method has already been called and the connection to the
         * database was successful
         * Precondition2: There exists a person table in the database already
         * Precondition3: data is a list of PersonDataRow objects
         * Postcondition: The person table will have been inserted into as many
         * times as there are PersonDataRow objects passed in
         */

        // Declaring the sql to be used to insert the data
        String sql = "INSERT INTO person VALUES(?, ?, ?, ?)";
        int insertCount = 0;

        // Attempting to connect to the database and initialize the prepared statement object
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Looping through every row in the dataset
            for (PersonDataRow row : data) {

                // Setting the value for each field to be inserted
                ps.setInt(1, row.getPid());
                ps.setString(2, row.getName());
                ps.setString(3, row.getEmail());
                ps.setString(4, row.getBirthday());

                // Inserting the row into the table
                ps.executeUpdate();

                // Incrementing the count
                insertCount++;
            }
            // Printing out that the insertions were successful
            System.out.println("Inserted " + insertCount + " rows successfully");
        }
        // Catching any SQL exception
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for inserting data from the patient dataset in the external text file, into
     * the patient table in the database
     * @param data
     */
    public static void insertPatientDataIntoPersonTable(List<PatientDataRow> data) {
        /*
         * Precondition1: The connect() method has already been called and the connection to the
         * database was successful
         * Precondition2: There exists a person table in the database already
         * Precondition3: data is a list of PatientDataRow objects
         * Postcondition: The patient table will have been inserted into as many
         * times as there are PatientDataRow objects passed in
         */

        // Declaring the sql to be used to insert the data
        String sql = "INSERT INTO patient VALUES(?, ?, ?)";
        int insertCount = 0;

        // Attempting to connect to the database and initialize the prepared statement object
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Looping through every row in the dataset
            for (PatientDataRow row : data) {

                // Setting the value for each field to be inserted
                ps.setInt(1, row.getPid());
                ps.setString(2, row.getLastVisit());
                ps.setBoolean(3, row.isConcussed());

                // Inserting the row into the table
                ps.executeUpdate();

                // Incrementing the count
                insertCount++;
            }
            // Printing out that the insertions were successful
            System.out.println("Inserted " + insertCount + " rows successfully");
        }
        // Catching any SQL exception
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for printing out the 5 youngest people from the person table
     */
    public static void selectOrderedPersonTable() {
        /*
         * Precondition1: The connect() method has already been called and the connection to the
         * database was successful
         * Precondition2: There exists a person table in the database already with rows inserted
         * Postcondition: 5 person instances will be printed out
         */

        String sql = "SELECT name, email, birthday FROM person ORDER BY birthday DESC LIMIT 5";

        try (Connection connection = DriverManager.getConnection(url);
             Statement query = connection.createStatement()) {

            // Executing the query
            ResultSet rs = query.executeQuery(sql);
            System.out.println("5 youngest people in the person table sorted from oldest to youngest:");

            // Fetching the results and printing them
            while (rs.next()) {
                String name = rs.getString("name");

                System.out.println("Person: " + name);
            }
        }
        // Catching any SQL exception
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for printing out the 5 people from the person/patient table
     * who were born after 12/05/2019 @ 5:21pm (UTC)
     */
    public static void selectPatientsWhoHaveLateBirthday() {
        /*
         * Precondition1: The connect() method has already been called and the connection to the
         * database was successful
         * Precondition2: There exists a person table and patient table in the database already with rows inserted
         * and there are matching pids on both tables
         * Postcondition: 5 person instances will be printed out
         */

        String sql = "SELECT name " +
                "FROM person pe, patient pa " +
                "WHERE pe.pid = pa.pid " +
                "AND " +
                "pa.concussed = 'false' " +
                "AND " +
                "pe.birthday > '1575566519' " +
                "LIMIT 5";

        try (Connection connection = DriverManager.getConnection(url);
             Statement query = connection.createStatement()) {

            // Executing the query
            ResultSet rs = query.executeQuery(sql);
            System.out.println("5 people who are concussed and were born after 12/05/2019 @ 5:21pm (UTC):");

            // Fetching the results and printing them
            while (rs.next()) {
                String name = rs.getString("name");

                System.out.println("Person: " + name);
            }
        }
        // Catching any SQL exception
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

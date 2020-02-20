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

}

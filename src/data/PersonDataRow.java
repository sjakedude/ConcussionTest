/**
 * CS-622 HW 2
 * PersonDataRow.java
 * Purpose: Class for holding the data from the external text file dataset of people
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
 */
package data;

public class PersonDataRow {

    private int pid;
    private String name;
    private String email;
    private String birthday;

    public PersonDataRow(int pid, String name, String email, String birthday) {
        this.pid = pid;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }
}

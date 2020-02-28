/**
 * CS-622 HW 2
 * PatientDataRow.java
 * Purpose: Class for holding the data from the external text file dataset of patients who
 * were seen for concussion-like symptoms
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
 */
package data;

public class PatientDataRow {

    private int pid;
    private String lastVisit;
    private boolean concussed;

    public PatientDataRow(int pid, String lastVisit, boolean concussed) {
        this.pid = pid;
        this.lastVisit = lastVisit;
        this.concussed = concussed;
    }

    public int getPid() {
        return pid;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public boolean isConcussed() {
        return concussed;
    }
}

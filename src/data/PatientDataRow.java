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

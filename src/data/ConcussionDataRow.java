package data;

public class ConcussionDataRow {

    // Initializing instance variables
    private String gender;
    private String sport;
    private int year;
    private boolean concussed;
    private int count;

    // Constructor for the ConcussionDataRow class
    public ConcussionDataRow(String gender, String sport, String year, String concussed, String count) {
        this.gender = gender;
        this.sport = sport;
        this.year = Integer.parseInt(year);
        this.concussed = determineConcussed(concussed);
        this.count = Integer.parseInt(count);
    }

    private boolean determineConcussed(String concussed) {
        if (concussed.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    public String getGender() {
        return gender;
    }

    public String getSport() {
        return sport;
    }

    public int getYear() {
        return year;
    }

    public boolean isConcussed() {
        return concussed;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "ConcussionDataRow{" +
                "gender='" + gender + '\'' +
                ", sport='" + sport + '\'' +
                ", year=" + year +
                ", concussed=" + concussed +
                ", count=" + count +
                '}';
    }
}

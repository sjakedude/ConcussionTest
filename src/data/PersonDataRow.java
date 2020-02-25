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

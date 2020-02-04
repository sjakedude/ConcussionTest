package scores;

public abstract class Score {

    protected double percentageScore;

    public Score() {
    }

    public abstract String printScore();
    public abstract void calculateScore();

    public double getPercentageScore() {
        return this.percentageScore;
    }
}

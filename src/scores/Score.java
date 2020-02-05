/**
 * Created by: Jake Stephens
 * Abstract class that contains the method signatures for the child classes to implement
 */

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

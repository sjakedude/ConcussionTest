/**
 * CS-622 HW 2
 * Score.java
 * Purpose: Abstract class that contains the method signatures for the child classes to implement
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
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

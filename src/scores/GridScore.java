/**
 * CS-622 HW 2
 * GridScore.java
 * Purpose: Holds the users score on the grid test
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
 */
package scores;

public class GridScore extends Score {

    private int numCorrect;
    private int totalPossible;

    public GridScore() {
    }

    public GridScore(int numCorrect, int totalPossible) {
        this.numCorrect = numCorrect;
        this.totalPossible = totalPossible;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public void setTotalPossible(int totalPossible) {
        this.totalPossible = totalPossible;
    }

    public int getNumCorrect() { return this.numCorrect; }

    public int getTotalPossible() { return this.totalPossible; }

    @Override
    public String printScore() {
        return "User got " + numCorrect + " out of " + totalPossible + " in grid test";
    }

    @Override
    public void calculateScore() {
        this.percentageScore = 100 * ((double)numCorrect / (double)totalPossible);
    }

    public String toString() {
        return "The user's card score numCorrect: " + this.numCorrect + " and totalPossible: " + this.totalPossible;
    }
}

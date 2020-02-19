/**
 * CS-622 HW 2
 * CardScore.java
 * Purpose: Holds the users score on the card test
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
 */
package scores;

public class CardScore extends Score {

    private int numCorrect;
    private int numWrong;
    private String card;

    public CardScore() {
    }

    public CardScore(int numCorrect, int numWrong, String card) {
        this.numCorrect = numCorrect;
        this.numWrong = numWrong;
        this.card = card;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public void setNumWrong(int numWrong) {
        this.numWrong = numWrong;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getNumCorrect() { return this.numCorrect; }

    public int getNumWrong() { return this.numWrong; }

    public String getCard() { return this.card; }

    @Override
    public String printScore() {
        return "User got " + this.numCorrect + " out of " + this.numWrong + " rounds in card test";
    }

    @Override
    public void calculateScore() {
        this.percentageScore = 100 * ((double)this.numCorrect / ((double) this.numCorrect + (double)this.numWrong));
    }
}

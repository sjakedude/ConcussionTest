/**
 * Created by: Jake Stephens
 * Class that contains the scores for the card test
 */
package scores;

public class CardScore extends Score {

    private int numCorrect;
    private int totalRounds;
    private String card;

    public CardScore() {
    }

    public CardScore(int numCorrect, int totalRounds, String card) {
        this.numCorrect = numCorrect;
        this.totalRounds = totalRounds;
        this.card = card;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getNumCorrect() { return this.numCorrect; }

    public int getTotalRounds() { return this.totalRounds; }

    public String getCard() { return this.card; }

    @Override
    public String printScore() {
        return "User got " + this.numCorrect + " out of " + this.totalRounds + " rounds in card test";
    }

    @Override
    public void calculateScore() {
        this.percentageScore = 100 * ((double)this.numCorrect / (double)this.totalRounds);
    }
}

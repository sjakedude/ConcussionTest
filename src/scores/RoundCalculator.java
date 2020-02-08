/**
 * Created by: Jake Stephens
 * Generic class that helps keep track of the number of rounds
 */
package scores;

public class RoundCalculator<S> {

    // The instance variables of this class
    private S object;
    private int rounds = 0;

    // Empty constructor
    public RoundCalculator() {
    }

    // Constructor for creating a RoundCalculator object
    public RoundCalculator(S o) {
        this.object = o;
        this.rounds = 0;
    }

    // Method that returns a number of rounds
    public int getRounds() {
        return this.rounds;
    }

    // Method to increment the number of rounds by 1
    public void increaseRounds() {
        this.rounds = rounds + 1;
    }

    // Method that returns the object
    public S getObject() {
        return this.object;
    }

    // Method to print out the object and the number of rounds
    public String toString() {
        return this.object.toString() + " with rounds: " + this.rounds;
    }
}

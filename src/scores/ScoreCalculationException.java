/**
 * Created by: Jake Stephens
 * User defined unchecked Exception class
 */

package scores;

public class ScoreCalculationException extends Exception {
    public ScoreCalculationException(String errorMessage) {
        super(errorMessage);
    }
}

/**
 * CS-622 HW 2
 * ScoreCalculationException.java
 * Purpose: User defined unchecked exception class
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
 */
package scores;

public class ScoreCalculationException extends Exception {
    public ScoreCalculationException(String errorMessage) {
        super(errorMessage);
    }
}

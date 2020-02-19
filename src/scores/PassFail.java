/**
 * CS-622 HW 2
 * PassFail.java
 * Purpose: Generic class that helps keep track of if the Score object
 * is a passing score or not
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
 */
package scores;

import java.io.Serializable;

public class PassFail<S> implements Serializable {

    // The instance variables of this class
    private S object;
    private boolean pass;

    // Constructor for creating a PassFail object
    public PassFail(S o, boolean pass) {
        this.object = o;
        this.pass = pass;
    }

    // Method to print out the object and if it was a passing score
    public String toString() {
        if (pass) {
            return this.object.toString() + " and the score is passing.";
        } else {
            return this.object.toString() + " and the score is not passing.";
        }
    }
}

/**
 * CS-622 HW 2
 * ConcussionTest_Test.java
 * Purpose: Junit test class for the ConcussionTest application
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
 */
package junit;

import data.DataIO;
import driver.ConcussionTest;
import org.junit.Test;
import scores.*;
import scores.ScoreCalculationException;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConcussionTest_Test {

    ConcussionTest testConcussionTest = new ConcussionTest();
    Score testCardScore = new CardScore(0, 0, "Queen of hearts");
    Score testGridScore = new GridScore(5, 8);
    Score testImageScore = new ImageScore(4, 5);
    List<RoundCalculator> testScores = new ArrayList<>();

    /**
     * This Test will make sure that the correct exception is thrown when there is
     * a problem calculating a users score.
     */
    @Test
    public void testCalculateAllScores() {
        testScores.add(new RoundCalculator(testCardScore));
        testScores.add(new RoundCalculator(testGridScore));
        testScores.add(new RoundCalculator(testImageScore));

        boolean exceptionThrown = false;

        // Calling the method expecting the exception to be thrown
        try {
            testConcussionTest.calculateAllScores(testScores);
        } catch (ScoreCalculationException e) {
            // Exception caught as expected
            exceptionThrown = true;
        } finally {
            assertEquals(true, exceptionThrown);
        }

        // Removing the score that was causing the exceptopn to be thrown and
        // resetting the exceptionThrown flag to false
        testScores.remove(0);
        exceptionThrown = false;

        // Calling the method expecting the exception not to be thrown
        try {
            testConcussionTest.calculateAllScores(testScores);
        } catch (ScoreCalculationException e) {
            // Exception caught as expected
            exceptionThrown = true;
        } finally {
            assertEquals(false, exceptionThrown);
        }
    }

    /**
     * This Test will make sure that the generic class RoundCalculator
     * is working as expected for increasing the number of rounds.
     */
    @Test
    public void testRoundCalculatorIncrement() {
        RoundCalculator testImageRounds = new RoundCalculator(testImageScore);
        testImageRounds.increaseRounds();
        testImageRounds.increaseRounds();
        testImageRounds.increaseRounds();

        assertEquals(4, testImageRounds.getRounds());
        assertEquals("The user's image score numCorrect: 4 and numMissed: 5 with rounds: 4", testImageRounds.toString());
    }

    /**
     * This Test will make sure that the generic class RoundCalculator
     * is working as expected for decreasing the number of rounds.
     */
    @Test
    public void testRoundCalculatorDecrement() {
        RoundCalculator testImageRounds = new RoundCalculator(testImageScore);
        testImageRounds.increaseRounds();
        testImageRounds.increaseRounds();
        testImageRounds.increaseRounds();
        testImageRounds.decreaseRounds();

        assertEquals(3, testImageRounds.getRounds());
        assertEquals("The user's image score numCorrect: 4 and numMissed: 5 with rounds: 3", testImageRounds.toString());
    }
}

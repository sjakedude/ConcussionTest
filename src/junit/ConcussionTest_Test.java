package junit;

import driver.ConcussionTest;
import org.junit.Test;
import scores.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConcussionTest_Test {

    ConcussionTest testConcussionTest = new ConcussionTest();
    Score testCardScore = new CardScore(3, 0, "Queen of hearts");
    Score testGridScore = new GridScore(5, 8);
    Score testImageScore = new ImageScore(4, 5);
    List<Score> testScores = new ArrayList<>();

    /**
     * This Test will make sure that the correct exception is thrown when there is
     * a problem calculating a users score.
     */
    @Test
    public void testCalculateAllScores() {
        testScores.add(testCardScore);
        testScores.add(testGridScore);
        testScores.add(testImageScore);

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
        testScores.remove(testCardScore);
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
}

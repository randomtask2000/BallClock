package com.emilionicoli.ballclock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test results printed to stdout with known results
 */
public class ApplicationSystemOutTest {

    private final int EXITCODE_ERROR = -1;
    private final int EXITCODE_OK = 0;
    private final int EXITCODE_EXCEPTION = 1;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void outputTest_30ballsAnd325minutes_happyPath() {
        String[] args = {"-balls", "30", "-minutes", "325"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_OK));
        assertThat("{\"Minutes\":[],\"FiveMin\":[22,13,25,3,7],\"Hours\":[6,12,17,4,15],\"Main\":[11,5,26,18,2,30,19,8,24,10,29,20,16,21,28,1,23,14,27,9]}\n", is(outContent.toString()));
    }

    @Test
    public void balls45ballsAnd333minutes_happyPath() {
        String[] args = {"-balls", "45", "-minutes", "333"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_OK));
        assertThat("{\"Minutes\":[22,3,33],\"FiveMin\":[9,25,5,6,44,12],\"Hours\":[17,37,23,2,24],\"Main\":[42,34,18,27,26,15,7,19,41,21,32,38,29,16,40,13,10,30,14,1,35,28,45,31,20,39,11,8,36,4,43]}\n", is(outContent.toString()));
    }

    @Test(expected = AssertionError.class)
    public void changedLastNumberToZero_sadPath() {
        String[] args = {"-balls", "45", "-minutes", "333"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_OK));
        assertThat("{\"Minutes\":[22,3,33],\"FiveMin\":[9,25,5,6,44,12],\"Hours\":[17,37,23,2,24],\"Main\":[42,34,18,27,26,15,7,19,41,21,32,38,29,16,40,13,10,30,14,1,35,28,45,31,20,39,11,8,36,4,0]}\n", is(outContent.toString()));
    }

    @Test(expected = AssertionError.class)
    public void badInput_badPath() {
        String[] args = {"-balls", "45", "-minutes", "333"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_OK));
        assertThat("{Oh No!}\n", is(outContent.toString()));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}

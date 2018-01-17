package com.emilionicoli.ballclock;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class ApplicationArgumentsTest {

    private final int EXITCODE_ERROR = -1;
    private final int EXITCODE_OK = 0;
    private final int EXITCODE_EXCEPTION = 1;

    @Test
    public void noArguments_happyPath() {
        String[] args = {"-h"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_OK));
    }

    @Test
    public void balls_happyPath() {
        String[] args = {"-balls", "27", "-verbose"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_OK));
    }

    @Test
    public void minutes_happyPath() {
        String[] args = {"-minutes", "1"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_OK));
    }

    @Test
    public void noArguments_sadPath() {
        String[] args = {};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_ERROR));
    }

    @Test
    public void missingArgument_badPath() {
        String[] args = {"-x", "0"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_EXCEPTION));
    }

    @Test
    public void badArgument_badPath() {
        String[] args = {"-x", "xx"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_EXCEPTION));
    }

    @Test
    public void badBallsArgument_badPath() {
        String[] args = {"-b", "xx"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_EXCEPTION));
    }

    @Test
    public void floatBallsArgument_badPath() {
        String[] args = {"-b", "33.33"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_EXCEPTION));
    }

    @Test
    public void tooFewBallsArgument_sadPath() {
        String[] args = {"-b", "26"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_EXCEPTION));
    }

    @Test
    public void tooManyBallsArgument_sadPath() {
        String[] args = {"-b", "128"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_EXCEPTION));
    }

    @Test
    public void floatMinutesArgument_badPath() {
        String[] args = {"-m", "1.111"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_EXCEPTION));
    }

    @Test
    public void zeroMinutesArgument_sadPath() {
        String[] args = {"-m", "0"};
        assertThat(new BallClockApplication().doMain(args), is(EXITCODE_EXCEPTION));
    }
}

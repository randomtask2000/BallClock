package com.emilionicoli.ballclock;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test known inputs and outputs
 */
public class BallClockTest {

    @Test
    public void given30Balls_expected15days_happyPath() throws Exception{
        assertThat(new BallClock(new Arguments(30)).runLoop(),is(15));
    }

    @Test
    public void given45Balls_expected378days_happyPath() throws Exception{
        assertThat(new BallClock(new Arguments(45)).runLoop(),is(378));
    }
}

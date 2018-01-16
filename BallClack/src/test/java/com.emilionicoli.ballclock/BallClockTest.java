package com.emilionicoli.ballclock;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BallClockTest {

    @Test
    public void given30Balls_returns15days_happyPath() throws Exception{
        assertThat(new BallClock(new Arguments(30)).doCycles(),is(15));
    }

    @Test
    public void given45Balls_returns378days_happyPath() throws Exception{
        assertThat(new BallClock(new Arguments(45)).doCycles(),is(378));
    }

    @Test
    public void println_happyPath() throws Exception{
        new BallClock(new Arguments(45)).run();
    }
}

package com.emilionicoli.ballclock;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class BallClock {

    Arguments arguments;
    Railable minuteRail, fiveMinuteRail, oneHourRail;
    FeedRail feedRail;
    boolean verbose;

    public BallClock(Arguments args){
        arguments = args;
        verbose = arguments.isVerbose();
        feedRail = new FeedRail(arguments.getBalls(), null);
        oneHourRail = new Rail(11, feedRail);
        fiveMinuteRail = new Rail(11, oneHourRail);
        minuteRail = new Rail(4, fiveMinuteRail);
        feedRail.setNextRail(minuteRail);
        Rail.setFeedRail(feedRail);
        Rail.setBallClock(this);
    }
    public void run(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        int days = doCycles();
        out.println(String.format("%d balls cycle after %d days.", arguments.getBalls(), days));
        stopwatch.stop();
        out.println(String.format("Completed in %s milliseconds (%.3f seconds)"
                , stopwatch.elapsed(TimeUnit.MILLISECONDS)
                , (stopwatch.elapsed(TimeUnit.MILLISECONDS)/1000d)));
    }
    public int doCycles(){
        Integer cycles = 1;
        do {
            feedRail.popToNext();
            if (feedRail.isFirstState()) { break; }
        } while ( cycles++ > 0 );

        return cycles / ( 60*24 ); // return days
    }
}

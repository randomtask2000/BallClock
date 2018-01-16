package com.emilionicoli.ballclock;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class BallClock {

    Arguments arguments;
    Rail minuteRail, fiveMinuteRail, oneHourRail, feedRail;
    boolean verbose;

    public BallClock(Arguments args){
        arguments = args;
        verbose = arguments.isVerbose();
        feedRail = new Rail(arguments.getBalls(), null);
        oneHourRail = new Rail(11, feedRail);
        fiveMinuteRail = new Rail(11, oneHourRail);
        minuteRail = new Rail(4, fiveMinuteRail);
        feedRail.setNextRail(minuteRail);
        feedRail.initFeedRail();
        Rail.setFeedRail(feedRail);
        Rail.setBallclock(this);
    }
    public void run(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        double days = doCycles();
        out.println(String.format("%d balls cycle after %.0f days."
                , arguments.getBalls(), days));
        stopwatch.stop();
        out.println(String.format("Completed in %s milliseconds (%.3f seconds)"
                , stopwatch.elapsed(TimeUnit.MILLISECONDS), stopwatch.elapsed(TimeUnit.SECONDS)));
    }
    public double doCycles(){

        Integer cycles = 1;
        do { feedRail.popToNextRail();
            if (feedRail.isFirstState()) { break; }
        } while (cycles++>0);

        return cycles / (60*24d); // return days
    }
}

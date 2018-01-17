package com.emilionicoli.ballclock;

import com.google.common.base.Stopwatch;

import static com.emilionicoli.ballclock.Utils.printJsonMinutes;
import static com.emilionicoli.ballclock.Utils.printStats;

public class BallClock {

    Arguments args;
    Railable minuteRail, fiveMinuteRail, oneHourRail;
    FeedRail feedRail;
    boolean verbose;
    int minutes;

    public BallClock(Arguments args){
        this.args = args;
        minutes = args.isMinutes() ? args.getMinutes() : 0;
        verbose = this.args.isVerbose();
        feedRail = new FeedRail(this.args.getBalls(), null);
        oneHourRail = new Rail(11, feedRail);
        fiveMinuteRail = new Rail(11, oneHourRail);
        minuteRail = new Rail(4, fiveMinuteRail);
        feedRail.setNextRail(minuteRail);
        Rail.setFeedRail(feedRail);
        Rail.setBallClock(this);
    }

    public void doBalls() throws BallClockException {
        if (!args.isBall()) throw new BallClockException("Please add ball parameter between 27 and 127");
        Stopwatch stopwatch = Stopwatch.createStarted();
        int days = runLoop();
        stopwatch.stop();
        printStats(args, stopwatch, days);
    }

    public void doMinutes() throws BallClockException {
        if (!args.isBall() || !args.isMinutes())
            throw new BallClockException("Please add ball parameter between 27 and 127 and minutes greater than 0");

        int days = runForMinutes();

        printJsonMinutes(this);
    }



    public int runLoop(){
        int cycles = 1;
        do {
            feedRail.popToNext();
            if (feedRail.isFirstState()) { break; }
        } while ( cycles++ > 0 );

        return cycles / ( 60*24 ); // return days
    }

    public int runForMinutes(){
        int minutes = args.getMinutes();
        int cycles = 1;

        while ( cycles < minutes+1 ) {
            feedRail.popToNext();
            cycles++;
        }

        return cycles / ( 60*24 ); // return days
    }

}

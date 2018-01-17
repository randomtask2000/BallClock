package com.emilionicoli.ballclock;

import com.google.common.base.Stopwatch;

import static com.emilionicoli.ballclock.Utils.printJsonMinutes;
import static com.emilionicoli.ballclock.Utils.printStats;

/**
 * BallClock application contains three rails and a feeding basin or rail.  The latter supplies the balls for each rail. The purpose of the rails are to tell time in 1, 5 minute and one hour increments.
 */
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
    }

    /**
     * Manage running of Balls illustration and stats output
     * @throws BallClockException
     */
    public void doBalls() throws BallClockException {
        if (!args.isBall()) throw new BallClockException("Please add ball parameter between 27 and 127");
        Stopwatch stopwatch = Stopwatch.createStarted();
        int days = runLoop();
        stopwatch.stop();
        printStats(args, stopwatch, days);
    }

    /**
     * Manage running of Minutes illustration and stats output in form of Json
     * @throws BallClockException
     */
    public void doMinutes() throws BallClockException {
        if (!args.isBall() || !args.isMinutes())
            throw new BallClockException("Please add ball parameter between 27 and 127 and minutes greater than 0");

        int days = runForMinutes();

        printJsonMinutes(this);
    }

    /**
     * Manage iteration based on finding the first order of the feeding rail the application was initialized on.
     * @return number of days in operation
     */
    public int runLoop(){
        int cycles = 1;
        do {
            feedRail.popToNext();
            if (feedRail.isFirstState()) { break; }
        } while ( cycles++ > 0 );

        return cycles / ( 60*24 ); // return days
    }

    /**
     * Manage the iteration limited by a given number of minutes.
     * @return number of days in operation
     */
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

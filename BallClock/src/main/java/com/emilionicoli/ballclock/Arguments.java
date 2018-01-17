package com.emilionicoli.ballclock;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;

/**
 * This class is used to parse and pass the args that are entered in the command line at instantiation of the program.
 * It is also a good mocking tool for regression testing.
 */
public class Arguments {
    /**
     * Balls and Minutes are the most important arguments the BallClock runs on
     */
    private int balls = 0;
    private int minutes = 0;

    private final String minutes_usage = "number of minutes to doBalls needs to be greater then zero";
    private final String balls_usage = "number of balls needs to be between 27 to 127";

    public Arguments() {}
    public Arguments(int balls) { this.balls = balls; }
    public Arguments(int balls, int minutes) { this.balls = balls; this.minutes = minutes; }

    @Option(name="-h", aliases = { "-help" }, usage="display options")
    private boolean help;

    @Option(name="-v", aliases = { "-verbose" }, usage="display message")
    private boolean verbose;

    @Option(name="-b", aliases = { "-balls", "-balls" }, metaVar = "27", usage=balls_usage)
    public void setBalls(final int property) throws BadArgument {
        balls = property;
        if (!isBall()) throw new BadArgument(balls_usage);
    }

    @Option(name="-t", aliases = { "-minutes", "-time", "-m", "-timeToRun" }, metaVar = "1", usage=minutes_usage)
    public void setMinutes(final int property) throws BadArgument {
        minutes = property;
        if (!isMinutes()) throw new BadArgument(minutes_usage);
    }

    public boolean isEmpty() { return !isBall() && !isMinutes() && !help; }

    /**
     * Is the Ball parameter correctly set?
     * @return
     */
    public boolean isBall() { return balls > 26 && balls < 128; }

    /**
     * Is the Minutes parameter set?
     * @return
     */
    public boolean isMinutes() { return minutes > 0; }

    public void setVerbose(boolean verbose) { this.verbose = verbose; }

    public int getBalls() { return balls; }

    public int getMinutes() { return minutes; }

    public boolean isHelp() { return help; }

    public void setHelp(boolean help) { this.help = help; }

    public boolean isVerbose() { return verbose; }

    /**
     * This exception is thrown when a bad argument is passed into the program. Note that it extends the CmdLineException
     * by use of a deprecated constructor because the new constructor doesn't have all of it's args present in this
     * class.
     */
    public class BadArgument extends CmdLineException { public BadArgument(String message) {
            super(message);
        } }
}

package com.emilionicoli.ballclock;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import static java.lang.System.err;
import static java.lang.System.out;

public class BallClockApplication {

    public static void main(String[] args) {
        System.exit(new BallClockApplication().doMain(args));
    }

    public int doMain(String[] ar) {

        Arguments args = new Arguments();
        final CmdLineParser parser = new CmdLineParser(args);

        if ( ar.length < 1 ) {
            err.println("No args were passed! \nPlease enter the following args");
            parser.printUsage(err);
            return -1; // Error
        }

        try {
            parser.parseArgument(ar);

        } catch (CmdLineException clex) {
            out.println("ERROR: Unable to parse command-line options: " + clex);
            return 1; // Exception
        }

        if( args.isVerbose() ) {
            out.println("-verbose flag is set");
        }

        if( args.isVerbose() && args.isBall() ) {
            out.println("-balls flag is set to " + args.getBalls());
        }

        if( args.isVerbose() && args.isMinutes() ) {
            out.println("-minutes flag is set, number of minutes to doBalls is " + args.getMinutes());
        }

        if( args.isHelp() ){
            out.println("Please enter the following args");
            parser.printUsage(out);
        }

        try {
            if ( args.isBall() && args.isMinutes() ) {
                new BallClock(args).doMinutes();
            }
            else if ( args.isBall() ) {
                new BallClock(args).doBalls();
            }
        } catch (Exception ex){
            out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
            return 1;
        }

        return 0; // Normal exit
    }
}

package com.emilionicoli.ballclock;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import static java.lang.System.err;
import static java.lang.System.out;

public class BallClockApplication {

    public static void main(String[] args) {
        System.exit(new BallClockApplication().doMain(args));
    }

    public int doMain(String[] args) {

        Arguments arguments = new Arguments();
        final CmdLineParser parser = new CmdLineParser(arguments);

        if (args.length < 1) {
            err.println("No arguments were passed! \nPlease enter the following arguments");
            parser.printUsage(err);
            return -1; // Error
        }

        try {
            parser.parseArgument(args);

        } catch (CmdLineException clex) {
            out.println("ERROR: Unable to parse command-line options: " + clex);
            return 1; // Exception
        }

        if(arguments.isVerbose()) {
            out.println("-verbose flag is set");
        }

        if(arguments.isVerbose() && arguments.isBall()) {
            out.println("-balls flag is set to " + arguments.getBalls());
        }

        if(arguments.isVerbose() && arguments.isMinutes()) {
            out.println("-minutes flag is set, number of minutes to run is " + arguments.getMinutes());
        }

        if(arguments.isHelp()){
            out.println("Please enter the following arguments");
            parser.printUsage(out);
        }

        try {
            if (arguments.isBall() || arguments.isMinutes()) {
                BallClock ballClock = new BallClock(arguments);
                ballClock.run();
            }
        } catch (Exception ex){
            out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
            return 1;
        }


        return 0; // Normal exit
    }
}

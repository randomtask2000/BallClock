/* ballclock main */
package main

import (
	"fmt"
	bc "./ballclock"
	"gopkg.in/alecthomas/kingpin.v2"
	"os"
)

var (
	verbose     = kingpin.Flag("verbose", "verbose mode").Short('v').Bool()
	balls       = kingpin.Arg("balls", "number of balls needs to be between 27 to 127").Uint()
	minutes     = kingpin.Arg("minutes", "number of minutes needs to be greater then zero").Uint()
	help 		= kingpin.Flag("h", "help message").Short('h').Bool()
	helpMessage = "No args were passed!\nPlease enter the following args\n" +
				" <balls>          : number of balls needs to be between 27 to 127\n"+
		        " <minutes>        : number of minutes needs to be greater than zero\n"+
				" --help           : display options (default: false)\n"+
				" --verbose        : display message (default: false)"
)

func main() {
	kingpin.Version("0.0.1")
	kingpin.Parse()
	args := bc.NewBallsAndMinutes(*balls, *minutes)
	args.SetHelp(*help)
	args.SetVerbose(*verbose)

	fmt.Println(args)
	fmt.Println(*verbose)
	fmt.Println(*balls)
	fmt.Println(*minutes)

	if *balls == 0 && *minutes == 0 {
		fmt.Println(helpMessage)
		os.Exit(1)
	}

	if args.IsVerbose() {
		fmt.Println("--verbose flag is set")
	}

	if args.IsVerbose() && args.IsBalls() {
		fmt.Println("--balls flag is set to %d", args.Balls())
	}

	if args.IsVerbose() && args.IsMinutes() {
		fmt.Println("--minutes flag is set, number of minutes is %d", args.Minutes())
	}

	//if args.IsBalls() && args.IsMinutes() {
	//	new BallClock(args).doMinutes();
	//}
	//else if args.isBall() {
	//	new BallClock(args).doBalls();
	//}

}

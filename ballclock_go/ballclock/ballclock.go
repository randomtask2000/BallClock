package ballclock

import (
	"errors"
)

type Ballclock struct {
	args Arguments
	//minuteRail, fiveMinuteRail, oneHourRail Railable
	//feedRail FeedRail
	verbose bool
	minutes uint
}

func NewBallClock(args Arguments) (Ballclock) {
	bc := Ballclock{}
	bc.args = args
	if bc.minutes = args.Minutes(); !args.IsMinutes() { bc.minutes = 0 }
	bc.verbose = args.IsVerbose()
	//feedRail = new FeedRail(this.args.getBalls(), null);
	//oneHourRail = new Rail(11, feedRail);
	//fiveMinuteRail = new Rail(11, oneHourRail);
	//minuteRail = new Rail(4, fiveMinuteRail);
	//feedRail.setNextRail(minuteRail);
	//Rail.setFeedRail(feedRail);
	return bc
}

/**
 * Manage running of Balls illustration and stats output
 */
func (bc *Ballclock) DoBalls() (err error) {
	if !bc.args.IsBalls() { return errors.New("Please add ball parameter between 27 and 127") }
	//Stopwatch stopwatch = Stopwatch.createStarted();
	days := runLoop();
	//stopwatch.stop();
	//printStats(args, stopwatch, days);
}
/**
 * Manage running of Minutes illustration and stats output in form of Json
 * @throws BallClockException
 */
func (bc *Ballclock) doMinutes() (err error) {
	if (!args.isBall() || !args.isMinutes())
	return errors.New("Please add ball parameter between 27 and 127 and minutes greater than 0");

	double days = runForMinutes();

	printJsonMinutes(this);
}


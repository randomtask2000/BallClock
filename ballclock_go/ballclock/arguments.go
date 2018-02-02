/* arguments for ballclock */
package ballclock
/* Arguments */
type Args interface {
	New() Arguments
	NewBalls(balls uint) Arguments
	NewBallsAndMinutes(balls uint, minutes uint) Arguments
	SetHelp(help bool)
	Help() bool
	SetVerbose(verbose bool)
	isVerbose() bool
	SetBall(balls uint)
	Balls() uint
	isBalls() bool
	SetMinutes(minutes uint)
	Minutes() uint
	isMinutes() bool
}
/* implementation of Arugments contract */
type Arguments struct {
	balls uint
	minutes uint
	help bool
	verbose bool
}
/* constructors */
func New() Arguments {
	args := Arguments{}
	args.SetBall(0)
	args.SetMinutes(0)
	return args
}
func NewBalls(balls uint) Arguments {
	args := Arguments{}
	args.SetBall(balls)
	args.SetMinutes(0)
	return args
}
func NewBallsAndMinutes(balls uint, minutes uint) Arguments {
	args := Arguments{}
	args.SetBall(balls)
	args.SetMinutes(minutes)
	return args
}
/* methods */
func (args *Arguments) SetHelp(help bool)       { args.help = help }
func (args *Arguments) IsHelp() bool            { return args.help }
func (args *Arguments) SetVerbose(verbose bool) { args.verbose = verbose }
func (args *Arguments) IsVerbose() bool         { return args.verbose }
func (args *Arguments) SetBall(balls uint) (err error) { // throw error if argument is out of range
	args.balls = balls
	if args.IsBalls() { return } else { return err }
}
func (args *Arguments) Balls() uint    { return args.balls }
func (args *Arguments) IsBalls() bool { return args.balls > 26 && args.balls < 128 }
func (args *Arguments) SetMinutes(minutes uint) (err error) {
	args.minutes = minutes
	if args.IsMinutes() { return } else { return err }
}
func (args *Arguments) Minutes() uint { return args.minutes }
func (args *Arguments) IsMinutes() bool { return args.minutes > 0 }

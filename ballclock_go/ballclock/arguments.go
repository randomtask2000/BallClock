/* arguments for ballclock */
package ballclock
/* Arguments */
type Arguments interface {
	New() Arguments
	NewBalls(balls int) Arguments
	NewBallsAndMinutes(balls int, minutes uint64) Arguments
	SetHelp(help bool)
	Help() bool
	SetVerbose(verbose bool)
	isVerbose() bool
	SetBall(balls int)
	Balls() int
	isBalls() bool
	SetMinutes(minutes uint64)
	Minutes() uint64
	isMinutes() bool
}
type ArgumentsImpl struct {
	balls int
	minutes uint64
	help bool
	verbose bool
}
func New() ArgumentsImpl {
	args := ArgumentsImpl{}
	args.SetBall(0)
	args.SetMinutes(0)
	return args
}
func NewBalls(balls int) ArgumentsImpl {
	args := ArgumentsImpl{}
	args.SetBall(balls)
	args.SetMinutes(0)
	return args
}
func NewBallsAndMinutes(balls int, minutes uint64) ArgumentsImpl {
	args := ArgumentsImpl{}
	args.SetBall(balls)
	args.SetMinutes(minutes)
	return args
}
func (args *ArgumentsImpl) SetHelp(help bool)             { args.help = help }
func (args *ArgumentsImpl) isHelp() bool                  { return args.help }
func (args *ArgumentsImpl) SetVerbose(verbose bool)       { args.verbose = verbose }
func (args *ArgumentsImpl) isVerbose() bool               { return args.verbose }
func (args *ArgumentsImpl) SetBall(balls int) (err error) { 
	if args.isBalls() {
		args.balls = balls; return
	} else {
		return err
	}
}
func (args *ArgumentsImpl) Balls() int                    { return args.balls }
func (args *ArgumentsImpl) isBalls() bool                 { return args.balls > 26 && args.balls < 128 }
func (args *ArgumentsImpl) SetMinutes(minutes uint64)     { args.minutes = minutes }
func (args *ArgumentsImpl) Minutes() uint64               { return args.minutes }
func (args *ArgumentsImpl) isMinutes() bool               { return args.minutes > 0 }

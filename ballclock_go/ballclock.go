/* ballclock main */
package main

import (
	"fmt"
	bc "./ballclock"
)

func main() {
	a := bc.New()
	a.SetBall(1)
	a.SetMinutes(3)
	fmt.Println(a)
	fmt.Println(a.Balls())
	fmt.Println(a.Minutes())
}

/* ballclock main */
package main

import (
	"fmt"
	b "./ballclock"
)

func main() {
	a := b.New()
	a.SetBall(1)
	a.SetMinutes(3)
	fmt.Println(a)
	fmt.Println(a.Balls())
	fmt.Println(a.Minutes())
}

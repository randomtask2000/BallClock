package com.emilionicoli.ballclock;

/**
 * This specific exception is thrown by the BallClock
 */
public class BallClockException extends Exception {
    public BallClockException(String message) {
    super(message);
}
    public BallClockException(String message, Exception ex) {
        super(message, ex);
    }
}

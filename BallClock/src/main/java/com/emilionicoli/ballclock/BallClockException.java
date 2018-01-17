package com.emilionicoli.ballclock;

public class BallClockException extends Exception {
    public BallClockException(String message) {
    super(message);
}
    public BallClockException(String message, Exception ex) {
        super(message, ex);
    }
}

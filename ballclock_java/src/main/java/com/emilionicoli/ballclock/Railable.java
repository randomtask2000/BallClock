package com.emilionicoli.ballclock;

/**
 * Contract used by both FeedRail and Rail classes to ensure they each can pass expected behavior as they link each other in the BallClock.
 */
public interface Railable {
    void setNextRail(Railable nextRail);

    void popToNext();

    @Override
    String toString();

    java.util.Deque<Integer> getQueue();

    boolean isEmpty();

    boolean isStocked();

    void flush(Integer ball);
}

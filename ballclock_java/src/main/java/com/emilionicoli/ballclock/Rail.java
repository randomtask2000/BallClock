package com.emilionicoli.ballclock;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

import static com.emilionicoli.ballclock.Utils.pushOrFlush;

/**
 * Similarly to the FeedRail, the Rail class implements similar behavior as dictated by the Railable contract.
 */
public final class Rail implements Railable {

    public final Deque<Integer> rail;
    private int capacity;
    private static FeedRail feed;
    private Railable nextRail;

    /**
     * This method is used once to set the single FeedRail for each of the Rails. Each rail interacts witht the FeedRail in some way or another.
     * @param feedRail
     */
    public static void setFeedRail(FeedRail feedRail) { feed = feedRail; }
    @Override
    public void setNextRail(Railable nextRail) { this.nextRail = nextRail; }

    public Rail(int capacity, Railable nextRail) {
        this.capacity = capacity;
        this.nextRail = nextRail;
        rail = new LinkedBlockingDeque<>(capacity);
    }

    /**
     * Send a ball to the next rail
     */
    @Override
    public void popToNext() { nextRail.flush(rail.pollFirst()); }

    @Override
    public String toString() {
        return rail.toString();
    }

    @Override
    public java.util.Deque<Integer> getQueue() { return rail; }

    @Override
    public boolean isEmpty() { return rail.isEmpty(); }

    /**
     * Is the rail at capacity
     * @return
     */
    @Override
    public boolean isStocked() { return rail.size() == capacity; }

    @Override
    public void flush(Integer ball) {
        pushOrFlush(ball, this, feed, nextRail);
    }
}

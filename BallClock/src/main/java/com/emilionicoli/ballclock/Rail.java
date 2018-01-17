package com.emilionicoli.ballclock;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

import static com.emilionicoli.ballclock.Utils.pushOrFlush;

public final class Rail implements Railable {

    public final Deque<Integer> rail;
    private int capacity;
    private static FeedRail feed;
    private Railable nextRail;
    private static BallClock ballclock;

    public static void setFeedRail(FeedRail feedRail) { feed = feedRail; }
    @Override
    public void setNextRail(Railable nextRail) { this.nextRail = nextRail; }
    public static void setBallClock(BallClock bc) { ballclock = bc; }

    public Rail(int capacity, Railable nextRail) {
        this.capacity = capacity;
        this.nextRail = nextRail;
        rail = new LinkedBlockingDeque<>(capacity);
    }

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

    @Override
    public boolean isStocked() { return rail.size() == capacity; }

    @Override
    public void flush(Integer ball) {
        pushOrFlush(ball, this, feed, nextRail);
    }
}

package com.emilionicoli.ballclock;

import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

import static com.emilionicoli.ballclock.Utils.pushOrFlush;
import static com.emilionicoli.ballclock.Utils.stockRail;

/**
 * FeedRail is the rail that feeds the timeframed rails, specifically the minutes rail.
 */
public final class FeedRail implements Railable {

    public final Deque<Integer> rail;
    private final Object[] firstStateFeedRail;
    private int capacity;
    private static FeedRail feed;
    private Railable nextRail;

    public void setNextRail(Railable nextRail) { this.nextRail = nextRail; }

    public FeedRail(int capacity, Railable nextRail) {
        this.capacity = capacity;
        this.nextRail = nextRail;
        rail = new LinkedBlockingDeque<>(capacity);
        firstStateFeedRail = stockRail(capacity, this);
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

    public boolean isFirstState() {
        return rail.size() == capacity
                && Arrays.equals(rail.toArray(), firstStateFeedRail);
    }
}

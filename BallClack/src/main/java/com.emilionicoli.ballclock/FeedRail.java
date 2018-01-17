package com.emilionicoli.ballclock;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class Rail implements Railable {

    @Getter public Deque<Integer> rail;
    private static Object[] firstStateFeedRail;
    private int capacity;
    @Setter private static Railable feed;
    @Setter private Railable nextRail;
    @Setter private static BallClock ballclock;

    public Rail(int capacity, Railable nextRail) {
        this.capacity = capacity;
        this.nextRail = nextRail;
        rail = new LinkedBlockingDeque<>(capacity);
    }

    @Override
    public void popToNextRail() {
        pushIntoRail(rail.pollFirst(), nextRail);
    }

    @Override
    public String toString() {
        return rail.toString();
    }

    boolean reachedLimit() { return rail.size() == capacity; }

    @Override
    public void pushOrFlush(Integer ball) {
        if ( !reachedLimit() ) {
            rail.offer(ball);
        } else {
            flushRail(ball);
        }
    }

    public void initFeedRail() {
        for ( int ballId = capacity -1; ballId>-1; ballId-- ){ // load balls into feed
            rail.offerFirst(ballId);
        }
        firstStateFeedRail = rail.toArray(); // we use this to compare our feed Rail state with
    }

    public boolean isFirstState() {
        return rail.size() == capacity
                && Arrays.equals(rail.toArray(), firstStateFeedRail);
    }

    private void flushRail(Integer ball) {
        while ( !rail.isEmpty() ) {
            feed.getRail().offer(rail.pollLast()); //TODO:!!!
        }
        pushIntoRail(ball, nextRail); // one ball goes into next rail
    }

    public void pushIntoRail(Integer ball, Railable rail) {
        rail.pushOrFlush(ball);
    }
}

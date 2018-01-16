package com.emilionicoli.ballclock;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class Rail {

    public Deque<Integer> rail;
    private static Object[] firstStateFeedRail;
    @Getter private int capacity;
    @Setter private static Rail feedRail;
    @Setter private Rail nextRail;
    @Setter private static BallClock ballclock;

    public Rail(int capacity, Rail nextRail){
        this.capacity = capacity;
        this.nextRail = nextRail;
        rail = new LinkedBlockingDeque<>(capacity);
    }

    public void popToNextRail() {
        Integer b = rail.pollFirst();
        pushIntoRail(b, nextRail);
    }

    public String toString() {
        return rail.toString();
    }

    boolean reachedLimit() { return rail.size() == capacity; }

    void pushOrFlush(Integer ball) {
        if (!reachedLimit()){
            rail.offer(ball);
        } else {
            flushRail(ball);
        }
    }

    public void initFeedRail() {
        for (int i = capacity -1; i>-1; i--){ // load balls
            rail.offerFirst(i);
        }
        firstStateFeedRail = rail.toArray();
    }

    public boolean isFirstState(){
        return rail.size() == capacity
                && Arrays.equals(rail.toArray(), firstStateFeedRail);
    }

    private void flushRail(Integer ball) {
        while (!rail.isEmpty()){
            feedRail.rail.offer(rail.pollLast()); //TODO:!!!
        }
        pushIntoRail(ball, nextRail); // one ball goes into next rail
    }

    void pushIntoRail(Integer ball, Rail r) {
        r.pushOrFlush(ball);
    }
}

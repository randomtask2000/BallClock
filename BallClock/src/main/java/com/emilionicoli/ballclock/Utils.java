package com.emilionicoli.ballclock;

import java.util.Arrays;

public class Utils {

    private Utils() {} // Singleton constructor

    static Object[] stockRail(int capacity, Railable rail) {
        for ( int ballId = capacity -1; ballId>-1; ballId-- ){ // load balls into feed
            rail.getQueue().offerFirst(ballId);
        }
        return rail.getQueue().toArray(); // we use this to compare our feed Rail state with
    }

    static void pushOrFlush(Integer ball, Railable rail, FeedRail feed, Railable nextRail) {
        if ( !rail.isStocked() ) {
            rail.getQueue().offer(ball);
        } else {
            flushRail(ball, feed, rail, nextRail);
        }
    }

    static void flushRail(Integer ball, FeedRail feed, Railable rail, Railable nextRail) {
        while ( !rail.isEmpty() ) {
            feed.getQueue().offer(rail.getQueue().pollLast());
        }
        nextRail.flush(ball);
    }

    static boolean isFirstState(Railable rail, Integer capacity, Object[] firstStateFeedRail) {
        return rail.getQueue().size() == capacity
                && Arrays.equals(rail.getQueue().toArray(), firstStateFeedRail);
    }
}

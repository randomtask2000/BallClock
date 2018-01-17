package com.emilionicoli.ballclock;

import com.google.common.base.Stopwatch;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class Utils {

    private Utils() {} // Singleton constructor

    static Object[] stockRail(int capacity, Railable rail) {
        for ( int ballId = capacity; ballId > 0; ballId-- ){ // load balls into feed
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

    static void printStats(Arguments args, Stopwatch stopwatch, int days) {
        out.println(String.format("%d balls cycle after %d days.", args.getBalls(), days));
        out.println(String.format("Completed in %s milliseconds (%.3f seconds)",
                stopwatch.elapsed(TimeUnit.MILLISECONDS),
                stopwatch.elapsed(TimeUnit.MILLISECONDS)/1000d));
    }

    static void printMinutes(BallClock bc) {
        out.println(String.format("%s", bc.minuteRail.toString()));
        out.println(String.format("%s", bc.fiveMinuteRail.toString()));
        out.println(String.format("%s", bc.oneHourRail.toString()));
        out.println(String.format("%s", bc.feedRail.toString()));
    }

    static void printJsonMinutes(BallClock bc) {
        String jsonOutput = getJson(bc);
        System.out.println(jsonOutput);
    }

    static String getJson(BallClock bc) {
        Map<String, Object[]> map = new LinkedHashMap<>();
        map.put("Minutes",bc.minuteRail.getQueue().toArray());
        map.put("FiveMin",bc.fiveMinuteRail.getQueue().toArray());
        map.put("Hours",bc.oneHourRail.getQueue().toArray());
        map.put("Main",bc.feedRail.getQueue().toArray());
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}

package com.emilionicoli.ballclock;

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

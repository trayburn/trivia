package com.improving;

import java.time.Duration;
import java.util.Date;

public class Timeout {
    private boolean started = false;
    private Date startTime;
    private int intervalInMilliseconds = 0;

    public Timeout(int intervalInSeconds) {
        this.intervalInMilliseconds = intervalInSeconds * 1000;
    }

    private Duration getDuration() {
        return Duration.between(startTime.toInstant(), new Date().toInstant());
    }

    public boolean isExpired() {
        return getDuration().toMillis() >= intervalInMilliseconds;
    }

    public boolean isStarted() {
        return started;
    }

    public Timeout start() {
        started = true;
        startTime = new Date();
        return this;
    }

    @Override
    public String toString() {
        var secondsRemaining = getRemainingSeconds();
        if (secondsRemaining <= 0) return "EXPIRED";
        else return "" + secondsRemaining + " seconds";
    }

    public int getRemainingSeconds() {
        return (int) ((intervalInMilliseconds - getDuration().toMillis()) / 1000);
    }
}

package pl.codewise.internship;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Timer {
    private int expirationTime;
    private Callback callback;
    private LocalDateTime startTime;

    public Timer(int expirationTime, Callback callback) {
        this.expirationTime = expirationTime;
        this.callback = callback;
        startTime = LocalDateTime.now();
    }

    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        long seconds = startTime.until(now, ChronoUnit.SECONDS);
        return seconds >= expirationTime;
    }

    public void callback() {
        callback.method();
    }
}

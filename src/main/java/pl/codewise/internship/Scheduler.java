package pl.codewise.internship;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Scheduler {
    private ConcurrentHashMap<Integer, Timer> timers;
    private int newTimerId;

    public Scheduler() {
        timers = new ConcurrentHashMap<>();
        newTimerId = 0;
        new Thread(new TimersExpirationChecker()).start();
    }

    public synchronized int start(int expirationTime, Callback callback) {
        newTimerId++;
        timers.put(newTimerId, new Timer(expirationTime, callback));
        return newTimerId;
    }

    public void stop(int timerId) {
        timers.remove(timerId);
    }

    private class TimersExpirationChecker implements Runnable {
        @Override
        public void run() {
            for(;;) {
                for(Map.Entry<Integer, Timer> entry : timers.entrySet()) {
                    Timer timer = entry.getValue();
                    if(timer.isExpired()) {
                        timer.callback();
                        timers.remove(entry.getKey());
                    }
                }
            }
        }
    }
}

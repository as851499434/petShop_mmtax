package com.mmtax.common.utils.redis;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: Moon
 * @Date: 2019/4/12 09:25
 * @Description:
 *
 */
public class SystemClock {
    private static final String THREAD_NAME = "system.clock";
    private static final SystemClock MILLIS_CLOCK = new SystemClock(1);
    private final long precision;
    private final AtomicLong now;

    private SystemClock(long precision) {
        this.precision = precision;
        now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }

    public static SystemClock millisClock() {
        return MILLIS_CLOCK;
    }

    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, THREAD_NAME);
            thread.setDaemon(true);
            return thread;
        });
        scheduler.scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), precision, precision, TimeUnit.MILLISECONDS);
    }

    public long now() {
        return now.get();
    }
}

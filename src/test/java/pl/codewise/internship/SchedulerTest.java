package pl.codewise.internship;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class SchedulerTest {
    private Scheduler scheduler;

    @Before
    public void before() {
        scheduler = new Scheduler();
    }

    @Test
    public void callbackIsNotCalled() {
        Callback callback = mock(Callback.class);
        scheduler.start(100, callback);
        verify(callback, times(0)).method();
    }

    @Test
    public void idsAreAssignedProperly() {
        int id1 = scheduler.start(5, () -> {});
        int id2 = scheduler.start(5, () -> {});

        Assert.assertEquals(1, id1);
        Assert.assertEquals(2, id2);
    }

    @Test
    public void callbackIsCalledAfter2Seconds() throws InterruptedException {
        Callback callback = mock(Callback.class);
        scheduler.start(1, callback);
        Thread.sleep(2000);

        verify(callback, times(1)).method();
    }

    @Test
    public void callbackIsNotCalledAfter1Seconds() throws InterruptedException {
        Callback callback = mock(Callback.class);
        scheduler.start(2, callback);
        Thread.sleep(1000);

        verify(callback, times(0)).method();
    }

    @Test
    public void callbackIsCalledOnlyForSingleObjectAfter2Seconds() throws InterruptedException {
        Callback callback1 = mock(Callback.class);
        Callback callback2 = mock(Callback.class);
        scheduler.start(3, callback1);
        scheduler.start(1, callback2);
        Thread.sleep(2000);

        verify(callback1, times(0)).method();
        verify(callback2, times(1)).method();
    }

    @Test
    public void callbackIsNotCalledAfterStop() throws InterruptedException {
        Callback callback = mock(Callback.class);
        int timerId = scheduler.start(1, callback);
        scheduler.stop(timerId);
        Thread.sleep(2000);

        verify(callback, times(0)).method();
    }
}

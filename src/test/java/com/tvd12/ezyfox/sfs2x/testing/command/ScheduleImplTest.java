package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;
import java.util.concurrent.ScheduledFuture;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.ScheduleImpl;

import static org.testng.Assert.*;

public class ScheduleImplTest extends BaseCommandTest {

    @Test
    public void test() {
        final ScheduleImpl sImpl = new ScheduleImpl(context, api, extension)
            .delay(0)
            .oneTime(true)
            .period(1);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                sImpl.stop();
            }
        };
        assertFalse(sImpl.stopped());
        sImpl.task(task);
        sImpl.stop();
        assertTrue(sImpl.stopped());
        sImpl.stopNow();
        sImpl.schedule();
        assertFalse(sImpl.stopped());
    }
    
    @Test
    public void test2() {
        final ScheduleImpl sImpl = new ScheduleImpl(context, api, extension)
                .delay(0)
                .period(1);
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    sImpl.stopNow();
                }
            };
            sImpl.task(task);
            sImpl.stop();
            sImpl.stopNow();
            sImpl.schedule();
    }
    
    @Test
    public void test3() throws Exception {
        final ScheduleImpl sImpl = new ScheduleImpl(context, api, extension);
        Field field = ReflectFieldUtil.getField("scheduledFuture", ScheduleImpl.class);
        field.setAccessible(true);
        ScheduledFuture<?> scheduledFuture = mock(ScheduledFuture.class);
        field.set(sImpl, scheduledFuture);
        sImpl.stop();
    }
    
}

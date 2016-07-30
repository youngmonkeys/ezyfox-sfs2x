package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ScheduledFuture;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.ScheduleImpl;
import com.tvd12.test.reflect.MethodBuilder;

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
        assertTrue(sImpl.cancel());
        assertTrue(sImpl.cancelled());
        assertFalse(sImpl.done());
        assertFalse(sImpl.stopped());
        sImpl.task(task);
        sImpl.stop();
        assertTrue(sImpl.stopped());
        sImpl.stop();
        sImpl.schedule();
        sImpl.cancel();
        sImpl.cancelNow();
        sImpl.cancelled();
        sImpl.done();
        sImpl.schedule();
    }
    
    @Test
    public void test2() {
        final ScheduleImpl sImpl = new ScheduleImpl(context, api, extension)
                .delay(0)
                .oneTime(false)
                .period(1);
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    sImpl.stop();
                }
            };
            sImpl.task(task);
            sImpl.stop();
            sImpl.schedule();
    }
    
    @Test
    public void test3() throws Exception {
        final ScheduleImpl sImpl = new ScheduleImpl(context, api, extension);
        Field field = ReflectFieldUtil.getField("scheduledFuture", ScheduleImpl.class);
        field.setAccessible(true);
        ScheduledFuture<?> scheduledFuture = mock(ScheduledFuture.class);
        field.set(sImpl, scheduledFuture);
        sImpl.oneTime(false);
        sImpl.stop();
    }
    
    @Test
    public void test4() throws Exception {
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
            sImpl.stop();
            sImpl.task(task);
            Field runnableField = ScheduleImpl.class.getDeclaredField("runnable");
            runnableField.setAccessible(true);
            Method runMethod = MethodBuilder.create()
                    .clazz(runnableField.getType())
                    .method("run")
                    .build();
            runMethod.setAccessible(true);
            runMethod.invoke(runnableField.get(sImpl));
    }
}

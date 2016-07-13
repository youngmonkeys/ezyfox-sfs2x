package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.concurrent.ScheduledFuture;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.PingClientImpl;

public class PingClientImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        final PingClientImpl sImpl = new PingClientImpl(context, api, extension)
            .delay(0)
            .oneTime(true)
            .user(user)
            .period(1);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                sImpl.stop();
            }
        };
        assertFalse(sImpl.stopped());
        sImpl.callback(task);
        sImpl.stop();
        assertTrue(sImpl.stopped());
        sImpl.stopNow();
        sImpl.ping();
    }
    
    @Test
    public void test2() {
        final PingClientImpl sImpl = new PingClientImpl(context, api, extension)
                .delay(0)
                .oneTime(false)
                .period(1);
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    sImpl.stopNow();
                }
            };
            sImpl.callback(task);
            sImpl.stop();
            sImpl.stopNow();
            sImpl.ping();
    }
    
    @Test
    public void test3() throws Exception {
        final PingClientImpl sImpl = new PingClientImpl(context, api, extension);
        Field field = ReflectFieldUtil.getField("scheduledFuture", PingClientImpl.class);
        field.setAccessible(true);
        ScheduledFuture<?> scheduledFuture = mock(ScheduledFuture.class);
        field.set(sImpl, scheduledFuture);
        sImpl.oneTime(false);
        sImpl.stop();
    }
    
}

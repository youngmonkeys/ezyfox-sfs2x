package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ScheduledFuture;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.PingClientImpl;
import com.tvd12.test.reflect.MethodBuilder;

public class PingClientImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        final PingClientImpl sImpl = new PingClientImpl(context, api, extension)
            .delay(0)
            .user(user);
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
        sImpl.callback(task);
        sImpl.stop();
        assertTrue(sImpl.stopped());
        sImpl.stop();
        sImpl.ping();
        sImpl.cancel();
        sImpl.cancelNow();
        sImpl.cancelled();
        sImpl.done();
        sImpl.ping();
    }
    
    @Test
    public void test2() throws Exception {
        final PingClientImpl sImpl = new PingClientImpl(context, api, extension)
                .delay(0);
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    sImpl.stop();
                }
            };
            sImpl.stop();
            sImpl.callback(task);
            sImpl.ping();
    }
    
    @Test
    public void test3() throws Exception {
        final PingClientImpl sImpl = new PingClientImpl(context, api, extension);
        Field field = ReflectFieldUtil.getField("scheduledFuture", PingClientImpl.class);
        field.setAccessible(true);
        ScheduledFuture<?> scheduledFuture = mock(ScheduledFuture.class);
        field.set(sImpl, scheduledFuture);
        sImpl.stop();
    }
    
    @Test
    public void test4() throws Exception {
        final PingClientImpl sImpl = new PingClientImpl(context, api, extension)
                .delay(0);
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    sImpl.stop();
                }
            };
            sImpl.callback(task);
            sImpl.stop();
            Field runnableField = PingClientImpl.class.getDeclaredField("runnable");
            runnableField.setAccessible(true);
            Method runMethod = MethodBuilder.create()
                    .clazz(runnableField.getType())
                    .method("run")
                    .build();
            runMethod.setAccessible(true);
            runMethod.invoke(runnableField.get(sImpl));
    }
}

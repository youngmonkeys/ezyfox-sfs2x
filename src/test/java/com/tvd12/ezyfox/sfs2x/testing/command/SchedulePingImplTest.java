package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ScheduledFuture;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.command.SchedulePing;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.PingClientImpl;
import com.tvd12.ezyfox.sfs2x.command.impl.SchedulePingImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;
import com.tvd12.test.reflect.MethodBuilder;

public class SchedulePingImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        final SchedulePing sImpl = new SchedulePingImpl(context, api, extension)
            .delay(0)
            .user(user)
            .period(1);
        sImpl.stop();
        assertTrue(sImpl.stopped());
        sImpl.ping();
        assertFalse(sImpl.stopped());
        sImpl.stop();
        assertTrue(sImpl.stopped());
    }
    
    @Test
    public void test2() throws Exception {
        AppUser user = new AppUser();
        user.setName("abc");
        final SchedulePingImpl sImpl = new SchedulePingImpl(context, api, extension)
                .user(user)
                .delay(0)
                .period(10);
            sImpl.stop();
            sImpl.ping();
    }
    
//    @Test
    public void test3() throws Exception {
        final SchedulePingImpl sImpl = new SchedulePingImpl(context, api, extension);
        Field field = ReflectFieldUtil.getField("scheduledFuture", PingClientImpl.class);
        field.setAccessible(true);
        ScheduledFuture<?> scheduledFuture = mock(ScheduledFuture.class);
        field.set(sImpl, scheduledFuture);
        sImpl.stop();
    }
    
//    @Test
    public void test4() throws Exception {
        final SchedulePingImpl sImpl = new SchedulePingImpl(context, api, extension)
                .delay(0)
                .period(1);
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

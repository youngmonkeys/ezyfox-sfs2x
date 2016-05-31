package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.smartfoxserver.v2.util.TaskScheduler;
import com.tvd12.ezyfox.core.command.Schedule;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public class ScheduleImpl extends BaseCommandImpl implements Schedule {
    
    private long delayTime;
    private boolean onTime;
    private long period;
    private Runnable runnable;
    
    private ScheduledFuture<?> scheduledFuture;
    
    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    public ScheduleImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ScheduleImpl delay(long time) {
        this.delayTime = time;
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ScheduleImpl oneTime(boolean value) {
        this.onTime = value;
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ScheduleImpl period(long value) {
        this.period = value;
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ScheduleImpl task(Runnable value) {
        this.runnable = value;
        return this;
    }

    @Override
    public void schedule() {
        TaskScheduler scheduler = SmartFoxServer
                    .getInstance().getTaskScheduler();
        if(onTime)
            scheduleOneTime(scheduler);
        else 
            schedule(scheduler);
    }

    @Override
    public void stop() {
        if(scheduledFuture != null)
            scheduledFuture.cancel(DONT_INTERRUPT_IF_RUNNING);
    }
    
    @Override
    public void stopNow() {
        if(scheduledFuture != null)
            scheduledFuture.cancel(!DONT_INTERRUPT_IF_RUNNING);
    }
    
    private void scheduleOneTime(TaskScheduler scheduler) {
        scheduledFuture = scheduler.schedule(runnable, (int)delayTime, TimeUnit.MILLISECONDS);
    }
    
    private void schedule(TaskScheduler scheduler) {
        scheduledFuture = scheduler.scheduleAtFixedRate(
                runnable, (int)delayTime, (int)period, TimeUnit.MILLISECONDS);
    }
    
}

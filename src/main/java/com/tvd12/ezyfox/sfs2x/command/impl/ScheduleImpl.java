package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.smartfoxserver.v2.util.TaskScheduler;
import com.tvd12.ezyfox.core.command.Schedule;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see Schedule
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class ScheduleImpl extends BaseCommandImpl implements Schedule {
    
    private long delayTime;
    private boolean onTime;
    private boolean stopped;
    private long period;
    private Runnable runnable;
    
    private ScheduledFuture<?> scheduledFuture;
    
    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    /**
     * @param context
     * @param api
     * @param extension
     */
    public ScheduleImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
        this.stopped = false;
    }

    /**
     * @see Schedule#delay(long)
     */
    @Override
    public ScheduleImpl delay(long time) {
        this.delayTime = time;
        return this;
    }

    /**
     * @see Schedule#oneTime(boolean)
     */
    @Override
    public ScheduleImpl oneTime(boolean value) {
        this.onTime = value;
        return this;
    }

    /**
     * @see Schedule#period(long)
     */
    @Override
    public ScheduleImpl period(long value) {
        this.period = value;
        return this;
    }

    /**
     * @see Schedule#task(Runnable)
     */
    @Override
    public ScheduleImpl task(Runnable value) {
        this.runnable = value;
        return this;
    }
    
    /**
     * @see com.tvd12.ezyfox.core.command.Schedule#stopped()
     */
    @Override
    public boolean stopped() {
        return stopped;
    }

    /**
     * @see Schedule#schedule()
     */
    @Override
    public void schedule() {
        stopped = false;
        TaskScheduler scheduler = SmartFoxServer
                    .getInstance()
                    .getTaskScheduler();
        if(onTime)
            scheduleOneTime(scheduler);
        else 
            schedule(scheduler);
    }

    /**
     * @see Schedule#stop()
     */
    @Override
    public void stop() {
        if(scheduledFuture != null)
            scheduledFuture.cancel(DONT_INTERRUPT_IF_RUNNING);
        stopped = true;
    }
    
    /**
     * @see Schedule#stopNow()
     */
    @Override
    public void stopNow() {
        if(scheduledFuture != null)
            scheduledFuture.cancel(!DONT_INTERRUPT_IF_RUNNING);
        stopped = true;
    }
    
    /**
     * Schedule one short
     * 
     * @param scheduler TaskScheduler object
     */
    private void scheduleOneTime(TaskScheduler scheduler) {
        scheduledFuture = scheduler.schedule(runnable, (int)delayTime, TimeUnit.MILLISECONDS);
    }
    
    /**
     * Schedule forever
     * 
     * @param scheduler TaskScheduler object
     */
    private void schedule(TaskScheduler scheduler) {
        scheduledFuture = scheduler.scheduleAtFixedRate(
                runnable, (int)delayTime, (int)period, TimeUnit.MILLISECONDS);
    }
    
}

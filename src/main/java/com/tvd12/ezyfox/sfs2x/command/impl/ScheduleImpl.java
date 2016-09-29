package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.smartfoxserver.v2.util.TaskScheduler;
import com.tvd12.ezyfox.core.command.Schedule;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

import lombok.AllArgsConstructor;

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
    private long period;
    private RunnableImpl runnable;
    private AtomicBoolean stopped;
    
    private ScheduledFuture<?> scheduledFuture;
    
    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public ScheduleImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
        this.onTime = true;
        this.stopped = new AtomicBoolean(false);
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
        this.runnable = new RunnableImpl(value);
        return this;
    }
    
    /**
     * @see com.tvd12.ezyfox.core.command.Schedule#stopped()
     */
    @Override
    public boolean stopped() {
        return stopped.get();
    }

    /**
     * @see Schedule#schedule()
     */
    @Override
    public void schedule() {
        stopped.set(false);
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
        cancelNow();
        stopped.set(true);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Schedule#cancel()
     */
    @Override
    public boolean cancel() {
        if(scheduledFuture != null)
            return scheduledFuture.cancel(DONT_INTERRUPT_IF_RUNNING);
        return true;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Schedule#cancelNow()
     */
    @Override
    public boolean cancelNow() {
        if(scheduledFuture != null)
            scheduledFuture.cancel(!DONT_INTERRUPT_IF_RUNNING);
        return true;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Schedule#cancelled()
     */
    @Override
    public boolean cancelled() {
        if(scheduledFuture != null)
            return scheduledFuture.isCancelled();
        return true;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Schedule#done()
     */
    @Override
    public boolean done() {
        if(scheduledFuture != null)
            return scheduledFuture.isDone();
        return false;
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
    
    @AllArgsConstructor
    private class RunnableImpl implements Runnable {
        
        private Runnable task;
        
        @Override
        public void run() {
            if(!stopped.get())
                task.run();
        }
    }
}

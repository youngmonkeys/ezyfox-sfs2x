package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.smartfoxserver.v2.util.TaskScheduler;
import com.tvd12.ezyfox.core.command.PingClient;
import com.tvd12.ezyfox.core.command.Schedule;
import com.tvd12.ezyfox.core.constants.ApiRequest;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see PingClient
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class PingClientImpl extends BaseCommandImpl implements PingClient {
    
    private long delayTime;
    private boolean onTime;
    private boolean stopped;
    private long period;
    private Runnable runnable;
    private ApiBaseUser user;
    
    private ScheduledFuture<?> scheduledFuture;
    
    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    /**
     * @param context
     * @param api
     * @param extension
     */
    public PingClientImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
        this.stopped = false;
        this.onTime = true;
    }

    /**
     * @see PingClient#delay(long)
     */
    @Override
    public PingClientImpl delay(long time) {
        this.delayTime = time;
        return this;
    }

    /**
     * @see PingClient#oneTime(boolean)
     */
    @Override
    public PingClientImpl oneTime(boolean value) {
        this.onTime = value;
        return this;
    }

    /**
     * @see PingClient#period(long)
     */
    @Override
    public PingClientImpl period(long value) {
        this.period = value;
        return this;
    }

    /**
     * @see PingClient#callback(Runnable)
     */
    @Override
    public PingClientImpl callback(Runnable value) {
        this.runnable = value;
        return this;
    }
    
    /**
     * @see com.tvd12.ezyfox.core.command.PingClient#user(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public PingClientImpl user(ApiBaseUser user) {
        this.user = user;
        return this;
    }
    
    /**
     * @see com.tvd12.ezyfox.core.command.PingClient#stopped()
     */
    @Override
    public boolean stopped() {
        return stopped;
    }

    /**
     * @see PingClient#ping()
     */
    @Override
    public void ping() {
        stopped = false;
        sendPingCommand();
        TaskScheduler scheduler = SmartFoxServer
                    .getInstance()
                    .getTaskScheduler();
        if(onTime)
            scheduleOneTime(scheduler);
        else 
            schedule(scheduler);
    }
    
    private void sendPingCommand() {
        User sfsUser = CommandUtil.getSfsUser(user, api);
        ISFSObject params = new SFSObject();
        extension.send(ApiRequest.PING, params, sfsUser);
    }

    /**
     * @see PingClient#stop()
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

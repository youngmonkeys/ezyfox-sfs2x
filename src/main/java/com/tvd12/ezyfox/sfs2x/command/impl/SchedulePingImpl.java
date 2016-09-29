package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.smartfoxserver.v2.util.TaskScheduler;
import com.tvd12.ezyfox.core.command.PingClient;
import com.tvd12.ezyfox.core.command.SchedulePing;
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
public class SchedulePingImpl extends BaseCommandImpl implements SchedulePing {
    
    private long delayTime;
    private long period;
    private ApiBaseUser user;
    
    private ScheduledFuture<?> scheduledFuture;
    
    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public SchedulePingImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SchedulePing#delay(long)
     */
    @Override
    public SchedulePingImpl delay(long time) {
        this.delayTime = time;
        return this;
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SchedulePing#period(long)
     */
    @Override
    public SchedulePingImpl period(long value) {
        this.period = value;
        return this;
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SchedulePing#user(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public SchedulePingImpl user(ApiBaseUser user) {
        this.user = user;
        return this;
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SchedulePing#stopped()
     */
    @Override
    public boolean stopped() {
        if(scheduledFuture != null)
            return scheduledFuture.isCancelled();
        return true;
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SchedulePing#stop()
     */
    @Override
    public void stop() {
        if(scheduledFuture != null)
            scheduledFuture.cancel(!DONT_INTERRUPT_IF_RUNNING);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.SchedulePing#ping()
     */
    @Override
    public void ping() {
        sendPingCommand();
        TaskScheduler scheduler = SmartFoxServer
                    .getInstance()
                    .getTaskScheduler();
        schedule(scheduler);
    }
    
    /**
     * Schedule forever
     * 
     * @param scheduler TaskScheduler object
     */
    private void schedule(TaskScheduler scheduler) {
        scheduledFuture = scheduler.scheduleAtFixedRate(
                createTask(), (int)delayTime, (int)period, TimeUnit.MILLISECONDS);
    }
    
    private void sendPingCommand() {
        User sfsUser = CommandUtil.getSfsUser(user, api);
        if(sfsUser != null)
            extension.send(ApiRequest.PING, new SFSObject(), sfsUser);
        else 
            stop();
    }
    
    private Runnable createTask() {
        return new Runnable() {
            
            @Override
            public void run() {
                sendPingCommand();
            }
        };
    }
    
}

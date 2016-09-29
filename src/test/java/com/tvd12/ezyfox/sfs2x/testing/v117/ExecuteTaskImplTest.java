package com.tvd12.ezyfox.sfs2x.testing.v117;

import java.lang.reflect.Field;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.SmartFoxServer;
import com.tvd12.ezyfox.core.command.ExecuteTask;
import com.tvd12.ezyfox.sfs2x.command.impl.ExecuteTaskImpl;
import com.tvd12.ezyfox.sfs2x.testing.command.BaseCommandTest2;

/**
 * @author tavandung12
 * Created on Sep 28, 2016
 *
 */
public class ExecuteTaskImplTest extends BaseCommandTest2 {

    @Test
    public void test() throws Exception {
        ExecuteTask et = new ExecuteTaskImpl(context, api, extension);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 
                4, 
                10, 
                TimeUnit.SECONDS, 
                new ArrayBlockingQueue<Runnable>(2), 
                new ThreadFactory() {
                    
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                }, 
                new RejectedExecutionHandler() {
                    
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        
                    }
                });
        Field field = SmartFoxServer.class.getDeclaredField("sysmtemWorkerPool");
        field.setAccessible(true);
        field.set(SmartFoxServer.getInstance(), executor);
        et.execute(new Runnable() {
            
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        });
    }
    
}

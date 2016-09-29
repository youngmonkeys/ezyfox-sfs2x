package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.ExecuteTask;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on Sep 28, 2016
 *
 */
public class ExecuteTaskImpl extends BaseCommandImpl implements ExecuteTask {

    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public ExecuteTaskImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ExecuteTask#execute(java.lang.Runnable)
     */
    @Override
    public void execute(Runnable task) {
        SmartFoxServer.getInstance().getSystemThreadPool().execute(task);
    }

}

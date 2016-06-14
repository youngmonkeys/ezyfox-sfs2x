package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.AddCommand;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see AddCommand
 * 
 * @author tavandung12
 * Created on Jun 14, 2016
 *
 */
public class AddCommandImpl extends BaseCommandImpl implements AddCommand {
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public AddCommandImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddCommand#add(java.lang.Class, java.lang.Class)
     */
    @Override
    public AppContext add(Class<?> baseClass, Class<?> implementation) {
        context.addAppCommand(baseClass, implementation);
        return context;
    }

}

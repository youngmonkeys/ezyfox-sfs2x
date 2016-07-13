package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.GetCurrentFolder;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on Jul 12, 2016
 *
 */
public class GetCurrentFolderImpl extends BaseCommandImpl implements GetCurrentFolder {
    /**
     * @param context
     * @param api
     * @param extension
     */
    public GetCurrentFolderImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public String execute() {
        return ("extensions/" + extension.getName() + "/");
    }

}

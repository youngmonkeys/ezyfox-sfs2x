package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.Properties;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.FetchConfigProperties;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class FetchConfigPropertiesImpl extends BaseCommandImpl implements FetchConfigProperties {

    /**
     * @param context
     * @param api
     * @param extension
     */
    public FetchConfigPropertiesImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Properties execute() {
        return extension.getConfigProperties();
    }

}

package com.tvd12.ezyfox.sfs2x.model.impl;

import com.tvd12.ezyfox.core.model.ApiBaseMessage;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiZone;

import lombok.Setter;

/**
 * @see ApiBaseMessage
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
@Setter
public class ApiBaseMessageImpl implements ApiBaseMessage {

    protected String content;
    protected ApiBaseUser sender;
    protected ApiZone zone;
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiBaseMessage#content()
     */
    @Override
    public String content() {
        return content;
    }
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiBaseMessage#zone()
     */
    @Override
    public ApiZone zone() {
        return zone;
    }
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiBaseMessage#sender()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiBaseUser> T sender() {
        // TODO Auto-generated method stub
        return (T) sender;
    }
    
}

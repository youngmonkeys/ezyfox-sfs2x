package com.tvd12.ezyfox.sfs2x.entities.impl;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiPrivateMessage;

import lombok.Setter;

/**
 * @see ApiMessageImpl
 * @see ApiPrivateMessage
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
@Setter
public class ApiPrivateMessageImpl extends ApiMessageImpl implements ApiPrivateMessage {

    private ApiBaseUser recipient;
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiMessageRecipient#recipient()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiBaseUser> T recipient() {
        return (T) recipient;
    }


}

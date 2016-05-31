package com.tvd12.ezyfox.sfs2x.model.impl;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiPrivateMessage;

import lombok.Setter;

/**
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
@Setter
public class ApiPrivateMessageImpl extends ApiMessageImpl implements ApiPrivateMessage {

    private ApiBaseUser recipient;
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiMessageRecipient#recipient()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiBaseUser> T recipient() {
        return (T) recipient;
    }


}

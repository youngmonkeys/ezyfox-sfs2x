package com.tvd12.ezyfox.sfs2x.model.impl;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiBuddyMessage;

import lombok.Setter;

/**
 * @see ApiBaseMessageImpl
 * @see ApiBuddyMessage
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class ApiBuddyMessageImpl extends ApiBaseMessageImpl implements ApiBuddyMessage {

    @Setter
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

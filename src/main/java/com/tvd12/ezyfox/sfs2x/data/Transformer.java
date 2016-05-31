package com.tvd12.ezyfox.sfs2x.data;

import com.smartfoxserver.v2.entities.data.SFSDataWrapper;

/**
 * @author tavandung12
 * Created on May 28, 2016
 *
 */
public interface Transformer {
    
    /**
     * Transform a value to SFSDataWrapper
     * 
     * @param value value to transform
     * @return a SFSDataWrapper object
     */
    public SFSDataWrapper transform(Object value);
}

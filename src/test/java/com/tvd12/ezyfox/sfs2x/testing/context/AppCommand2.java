package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.exception.ExtensionException;

/**
 * @author tavandung12
 * Created on Jun 14, 2016
 *
 */
public class AppCommand2 {
    
    public AppCommand2() throws ExtensionException  {
        throw new ExtensionException();
    }
    
    public String hello() {
        return "hello";
    }
    
}

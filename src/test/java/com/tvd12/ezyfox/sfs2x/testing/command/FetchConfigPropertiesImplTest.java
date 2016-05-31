package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.FetchConfigPropertiesImpl;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class FetchConfigPropertiesImplTest extends BaseCommandTest2 {
    
    @Test
    public void test() {
        new FetchConfigPropertiesImpl(context, api, extension).execute();
    }
    
}

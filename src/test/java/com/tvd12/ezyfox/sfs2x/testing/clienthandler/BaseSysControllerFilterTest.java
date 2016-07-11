package com.tvd12.ezyfox.sfs2x.testing.clienthandler;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.smartfoxserver.v2.controllers.SystemRequest;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.filter.FilterAction;
import com.tvd12.ezyfox.sfs2x.filter.BaseSysControllerFilter;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;

/**
 * @author tavandung12
 * Created on Jul 2, 2016
 *
 */
public class BaseSysControllerFilterTest extends BaseHandlerTest {

    @Test
    public void test() throws Exception {
        BaseSysControllerFilter filter = new BaseSysControllerFilter(context, SystemRequest.AddBuddy);
        Assert.assertEquals(filter.handleClientRequest(sfsUser, new SFSObject()), FilterAction.CONTINUE);
        
        filter = new BaseSysControllerFilter(context, SystemRequest.JoinRoom);
        Assert.assertEquals(filter.handleClientRequest(sfsUser, new SFSObject()), FilterAction.HALT);
    }
    
}

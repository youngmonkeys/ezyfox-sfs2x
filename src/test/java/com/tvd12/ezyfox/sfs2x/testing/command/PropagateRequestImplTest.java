/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.tvd12.ezyfox.core.bridge.ClientRequestHandlers;
import com.tvd12.ezyfox.sfs2x.command.impl.PropagateRequestImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.VideoPokerRoom;

/**
 * @author tavandung12
 *
 */
public class PropagateRequestImplTest extends BaseCommandTest2 {

	public PropagateRequestImplTest() {
		super();
		context.set(ClientRequestHandlers.class, new ClientRequestHandlers() {

			@Override
			public Object getClientRequestHandler(Object cmd) {
				return new BaseClientRequestHandler() {
					
					@Override
					public void handleClientRequest(User user, ISFSObject params) {
						System.out.println("PropagateRequestImplTest");
					}
				};
			}

			@Override
			public boolean containsClientRequestHandler(Object cmd) {
				return "hello".equals(cmd);
			}
			
		});
	}
	
    @Test(priority = 1)
    public void test() {
        PropagateRequestImpl command = new PropagateRequestImpl(context, api, extension);
        command.command("hello")
            .data(new VideoPokerRoom())
            .user(user)
            .param("a", "b")
            .only("a")
            .ignore("b")
            .execute();
    }
    
    @Test(priority = 2)
    public void test2() {
    	PropagateRequestImpl command = new PropagateRequestImpl(context, api, extension);
        command.command("hello")
            .data(null)
            .user(user)
            .execute();
    }
    
    @Test(priority = 3, expectedExceptions = {IllegalStateException.class})
    public void test3() {
    	PropagateRequestImpl command = new PropagateRequestImpl(context, api, extension);
        command.command("")
            .data(null)
            .user(user)
            .execute();
    }
    
    @Test(priority = 5, expectedExceptions = {IllegalStateException.class})
    public void test5() {
    	PropagateRequestImpl command = new PropagateRequestImpl(context, api, extension);
        command.command(null)
            .data(null)
            .user(user)
            .execute();
    }
    
    @Test(priority = 6, expectedExceptions = {IllegalStateException.class})
    public void test6() {
    	PropagateRequestImpl command = new PropagateRequestImpl(context, api, extension);
        command.command("no comment")
            .data(null)
            .user(user)
            .execute();
    }
    
    public static void main(String[] args) {
        new PropagateRequestImplTest().test();
    }
}

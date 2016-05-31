package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.exceptions.SFSLoginException;
import com.tvd12.ezyfox.sfs2x.command.impl.CreateNPCImpl;
import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class CreateNPCImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        CreateNPCImpl command = new CreateNPCImpl(context, api, extension);
        command.forceLogin(true)
        .username("npc")
        .zone(apiZone)
        .zone(apiZone.getName())
        .execute();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test2() throws SFSLoginException {
        doThrow(SFSLoginException.class)
        .when(api)
        .createNPC(any(String.class), any(Zone.class), any(boolean.class));
        CreateNPCImpl command = new CreateNPCImpl(context, api, extension);
        command.forceLogin(true)
        .username("npc")
        .zone(apiZone)
        .zone(apiZone.getName())
        .execute();
        doNothing()
        .when(api)
        .createNPC(any(String.class), any(Zone.class), any(boolean.class));
    }
    
}

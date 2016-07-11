/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.sfs2x.command.impl.FindRoomImpl;
import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 *
 */
public class GetRoomImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        FindRoomImpl command = new FindRoomImpl(context, api, extension);
        command.by(ROOM_NAME);
        command.by(1);
        when(zone.getRoomById(2)).thenReturn(sfsRoom);
        command.by(2);
        command.by(user);
        when(sfsUser.getJoinedRooms()).thenReturn(Lists.newArrayList(sfsRoom));
        command.by(user);
        
        ExUser exUser = new ExUser();
        exUser.setName("");
        command.by(exUser);
    }
    
    public static class ExUser extends ApiUser {
        
    }
    
}

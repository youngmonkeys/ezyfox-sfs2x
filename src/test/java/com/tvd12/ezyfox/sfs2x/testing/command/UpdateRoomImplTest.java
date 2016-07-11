package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.SFSRoom;
import com.smartfoxserver.v2.entities.Zone;
import com.tvd12.ezyfox.sfs2x.command.impl.UpdateRoomImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

import static org.mockito.Mockito.*;

public class UpdateRoomImplTest extends BaseCommandTest {

    @Test
    public void test() {
        PokerRoom pokerRoom = new PokerRoom();
        pokerRoom.setName("poker");
        Zone zone = mock(Zone.class);
        when(zone.getRoomByName("poker")).thenReturn(new SFSRoom("poker"));
        when(extension.getParentZone()).thenReturn(zone);
        UpdateRoomImpl update = new UpdateRoomImpl(context, api, extension);
        update.toClient(true)
            .include("1", "2")
            .exclude("1", "2")
            .room(pokerRoom)
            .user(null)
            .execute();
        
        update.toClient(false)
            .room(pokerRoom)
            .user(null)
            .execute();
        
        when(zone.getRoomByName("poker")).thenReturn(null);
        update.toClient(false)
            .room(pokerRoom)
            .user(null)
            .execute();
        
    }
    
    @Test
    public void test1() {
        PokerRoom pokerRoom = new PokerRoom();
        pokerRoom.setName("poker");
        Zone zone = mock(Zone.class);
        when(zone.getRoomByName("poker")).thenReturn(new SFSRoom("poker"));
        when(extension.getParentZone()).thenReturn(zone);
        UpdateRoomImpl update = new UpdateRoomImpl(context, api, extension);
        update.toClient(true)
            .room(pokerRoom)
            .user(null)
            .execute();
        
        update.toClient(false)
            .room(pokerRoom)
            .user(null)
            .execute();
        
        when(zone.getRoomByName("poker")).thenReturn(null);
        update.toClient(false)
            .room(pokerRoom)
            .user(null)
            .execute();
        
    }
    
}

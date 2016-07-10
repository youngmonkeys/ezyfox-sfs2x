package com.tvd12.ezyfox.sfs2x.testing.context;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiRoom;

public class ApiModelFactory {

    private ApiModelFactory() {}
    
    public static ISFSApi createSFSApi() throws SFSCreateRoomException {
        
        ApiRoom apiRoom = createRoom();
        Room sfsRoom = mock(Room.class);
        when(sfsRoom.getProperty(APIKey.ROOM)).thenReturn(apiRoom);

        ISFSApi api = mock(ISFSApi.class);
        when(api.createRoom(any(Zone.class), any(CreateRoomSettings.class), any(User.class)))
            .thenReturn(sfsRoom);
        
        return api;
    }
    
    public static ISFSExtension createExtension() {
        ISFSExtension extension = mock(ISFSExtension.class);
        
        return extension;
    }
    
    public static ApiRoom createRoom() {
        PokerRoom room = new PokerRoom();
        room.setName("Lobby");
        return room;
    }
    
}

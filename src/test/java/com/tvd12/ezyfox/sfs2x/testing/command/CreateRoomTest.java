package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiRoomExtension;
import com.tvd12.ezyfox.sfs2x.command.impl.CreateRoomImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.ApiModelFactory;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

public class CreateRoomTest extends BaseCommandTest2 {

    public CreateRoomTest() {
        super();
        
    }
    
    @Test
    public void test() {
        PokerRoom room = new PokerRoom();
        room.setExtension(new ApiRoomExtension("abc", getClass()));
        new CreateRoomImpl(context, api, extension)
            .agents(room)
            .execute();
            
    }
    
    @Test
    public void test2() {
        new CreateRoomImpl(context, api, extension)
            .agents(new ExRoom1());
            
    }
    
    @Test
    public void test3() {
        new CreateRoomImpl(context, api, extension)
            .agents(new ExRoom1());
            
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getRoomAgentClassAndValidateInvalidCaseTest() {
        new CreateRoomImpl(context, api, extension)
        .agents(new ExRoom())
        .execute();
    }
    
    @SuppressWarnings("unchecked")
    @Test(expectedExceptions = {RuntimeException.class})
    public void executeInvalidCaseTest() throws SFSCreateRoomException {
        ISFSApi api = ApiModelFactory.createSFSApi();
        when(api.createRoom(any(Zone.class), any(CreateRoomSettings.class), any(User.class)))
            .thenThrow(SFSCreateRoomException.class);
        
        new CreateRoomImpl(context, api, extension)
        .agents(new PokerRoom())
        .execute();
    }
    
    public static class ExRoom extends ApiRoom {
        
    }
    
    @RoomAgent
    public static class ExRoom1 extends ApiRoom {
        @Variable
        public int getValue() throws Exception {
            throw new Exception();
        }
    }
    
}

package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.tvd12.ezyfox.sfs2x.command.impl.UpdateUserImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.ApiModelFactory;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;

public class UpdateUserImplTest extends BaseCommandTest {

    @Test
    public void test() {
        AppUser user = new AppUser();
        user.setName("hello");
        User sfsUser = mock(User.class);
        List<UserVariable> vars = new ArrayList<>();
        vars.add(new SFSUserVariable("1", "abc"));
        when(sfsUser.getName()).thenReturn("hello");
        when(sfsUser.getVariables()).thenReturn(vars);
        when(api.getUserByName("hello")).thenReturn(sfsUser);
        UpdateUserImpl update = new UpdateUserImpl(context, api, extension);
        update.toClient(true)
        .include("1", "2", "3")
        .exclude("2")
        .user(user)
        .execute();
        
        update.toClient(false)
        .user(user)
        .execute();
        
        when(api.getUserByName("hello")).thenReturn(null);
        update.toClient(true)
        .user(user)
        .execute();
    }
    
    @Test
    public void test1() {
        AppUser user = new AppUser();
        user.setName("hello");
        User sfsUser = mock(User.class);
        when(sfsUser.getName()).thenReturn("hello");
        when(sfsUser.getVariables()).thenReturn(new ArrayList<UserVariable>());
        when(api.getUserByName("hello")).thenReturn(sfsUser);
        UpdateUserImpl update = new UpdateUserImpl(context, api, extension);
        update.toClient(true)
        .user(user)
        .execute();
        
        update.toClient(false)
        .user(user)
        .execute();
        
        when(api.getUserByName("hello")).thenReturn(null);
        update.toClient(true)
        .user(user)
        .execute();
    }
    
    @SuppressWarnings("unchecked")
    @Test(expectedExceptions = {RuntimeException.class})
    public void testInvalidCase() throws SFSCreateRoomException {
        api = ApiModelFactory.createSFSApi();
        AppUser user = new AppUser();
        user.setName("hello");
        User sfsUser = mock(User.class);
        when(sfsUser.getName()).thenReturn("hello");
        when(sfsUser.getVariables()).thenReturn(new ArrayList<UserVariable>());
        when(api.getUserByName("hello")).thenReturn(sfsUser);
        doThrow(SFSVariableException.class)
            .when(api).setUserVariables(any(User.class), any(List.class));
        UpdateUserImpl update = new UpdateUserImpl(context, api, extension);
        update.toClient(true)
        .user(user)
        .execute();
        
        api = ApiModelFactory.createSFSApi();
    }
    
}

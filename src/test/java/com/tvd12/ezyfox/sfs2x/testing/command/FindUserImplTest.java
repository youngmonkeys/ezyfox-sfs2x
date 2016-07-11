package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.FindUserImpl; 

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class FindUserImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        FindUserImpl command = new FindUserImpl(context, api, extension);
        when(api.getUserById(1)).thenReturn(sfsUser);
        command.by(1);
        when(api.getUserById(2)).thenReturn(null);
        command.by(2);
        command.by("abcxyz123");
    }
    
}

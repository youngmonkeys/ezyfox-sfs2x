package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.FindRoomImpl;
import static org.testng.Assert.*;

/**
 * @author tavandung12
 * Created on Jul 2, 2016
 *
 */
public class FindRoomImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        FindRoomImpl findRoom = new FindRoomImpl(context, api, extension);
        assertNull(findRoom.by("zzzzz"));
    }
    
}

package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.AddCommandImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppCommand3;
import static org.testng.Assert.*;

/**
 * @author tavandung12
 * Created on Jun 14, 2016
 *
 */
public class AddCommandImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        AddCommandImpl command = new AddCommandImpl(context, api, extension);
        command.add(AppCommand3.class, AppCommand3.class);
        assertNotNull(context.command(AppCommand3.class));
    }
    
}

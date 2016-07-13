package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.GetCurrentFolderImpl;

/**
 * @author tavandung12
 * Created on Jul 12, 2016
 *
 */
public class GetCurrentFolderImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        GetCurrentFolderImpl cmd = new GetCurrentFolderImpl(context, api, extension);
        cmd.execute();
    }
    
}

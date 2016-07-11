package com.tvd12.ezyfox.sfs2x.testing.command;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.sfs2x.command.impl.CommandUtil;

public class CommandUtilTest extends BaseCommandTest2 {

    @Override
    public Class<?> getTestClass() {
        return CommandUtil.class;
    }
    
    @Test
    public void getSFSUserListByNamesTest() {
        List<String> usernames = Lists.newArrayList(USER_NAME);
        Assert.assertEquals(CommandUtil.getSFSUserListByNames(usernames, api).size(), 1);
    }
    
}

package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.LogImpl;

public class LogImplTest extends BaseCommandTest {

    @Test
    public void test() {
        LogImpl log = new LogImpl(context, api, extension);
        log.from(this);
        log.debug("");
        log.debug("", new Exception());
        log.debug("user {}", "monkey");
        log.error("");
        log.error("", new Exception());
        log.error("user {}", "monkey");
        log.info("");
        log.info("", new Exception());
        log.info("user {}", "monkey");
        log.warn("");
        log.warn("", new Exception());
        log.warn("user {}", "monkey");
    }
    
}

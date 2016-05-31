package com.tvd12.ezyfox.sfs2x.testing.serializer;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.buddylist.BuddyVariable;
import com.tvd12.ezyfox.core.structure.BuddyClassUnwrapper;
import com.tvd12.ezyfox.sfs2x.serializer.BuddyAgentUnwrapper;
import com.tvd12.test.base.BaseTest;

import lombok.Data;

/**
 * @author tavandung12
 * Created on May 29, 2016
 *
 */
public class BuddyAgentUnwrapperTest extends BaseTest {

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        BuddyAgentUnwrapper.buddyAgentUnwrapper();
        List<BuddyVariable> vars = BuddyAgentUnwrapper.buddyAgentUnwrapper()
                .unwrap(new BuddyClassUnwrapper(ClassBuddy.class), new ClassBuddy());
        assertEquals(vars.size(), 2);
    }
    
    @Data
    public static class ClassBuddy {
        
        @com.tvd12.ezyfox.core.annotation.BuddyVariable
        private String name = "dungtv";
        
        @com.tvd12.ezyfox.core.annotation.BuddyVariable
        private String nickName = "dung";
        
    }

}

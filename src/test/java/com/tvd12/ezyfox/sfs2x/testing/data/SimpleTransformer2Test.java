package com.tvd12.ezyfox.sfs2x.testing.data;


import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.sfs2x.data.impl.SimpleTransformer;
import com.tvd12.ezyfox.sfs2x.testing.command.BaseCommandTest2;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerResult;

import lombok.Data;

/**
 * @author tavandung12
 * Created on Jul 5, 2016
 *
 */
public class SimpleTransformer2Test extends BaseCommandTest2 {

    @Test
    public void test() {
        SimpleTransformer transformer = new SimpleTransformer(context);
        SFSDataWrapper wrapper = transformer.transform(new PokerResult());
        ISFSObject sfsobject = (ISFSObject)wrapper.getObject();
        assertEquals(wrapper.getTypeId(), SFSDataType.SFS_OBJECT);
        assertEquals(sfsobject.getUtfString("name"), "name");
        
        wrapper = transformer.transform(new Result());
        sfsobject = (ISFSObject)wrapper.getObject();
        assertEquals(wrapper.getTypeId(), SFSDataType.SFS_OBJECT);
        assertEquals(sfsobject.getUtfString("2"), "r");
    }
    
    @Data
    public static class Result {
        @ResponseParam("1")
        private int id = 1;
        @ResponseParam("2")
        private String name = "r";
    }
    
}

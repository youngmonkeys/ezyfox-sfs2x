/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.responseparameterparser;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 *
 */
public class ResponseParamsClassTest {

    @Test
    public void test() {
        ISFSObject object = ResponseParamSerializer.getInstance()
                .object2params(new ResponseParamsClass(VideoPokerRoom.class), new VideoPokerRoom());
        assertEquals(object.getIntArray("1").size(), 3);
    }
    
    public static void main(String[] args) {
        ISFSObject object = ResponseParamSerializer.getInstance()
                .object2params(new ResponseParamsClass(VideoPokerRoom.class), new VideoPokerRoom());
        assertEquals(object.getIntArray("1").size(), 3);
    }
    
}

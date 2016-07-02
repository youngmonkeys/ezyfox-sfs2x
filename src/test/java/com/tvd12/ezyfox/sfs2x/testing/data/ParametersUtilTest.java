package com.tvd12.ezyfox.sfs2x.testing.data;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.sfs2x.data.impl.ParametersUtil;
import com.tvd12.test.base.BaseTest;
import static org.testng.Assert.*;

/**
 * @author tavandung12
 * Created on Jul 2, 2016
 *
 */
public class ParametersUtilTest extends BaseTest {

    /* (non-Javadoc)
     * @see com.tvd12.test.base.BaseTest#getTestClass()
     */
    @Override
    public Class<?> getTestClass() {
        return ParametersUtil.class;
    }
    
    @Test
    public void test() {
        ISFSObject sfsobj = new SFSObject();
        ISFSObject sfsobj2 = new SFSObject();
        sfsobj2.putInt("1", 1);
        sfsobj.putSFSObject("1", sfsobj2);
        ISFSArray sfsarray = new SFSArray();
        sfsarray.addInt(1);
        ISFSObject sfsobj3 = new SFSObject();
        sfsobj3.putInt("1", 1);
        sfsarray.addSFSObject(sfsobj3);
        sfsobj.putSFSArray("2", sfsarray);
        sfsobj.putUtfString("3", "3");
        Parameters params = ParametersUtil.sfsobject2parameters(sfsobj);
        assertEquals(params.get("1", Parameters.class).get("1", Integer.class), new Integer(1));
        assertEquals(params.get("2", Parameters.class).get(0, Integer.class), new Integer(1));
        assertEquals(params.get("2", Parameters.class).get(1, Parameters.class).get("1", Integer.class), new Integer(1));
        assertEquals(params.get("3", String.class), "3");
    }
    
}

package com.tvd12.ezyfox.sfs2x.testing.useragentwrapper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;

import static org.testng.Assert.*;

public class VariableTest {

	@Test
	public void test() {
		User user = mock(User.class);
		UserVariable variable = new SFSUserVariable("dung", new Long(10L));
		when(user.getVariable("dung")).thenReturn(variable);
		assertEquals(new Double(10), variable.getDoubleValue());
	}
	
	public static void main(String[] args) {
		User user = mock(User.class);
		UserVariable variable = new SFSUserVariable("dung", null);
		when(user.getVariable("dung")).thenReturn(variable);
		assertEquals(null, variable.getDoubleValue());
		
		ISFSObject sfsObject = new SFSObject();
		sfsObject.put("1", new SFSDataWrapper(SFSDataType.BYTE_ARRAY, null));
		sfsObject.put("2", new SFSDataWrapper(SFSDataType.BOOL, null));
		
		System.out.print(new String(sfsObject.getByteArray("1")));
		System.out.print(sfsObject.getByte("1"));
	}
	
}

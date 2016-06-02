package com.tvd12.ezyfox.sfs2x.testing.useragentwrapper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.structure.AgentClassWrapper;
import com.tvd12.ezyfox.sfs2x.serializer.AgentDeserializer;
import com.tvd12.ezyfox.sfs2x.testing.ExampleUser;

public class UserAgentWrapperTest {
	private User user;
	
	public UserAgentWrapperTest() {
	    this.init();
	}
	
	public void init() {
		user = mock(User.class);
		List<UserVariable> variables = new ArrayList<>();
		variables.add(new SFSUserVariable("money", new Long(1000L)));
		variables.add(new SFSUserVariable("fri", createArray()));
		variables.add(new SFSUserVariable("fstn", "Nguyen"));
		variables.add(new SFSUserVariable("lstn", "Tuan Duong"));
		variables.add(new SFSUserVariable("zzz", "123"));
		when(user.getVariable("money")).thenReturn(new SFSUserVariable("money", new Long(1000L)));
		when(user.getVariable("fri")).thenReturn(new SFSUserVariable("money", createArray()));
		when(user.getVariable("fstn")).thenReturn(new SFSUserVariable("money", "Nguyen"));
		when(user.getVariable("lstn")).thenReturn(new SFSUserVariable("money", "Tuan Duong"));
		when(user.getVariables()).thenReturn(variables);
		
	}
	
	@Test
	public void testInvalidCase() throws ExtensionException {
	    AgentClassWrapper clazz = new AgentClassWrapper(ExampleUser.class);
		ExampleUser exUser = (ExampleUser) new AgentDeserializer().deserialize(clazz, user.getVariables());
		assertNotNull(exUser);
		assertEquals(new Long(1000L), exUser.getMoney());
		assertEquals("Nguyen", exUser.getFirstName());
		assertEquals("Tuan Duong", exUser.getLastName());
		assertEquals(2, exUser.getFriends().size());
		assertEquals("Cung", exUser.getFriends().get(0).getFirstName());
		assertEquals("Hoang", exUser.getFriends().get(1).getFirstName());
		
	}
	
	private ISFSArray createArray() {
		ISFSArray array = new SFSArray();
		ISFSObject one = new SFSObject();
		one.putUtfString("fstn", "Cung");
		one.putUtfString("lstn", "Vinh Nam");
		array.addSFSObject(one);
		
		ISFSObject two = new SFSObject();
		two.putUtfString("fstn", "Hoang");
		two.putUtfString("lstn", "Duy Hieu");
		array.addSFSObject(two);
		
		return array;
	}
}

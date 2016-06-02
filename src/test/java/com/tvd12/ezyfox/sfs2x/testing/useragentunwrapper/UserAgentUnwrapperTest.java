package com.tvd12.ezyfox.sfs2x.testing.useragentunwrapper;

import java.util.List;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.structure.AgentClassUnwrapper;
import com.tvd12.ezyfox.sfs2x.serializer.UserAgentSerializer;

import static org.testng.Assert.*;

public class UserAgentUnwrapperTest {

	@SuppressWarnings("unchecked")
    @Test
	public void testValidCase() throws ExtensionException {
		UserAgentForUnwrapper userAgent = new UserAgentForUnwrapper();
		AgentClassUnwrapper clazz = new AgentClassUnwrapper(UserAgentForUnwrapper.class);
		List<UserVariable> variables = UserAgentSerializer
				.getInstance()
				.serialize(clazz, userAgent);
		assertNotNull(variables);
		assertEquals(3, variables.size());
		assertEquals("dzung", variables.get(0).getStringValue());
		assertEquals(new Integer(123), variables.get(1).getIntValue());
		assertEquals("kitty", variables.get(2).getSFSObjectValue().getUtfString("name"));
	}
	
}

package com.tvd12.ezyfox.sfs2x.testing.parameterparser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.structure.RequestListenerClass;
import com.tvd12.ezyfox.sfs2x.serializer.RequestParamDeserializer;
import com.tvd12.test.performance.Performance;
import com.tvd12.test.performance.Script;

import static org.testng.Assert.*;

public class ParameterParserTest {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(ParameterParserTest.class);

	@Test
	public void testValidObjectCase() {
		ISFSObject first = mock(ISFSObject.class);
		when(first.get("name")).thenReturn(new SFSDataWrapper(SFSDataType.UTF_STRING, "dung"));
		when(first.get("adr")).thenReturn(new SFSDataWrapper(SFSDataType.UTF_STRING, "bg"));
		
		ISFSObject second = mock(ISFSObject.class);
		when(second.get("name")).thenReturn(new SFSDataWrapper(SFSDataType.UTF_STRING, "dung1"));
		when(second.get("adr")).thenReturn(new SFSDataWrapper(SFSDataType.UTF_STRING, "bg1"));
		
		ISFSObject third = mock(ISFSObject.class);
		when(third.get("name")).thenReturn(new SFSDataWrapper(SFSDataType.UTF_STRING, "dung2"));
		when(third.get("adr")).thenReturn(new SFSDataWrapper(SFSDataType.UTF_STRING, "bg2"));
		
		when(first.get("user")).thenReturn(new SFSDataWrapper(SFSDataType.SFS_OBJECT, second));
		when(second.get("user")).thenReturn(new SFSDataWrapper(SFSDataType.SFS_OBJECT, third));

		RequestListenerClass clazz = new RequestListenerClass(User.class);
		User user = (User) RequestParamDeserializer.getInstance().deserialize(clazz, first);
		assertNotNull(user);
		assertNotNull(user.getUser());
		assertNotNull(user.getUser().getUser());
		
		assertEquals(user, new User("dung", "bg"));
		assertEquals(user.getUser(), new User("dung1", "bg1"));
		assertEquals(user.getUser().getUser(), new User("dung2", "bg2"));
	}
	
	@Test(timeOut = 100)
	public void testValidArrayCase() {
		ISFSObject first = new SFSObject();
		first.putUtfString("name", "dung");
		first.putUtfString("adr", "bg");
		
		ISFSArray array = new SFSArray();
		for(int i = 1 ; i <= 3 ; i++) {
			ISFSObject obj = new SFSObject();
			obj.putUtfString("name", "dung" + i);
			obj.putUtfString("adr", "bg" + i);
			array.addSFSObject(obj);
		}
		first.putSFSArray("users", array);
		
		RequestListenerClass clazz = new RequestListenerClass(User.class);
		User user = (User)(RequestParamDeserializer.getInstance().deserialize(clazz, first));
		assertNotNull(user);
		assertEquals(user.getUsers().size(), 3);
		
		for(int i = 1 ; i <= 3 ; i++) {
			assertEquals(user.getUsers().get(i - 1), new User("dung" + i, "bg" + i));
		}
		
	}
	
	public void testHasNoSetterMethodCase() {
		ISFSObject obj = new SFSObject();
		obj.putUtfString("name", "dung");
		RequestListenerClass clazz = new RequestListenerClass(User.class);
		RequestParamDeserializer.getInstance().deserialize(clazz, obj);
	}
	
	@Test
	public void testHasNoAnnotation() {
		ISFSObject obj = new SFSObject();
		obj.putUtfString("name", "dung");
		RequestListenerClass clazz = new RequestListenerClass(NoParam.class);
		RequestParamDeserializer.getInstance().deserialize(clazz, obj);
	}
	
	@Test
	public void performanceTest() {
		final ISFSObject first = new SFSObject();
		first.putUtfString("name", "dung");
		first.putUtfString("adr", "bg");
		
		ISFSArray array = new SFSArray();
		for(int i = 1 ; i <= 3 ; i++) {
			ISFSObject obj = new SFSObject();
			obj.putUtfString("name", "dung" + i);
			obj.putUtfString("adr", "bg" + i);
			array.addSFSObject(obj);
		}
		first.putSFSArray("users", array);
		final RequestListenerClass clazz = new RequestListenerClass(User.class);
		long time = Performance.create()
				.loop(1000000)
				.test(new Script() {
                    @Override
                    public void execute() {
    					try {
    					    RequestParamDeserializer.getInstance().deserialize(clazz, first);
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
                    }
				}).getTime();
		LOGGER.info("ParameterParserTest#performanceTest time(new) = " + time);
		
	}
	
	public void performanceTest2() throws ExtensionException {
        final ISFSObject first = new SFSObject();
        first.putUtfString("name", "dung");
        first.putUtfString("adr", "bg");
        
        ISFSArray array = new SFSArray();
        for(int i = 1 ; i <= 3 ; i++) {
            ISFSObject obj = new SFSObject();
            obj.putUtfString("name", "dung" + i);
            obj.putUtfString("adr", "bg" + i);
            array.addSFSObject(obj);
        }
        first.putSFSArray("users", array);
        
        final RequestListenerClass rlc = new RequestListenerClass(User.class);
        
        long time = Performance.create()
                .loop(1000000)
                .test(new Script() {
                    @Override
                    public void execute() {
                        RequestParamDeserializer.getInstance().deserialize(rlc, first);
//                      User user = (User)RequestParamParser2.getInstance().assignValues(rlc, first);
//                      System.out.println(user.getAddress());
//                      System.out.println(user.getName());
//                      System.out.println(user.getUsers());
                    }
                }).getTime();
        LOGGER.info("ParameterParserTest#performanceTest2 time = " + time);
        
    }
	
}

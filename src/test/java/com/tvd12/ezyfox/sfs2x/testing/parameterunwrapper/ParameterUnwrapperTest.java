package com.tvd12.ezyfox.sfs2x.testing.parameterunwrapper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.sfs2x.serializer.ParameterUnwrapper;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamParser;
import com.tvd12.ezyfox.sfs2x.testing.responseparameterparser.ResponseParamParserTest;
import com.tvd12.test.performance.Performance;
import com.tvd12.test.performance.Script;

import lombok.Data;

public class ParameterUnwrapperTest {

	private static final Logger LOGGER
    	= LoggerFactory.getLogger(ResponseParamParserTest.class);
	
	public void getValueTestPerformance() throws ExtensionException {
		final Method method = ReflectMethodUtil.getMethod("getValue", 
				ParameterUnwrapper.class, 
				Class.class, Object.class);
		method.setAccessible(true);
		final Object obj = ResponseParamParser.getInstance();
		long time = Performance.create()
				.test(new Script() {
                    @Override
                    public void execute() {
    					try {
    						method.invoke(obj, String.class, new String("dung"));
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
                    }
				})
				.getTime();
		LOGGER.info("getValueTestPerformance time = " + time);
	}
	
	public void arrayToCollectionPerformance() throws ExtensionException {
		final Method method = ReflectMethodUtil.getMethod("arrayToCollection", 
				ParameterUnwrapper.class, 
				Object.class, Class.class);
		method.setAccessible(true);
		final Object obj = ResponseParamParser.getInstance();
		final String[] strs = {"dung", "duong"};
		long time = Performance.create()
				.test(new Script() {
                    @Override
                    public void execute() {
    					try {
    						method.invoke(obj, strs, String.class);
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
                    }
				})
				.getTime();
		LOGGER.info("arrayToCollectionPerformance time = " + time);
	}
	
	public void parseCollectionPerformance() throws ExtensionException {
		final Method method = ReflectMethodUtil.getMethod("parseCollection", 
				ParameterUnwrapper.class, 
				Field.class, Object.class);
		method.setAccessible(true);
		final ClassA classA = new ClassA();
		final Object obj = ResponseParamParser.getInstance();
		final Field field = ReflectFieldUtil.getField("strings", ClassA.class);
		long time = Performance.create()
				.test(new Script() {
                    @Override
                    public void execute() {
    					try {
    						method.invoke(obj, field, classA.getStrings());
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
                    }
				})
				.getTime();
		LOGGER.info("parseCollectionPerformance time = " + time);
	}
	
	public void assignValuePerformance() throws ExtensionException {
		final Method method = ReflectMethodUtil.getMethod("assignValue", 
				ParameterUnwrapper.class, 
				ISFSObject.class, Entry.class, Object.class);
		method.setAccessible(true);
		final ClassA classA = new ClassA();
		final Object obj = ResponseParamParser.getInstance();
		final Field field = ReflectFieldUtil.getField("strings", ClassA.class);
		@SuppressWarnings("unchecked")
		final Entry<String, Field> entry = mock(Entry.class);
		when(entry.getKey()).thenReturn("strings");
		when(entry.getValue()).thenReturn(field);
		final ISFSObject sfsObject = new SFSObject();
		long time = Performance.create()
				.loop(1)
				.test(new Script() {
                    @Override
                    public void execute() {
    					try {
    						method.invoke(obj, sfsObject, entry, classA);
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
                    }
				})
				.getTime();
		LOGGER.info("assignValuePerformance time = " + time);
	}
	
	@Data
	public static class ClassA {
		public ClassA() {
			strings.add("dung");
			strings.add("duong");
		}
		List<String> strings = new ArrayList<>();
	}
	
	public static void main(String[] args) throws ExtensionException {
		new ParameterUnwrapperTest().assignValuePerformance();
	}
}

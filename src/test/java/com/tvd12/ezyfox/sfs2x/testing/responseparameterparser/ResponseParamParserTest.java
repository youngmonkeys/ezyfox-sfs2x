package com.tvd12.ezyfox.sfs2x.testing.responseparameterparser;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.structure.ResponseHandlerClass;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer;
import com.tvd12.test.performance.Performance;
import com.tvd12.test.performance.Script;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ResponseParamParserTest {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(ResponseParamParserTest.class);
    
	@Test
	public void testValidObject() {
		UserBettingActionListener listener = new UserBettingActionListener();
		ResponseHandlerClass clazz = new ResponseHandlerClass(UserBettingActionListener.class);
		ISFSObject sfsObject = new ResponseParamSerializer().object2params(clazz, listener);
		assertTrue(sfsObject.getInt("result") == 100);
		assertEquals(sfsObject.getUtfString("msg"), "hello");
		byte[] xyz = (byte[])sfsObject.get("xyz").getObject();
		assertTrue(xyz[0] == 1);
		
		Collection<Integer> abc = sfsObject.getIntArray("abc");
		
		assertEquals(new Integer(4), abc.iterator().next());
				
		ISFSObject value = sfsObject.getSFSObject("value");
		assertTrue(value.getInt("value") == 100);
		ISFSArray nexts = value.getSFSArray("chips");
		for(int i = 0 ; i < 3 ; i++) {
			ISFSObject next = nexts.getSFSObject(i);
			assertTrue(next.getInt("money") == i + 10);
		}
	}
	
	@Test(expectedExceptions = {IllegalStateException.class})
	public void collectionOfCollectionCase() {
	    ResponseHandlerClass clazz = new ResponseHandlerClass(ClassA.class);
        new ResponseParamSerializer().object2params(clazz, new ClassA());
	}
	
	@Test
    public void collectionOfArrayCase() {
        ResponseHandlerClass clazz = new ResponseHandlerClass(ClassB.class);
        ISFSObject params = new ResponseParamSerializer().object2params(clazz, new ClassB());
        assertNotNull(params.getSFSArray("lby"));
        ISFSArray array = params.getSFSArray("lby");
        for(byte i = 0 ; i < 3 ; i++) {
            byte[] value = array.getByteArray(i);
            byte[] expected = {(byte)(i*1), (byte)(i*2), (byte)(i*3)};
            for(int j = 0 ; j < value.length ; j++)
                assertEquals(expected[j], value[j]);
            LOGGER.info("byte array is " + value[0] + ", " + value[1] + ", " + value[2]);
        }
    }
	
	@Test
	public void testBiArrayCase() {
	    ResponseHandlerClass clazz = new ResponseHandlerClass(ClassC.class);
        ISFSObject params = new ResponseParamSerializer().object2params(clazz, new ClassC());
        
        ISFSArray a0 = params.getSFSArray("a0");
        
        //test boolean array
        assertEquals(a0.size(), 2);
        Collection<Boolean> a01 = a0.getBoolArray(0);
        assertEquals(a01.size(), 2);
        assertEquals(a01.iterator().next(), Boolean.TRUE);
        assertEquals((Boolean)a01.toArray()[1], Boolean.FALSE);
        Collection<Boolean> a02 = a0.getBoolArray(1);
        assertEquals(a02.size(), 2);
        assertEquals(a02.iterator().next(), Boolean.FALSE);
        assertEquals((Boolean)a02.toArray()[1], Boolean.TRUE);
        
        ISFSArray a18 = params.getSFSArray("a18");
        
        //test boolean array
        assertEquals(a18.size(), 2);
        
        ISFSArray a181 = a18.getSFSArray(0);
        assertEquals(a181.size(), 2);
        ISFSObject a1811 = a181.getSFSObject(0);
        assertEquals(a1811.getUtfString("a"), "1");
        ISFSObject a1812 = a181.getSFSObject(1);
        assertEquals(a1812.getUtfString("a"), "2");
        
        ISFSArray a182 = a18.getSFSArray(1);
        assertEquals(a182.size(), 2);
        ISFSObject a1821 = a182.getSFSObject(0);
        assertEquals(a1821.getUtfString("a"), "3");
        ISFSObject a1822 = a182.getSFSObject(1);
        assertEquals(a1822.getUtfString("a"), "4");
	}
	
	@Test(expectedExceptions = {IllegalStateException.class})
	public void testBiArrayCase2() {
	    ResponseHandlerClass clazz = new ResponseHandlerClass(ClassD.class);
        new ResponseParamSerializer().object2params(clazz, new ClassD());
	}
	
	@Test
    public void testNullValueCase() {
        ResponseHandlerClass clazz = new ResponseHandlerClass(ClassE.class);
        ISFSObject params = new ResponseParamSerializer().object2params(clazz, new ClassE());
        assertNull(params.getUtfStringArray("strs"));
    }
	
	@Test
	public void collectionStringCase() {
	    ResponseHandlerClass clazz = new ResponseHandlerClass(ClassF.class);
        ISFSObject params = new ResponseParamSerializer().object2params(clazz, new ClassF());
	    Collection<String> dreams = params.getUtfStringArray("dreams");
	    assertEquals(2, dreams.size());
	    Iterator<String> it = dreams.iterator();
	    assertEquals("dung", it.next());
	    assertEquals("duong", it.next());
	}
	
	@Test
    public void collectionStringCase1() {
	    ResponseHandlerClass handlerClass = new ResponseHandlerClass(ClassF.class);
        ISFSObject params = new ResponseParamSerializer().object2params(handlerClass, new ClassF());
        Collection<String> dreams = params.getUtfStringArray("dreams");
        assertEquals(2, dreams.size());
        Iterator<String> it = dreams.iterator();
        assertEquals("dung", it.next());
        assertEquals("duong", it.next());
    }
	
	@Test
	public void performanceTest() {
		final ClassF classF = new ClassF();
		long time = System.currentTimeMillis();
		for(int i = 0 ; i < 1000000 ; i++) {
			ISFSObject params = new SFSObject();
			params.putUtfStringArray("dreams", classF.getDreams());
		}
		long offset = System.currentTimeMillis() - time;
		LOGGER.info("ResponseParamParserTest#performanceTest with no parser time = " + offset);
		final ResponseHandlerClass handlerClass = new ResponseHandlerClass(ClassF.class);
		offset = Performance.create()
		        .test(new Script() {
                    @Override
                    public void execute() {
                        new ResponseParamSerializer().object2params(handlerClass, classF);
                    }
		        })
		        .getTime();
		LOGGER.info("ResponseParamParserTest#performanceTest with parser(new) time = " + offset);
	}
	
	@Test
	public void performanceTest2() {
		ClassJ classJ = new ClassJ();
		long time = System.currentTimeMillis();
		for(int i = 0 ; i < 1000000 ; i++) {
			ISFSObject params = new SFSObject();
			params.putUtfString("dreams", classJ.getDr());
		}
		long offset = System.currentTimeMillis() - time;
		LOGGER.info("ResponseParamParserTest#performanceTest2 with no parser time = " + offset);
		ResponseHandlerClass clazz = new ResponseHandlerClass(ClassJ.class);
		time = System.currentTimeMillis();
		for(int i = 0 ; i < 1000000 ; i++) {
			@SuppressWarnings("unused")
			ISFSObject params = new ResponseParamSerializer().object2params(clazz, classJ);
		}
		offset = System.currentTimeMillis() - time;
		LOGGER.info("ResponseParamParserTest#performanceTest2 with parser time = " + offset);
	}
	
	@Test
	public void getFieldsWithAnnotationsPerformaceTest() {
		long time = System.currentTimeMillis();
		for(int i = 0 ; i < 1000 ; i++) {
			FieldUtils.getFieldsWithAnnotation(
					 ClassF.class, ResponseParam.class);
		}
		long offset = System.currentTimeMillis() - time;
		LOGGER.info("ResponseParamParserTest#getFieldsWithAnnotationsPerformaceTest time = " + offset);
	}
	
	@Data
	@ClientResponseHandler
	public static class ClassA {
	    @ResponseParam
	    public List<Collection<?>> collections = new ArrayList<>();
	}
	
	@Data
	@ClientResponseHandler
	public static class ClassB {
	    public ClassB() {
	        for(byte i = 0 ; i < 3 ; i++) {
	            Byte array[] = new Byte[] {new Byte((byte) (i * 1)), 
	                    new Byte((byte) (i * 2)), 
	                    new Byte((byte) (i * 3))};
	            lbytes.add(array);
	        }
	    }
	    @ResponseParam("lby")
	    public List<Byte[]> lbytes = new ArrayList<>();
	}
	
	@Data
	@ClientResponseHandler
	public static class ClassC {
	    @ResponseParam
        public boolean[][] a0 = {{true, false}, {false, true}};
	    @ResponseParam
	    public byte[][] a1 = {{1, 2}, {3, 4}};
	    @ResponseParam
        public char[][] a2 = {{1, 2}, {3, 4}};
	    @ResponseParam
        public double[][] a3 = {{1, 2}, {3, 4}};
	    @ResponseParam
        public float[][] a4 = {{1, 2}, {3, 4}};
	    @ResponseParam
        public int[][] a5 = {{1, 2}, {3, 4}};
	    @ResponseParam
        public long[][] a6 = {{1, 2}, {3, 4}};
	    @ResponseParam
        public short[][] a8 = {{1, 2}, {3, 4}};
	    
	    @ResponseParam
        public Boolean[][] a9 = {{true, false}, {false, true}};
	    @ResponseParam
        public Byte[][] a10 = {{1, 2}, {3, 4}};
	    @ResponseParam
        public Character[][] a11 = {{1, 2}, {3, 4}};
	    @ResponseParam
        public Double[][] a12 = {{1D, 2D}, {3D, 4D}};
	    @ResponseParam
        public Float[][] a13 = {{1F, 2F}, {3F, 4F}};
	    @ResponseParam
        public Integer[][] a14 = {{1, 2}, {3, 4}};
	    @ResponseParam
        public Long[][] a15 = {{1L, 2L}, {3L, 4L}};
	    @ResponseParam
        public Short[][] a16 = {{1, 2}, {3, 4}};
	    @ResponseParam
        public String[][] a17 = {{"1", "2"}, {"3", "4"}};
	    @ResponseParam
        public ClassH[][] a18 = {{new ClassH("1"), new ClassH("2")}, {new ClassH("3"), new ClassH("4")}};
	}
	
	@Data
    @ClientResponseHandler
    public static class ClassD {
	    public ClassD() {
	        byte[][] abc = {{1, 2},{2, 3}};
	        abcarrayzz.add(abc);
	    }
        @ResponseParam
        public List<byte[][]> abcarrayzz = new ArrayList<>();
    }
	
	@Data
	@ClientResponseHandler
	public static class ClassE {
	    @ResponseParam
	    public List<String> strs;
	}
	
	@Data
	@ClientResponseHandler
	public static class ClassF {
	    public ClassF() {
	        dreams.add("dung");
	        dreams.add("duong");
	    }
	    
	    @ResponseParam
	    public List<String> dreams = new ArrayList<>();
	}
	
	@Data
	@ClientResponseHandler
	public static class ClassJ {
	    public ClassJ() {
	    }
	    
	    @ResponseParam
	    public String dr = "dung";
	}
	
	@Data
	@AllArgsConstructor
	public static class ClassH {
	    @ResponseParam("a")
	    private String a;
	}
	
	public static void main(String[] args) {
        new ResponseParamParserTest().collectionOfArrayCase();
    }
}

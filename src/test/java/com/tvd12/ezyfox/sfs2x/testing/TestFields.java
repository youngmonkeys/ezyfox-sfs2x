package com.tvd12.ezyfox.sfs2x.testing;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.annotation.ResponseParam;

import lombok.Data;

@Data
public class TestFields {

	@ResponseParam
	int number1;
	
	@ResponseParam
	short number2;
	
	@ResponseParam
	byte number3;
	
	@ResponseParam
	long number4;
	
	@ResponseParam
	double number5;
	
	@ResponseParam
	Integer nubmer6 = new Integer(1);
	
	@ResponseParam
	String string1;
	
	@ResponseParam
	Integer[] ints1 = {12, 3, 4};
	
	private static Field[] getFields(Object obj) {
		return FieldUtils.getFieldsWithAnnotation(
				obj.getClass(), 
				ResponseParam.class);
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Object obj = new TestFields();
		Field[] fields = getFields(obj);
		for(Field field : fields) {
			Class<?> type = field.getType();
			if(type.isArray() && type.getComponentType() == Integer.class) {
				System.out.println(field.getName() + " <<===>> " + field.getType());
			}
		}
		ISFSObject sfsObject = new SFSObject();
		sfsObject.put("int-array", new SFSDataWrapper(SFSDataType.INT_ARRAY, new Integer[] {new Integer(1), new Integer(2)}));
//		int[] value = (int[])sfsObject.get("int-array").getObject();
//		System.out.println(value[1]);
		
		System.out.println(fields[5].getType() == (new TestFields().getNubmer6().getClass()));
	}
	
}

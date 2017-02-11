package com.tvd12.ezyfox.sfs2x.data.impl;

import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.byteArrayToCharArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveBoolArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveDoubleArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveFloatArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveIntArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveLongArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveShortArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToStringArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperBoolArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperDoubleArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperFloatArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperIntArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperLongArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperShortArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.toByteWrapperArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.toCharWrapperArray;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.core.transport.Arraymeters;
import com.tvd12.ezyfox.core.transport.Parameters;

import lombok.AllArgsConstructor;

/**
 * @author tavandung12
 * Created on Feb 10, 2017
 *
 */
@AllArgsConstructor
public class SfsArrayParameters implements Arraymeters {
	
	private ISFSArray array;
	
	private static final Map<Class<?>, ValueFetcher> FETCHERS = 
            defaultFetchers();
	private static final SimpleTransformer TRANSFORMER = 
    		new SimpleTransformer();

	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.RoArraymeters#get(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(int index) {
		return (T) array.get(index).getObject();
	}

	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.RoArraymeters#get(int, java.lang.Class)
	 */
	@Override
	public <T> T get(int index, Class<T> type) {
		if (FETCHERS.containsKey(type))
			return getByType(index, type);
		else
			throw new IllegalArgumentException("has no value with " + type + " at index " + index);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getByType(int index, Class<T> type) {
		return (T) FETCHERS.get(type).get(index, array);
	}

	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.RoArraymeters#size()
	 */
	@Override
	public int size() {
		return array.size();
	}
	
	private void addOne(Object value) {
		array.add(transformData(value));
	}
	
	private SFSDataWrapper transformData(Object value) {
		return TRANSFORMER.transform(value);
	}

	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.Arraymeters#add(java.lang.Object[])
	 */
	@Override
	public void add(Object... values) {
		add(Lists.newArrayList(values));
	}

	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.Arraymeters#add(java.util.Collection)
	 */
	@Override
	public void add(Collection<Object> values) {
		for(Object value : values)
			addOne(value);
	}

	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.Arraymeters#set(int, java.lang.Object)
	 */
	@Override
	public void set(int index, Object value) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.Arraymeters#remove(int)
	 */
	@Override
	public void remove(int index) {
		array.removeElementAt(index);
	}
	
	public static interface ValueFetcher {
        Object get(int index, ISFSArray array);
    }
	
	public static Map<Class<?>, ValueFetcher> defaultFetchers() {
        Map<Class<?>, ValueFetcher> answer = new HashMap<>();
        answer.put(boolean.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getBool(index);
            }
        });
        answer.put(byte.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getByte(index);
            }
        });
        answer.put(char.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return (char)array.getByte(index).byteValue();
            }
        });
        answer.put(double.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getDouble(index);
            }
        });
        answer.put(float.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getFloat(index);
            }
        });
        answer.put(int.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getInt(index);
            }
        });
        answer.put(long.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getLong(index);
            }
        });
        answer.put(short.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getShort(index);
            }
        });
        
        answer.put(Boolean.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getBool(index);
            }
        });
        answer.put(Byte.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getByte(index);
            }
        });
        answer.put(Character.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return (char)array.getByte(index).byteValue();
            }
        });
        answer.put(Double.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getDouble(index);
            }
        });
        answer.put(Float.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getFloat(index);
            }
        });
        answer.put(Integer.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getInt(index);
            }
        });
        answer.put(Long.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getLong(index);
            }
        });
        answer.put(Short.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getShort(index);
            }
        });
        answer.put(String.class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getUtfString(index);
            }
        });
        
        //============
        answer.put(Boolean[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToWrapperBoolArray(array.getBoolArray(index));
            }
        });
        answer.put(Byte[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return toByteWrapperArray(array.getByteArray(index));
            }
        });
        answer.put(Character[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return toCharWrapperArray(array.getByteArray(index));
            }
        });
        answer.put(Double[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToWrapperDoubleArray(array.getDoubleArray(index));
            }
        });
        answer.put(Float[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToWrapperFloatArray(array.getFloatArray(index));
            }
        });
        answer.put(Integer[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToWrapperIntArray(array.getIntArray(index));
            }
        });
        answer.put(Long[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToWrapperLongArray(array.getLongArray(index));
            }
        });
        answer.put(Short[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToWrapperShortArray(array.getShortArray(index));
            }
        });
        answer.put(String[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToStringArray(array.getUtfStringArray(index));
            }
        });
        
      //============
        answer.put(boolean[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToPrimitiveBoolArray(array.getBoolArray(index));
            }
        });
        answer.put(byte[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return array.getByteArray(index);
            }
        });
        answer.put(char[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return byteArrayToCharArray(array.getByteArray(index));
            }
        });
        answer.put(double[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToPrimitiveDoubleArray(array.getDoubleArray(index));
            }
        });
        answer.put(float[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToPrimitiveFloatArray(array.getFloatArray(index));
            }
        });
        answer.put(int[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToPrimitiveIntArray(array.getIntArray(index));
            }
        });
        answer.put(long[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToPrimitiveLongArray(array.getLongArray(index));
            }
        });
        answer.put(short[].class, new ValueFetcher() {
            public Object get(int index, ISFSArray array) {
                return collectionToPrimitiveShortArray(array.getShortArray(index));
            }
        });
        
        answer.put(Parameters.class, new ValueFetcher() {
			public Object get(int index, ISFSArray array) {
				return new SfsParameters(array.getSFSObject(index));
			}
		});
        
        answer.put(Parameters[].class, new ValueFetcher() {
			public Object get(int index, ISFSArray array) {
				ISFSArray data = array.getSFSArray(index);
				Parameters[] answer = new Parameters[data.size()];
				for(int i = 0 ; i < answer.length ; i++)
					answer[i] = new SfsParameters(data.getSFSObject(i)); 
				return answer;
			}
		});
        
        answer.put(Arraymeters.class, new ValueFetcher() {
			public Object get(int index, ISFSArray array) {
				return new SfsArrayParameters(array.getSFSArray(index));
			}
		});
        return answer;
    }

}

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.tvd12.ezyfox.core.transport.Arraymeters;
import com.tvd12.ezyfox.core.transport.Parameters;

import lombok.AllArgsConstructor;

/**
 * @author tavandung12
 * Created on Jan 6, 2017
 *
 */
@AllArgsConstructor
public class SfsParameters implements Parameters {

	private ISFSObject data;
    
    private static final Map<Class<?>, ValueFetcher> FETCHERS = 
            defaultFetchers();
    private static final SimpleTransformer TRANSFORMER = 
    		new SimpleTransformer();
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#size()
     */
    @Override
    public int size() {
        return data.size();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#contain(java.lang.Object)
     */
    @Override
    public boolean contain(Object key) {
        return data.containsKey((String)key);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#clear()
     */
    @Override
    public void clear() {
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#setAll(java.util.Map)
     */
    @Override
    public void setAll(Map<Object, Object> values) {
    	for(Object key : values.keySet())
    		set(key, values.get(key));
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#set(java.lang.Object, java.lang.Object)
     */
    @Override
    public Object set(Object key, Object value) {
        data.put(key.toString(), TRANSFORMER.transform(value));
        return value;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#get(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key) {
        return (T)data.get(key.toString()).getObject();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#get(java.lang.Object, java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> clazz) {
        return (T) doGet((String)key, clazz);
    }
    
    private Object doGet(String key, Class<?> clazz) {
    	if(FETCHERS.containsKey(clazz))
            return FETCHERS.get(clazz).get(key, data);
        throw new IllegalArgumentException("has no value with " + clazz + " and key " + key);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#keys()
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Set<Object> keys() {
        return (Set)data.getKeys();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#values()
     */
    @Override
    public List<Object> values() {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#toMap()
     */
    @Override
    public Map<Object, Object> toMap() {
        throw new UnsupportedOperationException();
    }
    
    public static interface ValueFetcher {
        Object get(String key, ISFSObject data);
    }
    
    public static Map<Class<?>, ValueFetcher> defaultFetchers() {
        Map<Class<?>, ValueFetcher> answer = new HashMap<>();
        answer.put(boolean.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getBool(key);
            }
        });
        answer.put(byte.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getByte(key);
            }
        });
        answer.put(char.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return (char)data.getByte(key).byteValue();
            }
        });
        answer.put(double.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getDouble(key);
            }
        });
        answer.put(float.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getFloat(key);
            }
        });
        answer.put(int.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getInt(key);
            }
        });
        answer.put(long.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getLong(key);
            }
        });
        answer.put(short.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getShort(key);
            }
        });
        
        answer.put(Boolean.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getBool(key);
            }
        });
        answer.put(Byte.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getByte(key);
            }
        });
        answer.put(Character.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return (char)data.getByte(key).byteValue();
            }
        });
        answer.put(Double.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getDouble(key);
            }
        });
        answer.put(Float.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getFloat(key);
            }
        });
        answer.put(Integer.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getInt(key);
            }
        });
        answer.put(Long.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getLong(key);
            }
        });
        answer.put(Short.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getShort(key);
            }
        });
        answer.put(String.class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getUtfString(key);
            }
        });
        
        //============
        answer.put(Boolean[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToWrapperBoolArray(data.getBoolArray(key));
            }
        });
        answer.put(Byte[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return toByteWrapperArray(data.getByteArray(key));
            }
        });
        answer.put(Character[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return toCharWrapperArray(data.getByteArray(key));
            }
        });
        answer.put(Double[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToWrapperDoubleArray(data.getDoubleArray(key));
            }
        });
        answer.put(Float[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToWrapperFloatArray(data.getFloatArray(key));
            }
        });
        answer.put(Integer[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToWrapperIntArray(data.getIntArray(key));
            }
        });
        answer.put(Long[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToWrapperLongArray(data.getLongArray(key));
            }
        });
        answer.put(Short[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToWrapperShortArray(data.getShortArray(key));
            }
        });
        answer.put(String[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToStringArray(data.getUtfStringArray(key));
            }
        });
        
      //============
        answer.put(boolean[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToPrimitiveBoolArray(data.getBoolArray(key));
            }
        });
        answer.put(byte[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return data.getByteArray(key);
            }
        });
        answer.put(char[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return byteArrayToCharArray(data.getByteArray(key));
            }
        });
        answer.put(double[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToPrimitiveDoubleArray(data.getDoubleArray(key));
            }
        });
        answer.put(float[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToPrimitiveFloatArray(data.getFloatArray(key));
            }
        });
        answer.put(int[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToPrimitiveIntArray(data.getIntArray(key));
            }
        });
        answer.put(long[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToPrimitiveLongArray(data.getLongArray(key));
            }
        });
        answer.put(short[].class, new ValueFetcher() {
            public Object get(String key, ISFSObject data) {
                return collectionToPrimitiveShortArray(data.getShortArray(key));
            }
        });
        
        answer.put(Parameters.class, new ValueFetcher() {
			public Object get(String key, ISFSObject data) {
				return new SfsParameters(data.getSFSObject(key));
			}
		});
        
        answer.put(Parameters[].class, new ValueFetcher() {
			public Object get(String key, ISFSObject data) {
				ISFSArray array = data.getSFSArray(key);
				Parameters[] answer = new SfsParameters[array.size()];
		        for(int i = 0 ; i < array.size() ; i++)
		            answer[i] = new SfsParameters(array.getSFSObject(i));
		        return answer;
			}
		});
        
        answer.put(Arraymeters.class, new ValueFetcher() {
			public Object get(String key, ISFSObject data) {
				return new SfsArrayParameters(data.getSFSArray(key));
			}
		});
        
        return answer;
    }

}

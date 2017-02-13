package com.tvd12.ezyfox.sfs2x.data.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.sfs2x.data.impl.SimpleTransformer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SfsObjectMap implements Map<Object, Object> {

	private ISFSObject data;
	
	private static final SimpleTransformer TRANSFORMER =
			new SimpleTransformer();
	
	@Override
	public int size() {
		return data.size();
	}

	@Override
	public boolean isEmpty() {
		return data.size() == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		return data.containsKey(key.toString());
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object get(Object key) {
		return data.get(key.toString()).getObject();
	}

	@Override
	public Object put(Object key, Object value) {
		data.put(key.toString(), TRANSFORMER.transform(value));
		return value;
	}

	@Override
	public Object remove(Object key) {
		return data.removeElement(key.toString());
	}

	@Override
	public void putAll(Map<? extends Object, ? extends Object> m) {
		for(Object key : m.keySet())
			put(key, m.get(key));
	}

	@Override
	public void clear() {
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<Object> keySet() {
		return (Set)data.getKeys();
	}

	@Override
	public Collection<Object> values() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Entry<Object, Object>> entrySet() {
		return entrySet(data.iterator());
	}
	
	private Set<Entry<Object, Object>> entrySet(Iterator<Entry<String, SFSDataWrapper>> iterator) {
		Set<Entry<Object, Object>> result = new HashSet<>();
	    while (iterator.hasNext())
	        result.add(new SfsEntry(iterator.next()));
	    return result;
	}
	
	@AllArgsConstructor
	public static final class SfsEntry implements Entry<Object, Object> {
		private Entry<String, SFSDataWrapper> it;
		
		@Override
		public Object getKey() {
			return it.getKey();
		}

		@Override
		public Object getValue() {
			return it.getValue().getObject();
		}

		@Override
		public Object setValue(Object value) {
			throw new UnsupportedOperationException();
		}
	}

}

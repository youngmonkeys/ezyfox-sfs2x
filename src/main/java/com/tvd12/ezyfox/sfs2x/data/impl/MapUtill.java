package com.tvd12.ezyfox.sfs2x.data.impl;

import java.util.Map;
import java.util.Map.Entry;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on Jul 2, 2016
 *
 */
public class MapUtill {

    private MapUtill() {}
    
    public static ISFSObject map2SFSObject(AppContextImpl context, 
            Map<String, Object> map) {
        SimpleTransformer transformer = new SimpleTransformer(context);
        ISFSObject answer = new SFSObject();
        for(Entry<String, Object> entry : map.entrySet()) {
            answer.put(entry.getKey(), transformer.transform(entry.getValue()));
        }
        return answer;
    }
    
}

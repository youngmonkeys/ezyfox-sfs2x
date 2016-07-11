package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.List;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.FindZone;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see FindZone
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class FindZoneImpl extends BaseCommandImpl implements FindZone {

    /**
     * @param context
     * @param api
     * @param extension
     */
    public FindZoneImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FindZone#current()
     */
    @Override
    public ApiZone current() {
        return (ApiZone) extension.getParentZone().getProperty(APIKey.ZONE);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FindZone#all()
     */
    @Override
    public List<ApiZone> all() {
        List<ApiZone> answer = new ArrayList<>();
        for(Zone zone : SmartFoxServer.getInstance()
                .getZoneManager().getZoneList()) {
            answer.add((ApiZone) zone.getProperty(APIKey.ZONE));
        }
        return answer;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FindZone#find(int)
     */
    @Override
    public ApiZone find(int id) {
        Zone zone = SmartFoxServer.getInstance()
                .getZoneManager()
                .getZoneById(id);
        if(zone != null && zone.containsProperty(APIKey.ZONE)) 
            return (ApiZone) zone.getProperty(APIKey.ZONE);
        return null;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FindZone#find(java.lang.String)
     */
    @Override
    public ApiZone find(String name) {
        Zone zone = SmartFoxServer.getInstance()
                .getZoneManager()
                .getZoneByName(name);
        if(zone != null && zone.containsProperty(APIKey.ZONE)) 
            return (ApiZone) zone.getProperty(APIKey.ZONE);
        return null;
    }

    
    
}

package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.Map;

import com.smartfoxserver.v2.core.ISFSEventParam;

/**
 * @author tavandung12
 * Created on Apr 3, 2017
 *
 */
public interface InternalEventHandler {

	void handleEvent(String event, Map<ISFSEventParam, Object> params);
	
}
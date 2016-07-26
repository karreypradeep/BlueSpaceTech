/**
 * This document is a part of the source code and related artifacts for
 * SMSystem.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.core.utility;


import com.bluespacetech.core.constants.CoreConstants;

public final class ViewUtil {


    public static String getPrincipal() {
	String username = null;
	if((null == username) || (username.trim().length()==0)){
	    username = CoreConstants.ANONYMOUS_USER;
	}
	return username;
    }

}

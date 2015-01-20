package com.genesearch;

import org.json.JSONObject;

/**
 * Created by user on 19.01.2015.
 */
public class Util {

    public  static String safeString(Object object) {
        String result = null;

        if(object == null) {
            return null;
        }

        if(object instanceof String) {
            result = (String) object;
            if(result.trim().equalsIgnoreCase("null")) {
                result = null;
            }
        }
        else if(object.equals(JSONObject.NULL)) {
            return null;
        }
        else {
            result = object.toString();
        }

        return result;
    }
}

package com.example.nikolas.messagernik.entity.response;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by User on 02.10.2015.
 */
public class ResponseListUsers {
    private int code;
    private JSONArray responseList;

    public ResponseListUsers(int code, JSONArray responseList) {
        this.code = code;
        this.responseList = responseList;
    }

    public ResponseListUsers(int code) {
        this.code = code;
    }

    public ResponseListUsers(JSONArray responseList) {
        this.responseList = responseList;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResponseList() {
        return responseList;
    }

    public void setResponseList(JSONArray responseList) {
        this.responseList = responseList;
    }

    public static ResponseListUsers fromJson(final JSONObject objectResponse)
    {
        final Integer code = objectResponse.optInt("code");
        final JSONArray object = objectResponse.optJSONArray("responseList");
        return new ResponseListUsers(code,object);
    }
}

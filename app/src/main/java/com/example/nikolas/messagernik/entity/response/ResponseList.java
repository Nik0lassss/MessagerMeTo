package com.example.nikolas.messagernik.entity.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by User on 02.10.2015.
 */
public class ResponseList {
    private int code;
    private Object responseList;

    public ResponseList(int code, Object responseList) {
        this.code = code;
        this.responseList = responseList;
    }

    public ResponseList(int code) {
        this.code = code;
    }

    public ResponseList(Object responseList) {
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

    public void setResponseList(List<Object> responseList) {
        this.responseList = responseList;
    }

    public static ResponseList fromJson(final JSONObject objectResponse)
    {
        final Integer code = objectResponse.optInt("code");
        final JSONArray object = objectResponse.optJSONArray("responseList");
        return new ResponseList(code,object);
    }
}

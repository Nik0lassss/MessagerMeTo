package com.example.nikolas.messagernik.entity.response;

import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by User on 02.10.2015.
 */
public class ResponseObject {
    private int code;
    private Object responseObject;

    public ResponseObject(int code, Object responseObject) {
        this.code = code;
        this.responseObject = responseObject;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static ResponseObject fromJson(final JSONObject objectResponse) {
        final Integer code = objectResponse.optInt("code");
        final JSONObject object = objectResponse.optJSONObject("responseObject");
        return new ResponseObject(code,object);
    };
}

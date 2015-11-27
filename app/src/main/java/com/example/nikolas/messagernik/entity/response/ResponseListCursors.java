package com.example.nikolas.messagernik.entity.response;




import com.example.nikolas.messagernik.entity.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by User on 02.10.2015.
 */

public class ResponseListCursors implements java.io.Serializable {
    private int code;
    private JSONArray responseList;
    private Cursor cursor;


    public ResponseListCursors() {
    }

    public ResponseListCursors(int code, JSONArray responseList, Cursor cursor) {

        this.code = code;
        this.responseList = responseList;
        this.cursor = cursor;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONArray getResponseList() {
        return responseList;
    }

    public void setResponseList(JSONArray responseList) {
        this.responseList = responseList;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }
    public static ResponseListCursors fromJson(final JSONObject objectResponse)
    {
        Cursor cursor=null;
        final Integer code = objectResponse.optInt("code");
        final JSONArray object = objectResponse.optJSONArray("responseList");
        try {
             cursor = Cursor.fromJson(objectResponse.getJSONObject("cursor"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseListCursors(code,object,cursor);
    }
}

package com.example.nikolas.messagernik.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nikolas on 18.09.2015.
 */
public class User {



    private Integer id;
    private String first_name;
    private String last_name;
    private SecurityUser securityUser;



    public User() {
    }

    public User(Integer id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public User(Integer id, String first_name, String last_name, SecurityUser securityUser) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.securityUser = securityUser;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public SecurityUser getSecurityUser() {
        return securityUser;
    }

    public void setSecurityUser(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }

    public static User fromJson(final JSONObject object)
    {
      final   Integer id =object.optInt("id", 0);
      final   String firstName = object.optString("first_name", "");
      final   String lastName = object.optString("last_name","");
        SecurityUser securityUser = new SecurityUser();
        try {
            JSONObject securityJSONObject = object.getJSONObject("securityUser");
            securityUser.setId(securityJSONObject.optInt("id",0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  new User(id,firstName,lastName,securityUser);
    }

    public static ArrayList<User> fromJson(final JSONArray arrayObject)
    {
    final ArrayList<User> userArrayList = new ArrayList<User>();
        for (int i=0;i<arrayObject.length();i++)
        {
            try {
                final User user = fromJson(arrayObject.getJSONObject(i));
                if(null != user) userArrayList.add(user);
            } catch (JSONException e) {
            }
        }
        return  userArrayList;
    }


}

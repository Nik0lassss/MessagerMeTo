package com.example.nikolas.messagernik.config;

public class Config {
	// File upload url (replace the ip with your server address)
    public static final String SERVER_URL= "http://192.168.174.1:8080/server/";
	public static final String FILE_UPLOAD_URL = SERVER_URL+"rest/upload";

    public static final String GET_USER_URL="rest/user/";
    public static final String LOGIN_URL=SERVER_URL+GET_USER_URL+"login";
	// Directory name to store captured images and videos
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
}

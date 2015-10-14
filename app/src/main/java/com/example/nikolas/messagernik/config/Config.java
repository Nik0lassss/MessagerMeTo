package com.example.nikolas.messagernik.config;

public class Config {
	// File upload url (replace the ip with your server address)
    public static final String SERVER_URL= "http://messagserver-chirkevich.rhcloud.com/server/";
	public static final String FILE_UPLOAD_URL = SERVER_URL+"rest/uploadPhoto";

    public static final String GET_USER_URL="rest/user/";
    public static final String LOGIN_URL=SERVER_URL+GET_USER_URL+"login";
    public static final String IMAGE_PATH="rest/image/";
    public static final String GET_IMAGE_URL=SERVER_URL+IMAGE_PATH;
    public static final String CREATE_ACCOUNT =SERVER_URL+"rest/account/create";
    public static final String GET_ALL_USERS=SERVER_URL+GET_USER_URL+"all";
    public static final String GET_RECIEVED_MESSAGE=SERVER_URL+"rest/user/message/received/";
	// Directory name to store captured images and videos
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
}

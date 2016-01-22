package com.example.nikolas.messagernik.config;

public class Config {
	// File upload url (replace the ip with your server address)
    public static final String SERVER_URL= "http://messagserver-chirkevich.rhcloud.com/server/";
	public static final String FILE_UPLOAD_URL = SERVER_URL+"rest/uploadPhoto";

    public static final String GET_USER_URL="rest/user/";
    public static final String GET_CONVERSATION_URL="rest/conversation/";
    public static final String LOGIN_URL=SERVER_URL+GET_USER_URL+"login";
    public static final String USER_GET_URL=SERVER_URL+GET_USER_URL+"get";
    public static final String USER_GET_NEW_MESSAGE=SERVER_URL+GET_USER_URL+"message/getNew";
    public static final String CONVERSATION_GET_URL=SERVER_URL+GET_CONVERSATION_URL+"get";
    public static final String IMAGE_PATH="rest/image/";
    public static final String PUT_MESSAGE_URL=SERVER_URL+GET_USER_URL+"message";
    public static final String GET_IMAGE_URL=SERVER_URL+IMAGE_PATH;
    public static final String CREATE_ACCOUNT =SERVER_URL+"rest/account/create";
    public static final String GET_ALL_USERS=SERVER_URL+GET_USER_URL+"all/";
    public static final String VALIDATE_SECRET_TOCKEN=SERVER_URL+GET_USER_URL+"validateTocken";
    public static final String GET_RECIEVED_MESSAGE=SERVER_URL+"rest/user/message/received/";
    public static final String POST_NOTIFY_NEW_MESSAGE ="http://messagserver-chirkevich.rhcloud.com/server/name/get/";
    public static final String POST_GET_FRIENDS_LIST=SERVER_URL+"rest/friends/getFriendsList";
    public static final String POST_SUBMIT_ADD_TO_FRIENDS=SERVER_URL+"rest/user/submitAddToFriends";
    public static final String POST_PUT_REQUEST_TO_FRIEND=SERVER_URL+"rest/user/putFriendsRequest";
    public static final String GET_FRIENDS_REQUEST_TO_ME=SERVER_URL+"rest/user/getFriendsRequestToMe/";
	// Directory name to store captured images and videos
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
}

package com.hehe.chat;

import com.alibaba.fastjson.JSON;

/**
 * WebSocket 聊天消息类
 */
public class Message {

    public static final String ENTER = "ENTER";
    public static final String SPEAK = "SPEAK";
    public static final String QUIT = "QUIT";

    private String type;//消息类型

    private String username; //发送人

    private String msg; //发送消息

    private int onlineCount; //在线用户数

    private Object onlineUsers;

    public static String jsonStr(String type, String username, String msg, int onlineTotal,Object onlineUsers) {
        return JSON.toJSONString(new Message(type, username, msg, onlineTotal ,onlineUsers));
    }

    public Message(String type, String username, String msg, int onlineCount, Object onlineUsers) {
        this.type = type;
        this.username = username;
        this.msg = msg;
        this.onlineCount = onlineCount;
        this.onlineUsers = onlineUsers;
    }

    public static String getENTER() {
        return ENTER;
    }

    public static String getSPEAK() {
        return SPEAK;
    }

    public static String getQUIT() {
        return QUIT;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public Object getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(Object onlineUsers) {
        this.onlineUsers = onlineUsers;
    }
}

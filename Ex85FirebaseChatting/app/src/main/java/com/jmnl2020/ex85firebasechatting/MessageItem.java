package com.jmnl2020.ex85firebasechatting;

public class MessageItem {

    String name;
    String message;
    String time;
    String profileUrl;

    //닉네임 / 메세지 / 작성시간 / 프로필사진 Url(http:// ~~~~)


    public MessageItem() {
    }

    public MessageItem(String name, String message, String time, String profileUrl) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.profileUrl = profileUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}

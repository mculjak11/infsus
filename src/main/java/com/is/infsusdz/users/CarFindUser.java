package com.is.infsusdz.users;

import java.util.List;
import java.util.Map;

public class CarFindUser {
    private String id;
    private String username;
    private String password;
    private String profilePic;
    private String email;
    private Map<String, Object> userInfo;
    private Map<String,List<Object>> ads;

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Object> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, Object> userInfo) {
        this.userInfo = userInfo;
    }

    public Map<String,List<Object>> getAds() {
        return ads;
    }

    public void setAds(Map<String,List<Object>> ads) {
        this.ads = ads;
    }

    @Override
    public String toString() {
        return "CarFindUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", email='" + email + '\'' +
                ", userInfo=" + userInfo +
                ", ads=" + ads +
                '}';
    }
}

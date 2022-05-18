package com.is.infsusdz.users;

import java.util.List;
import java.util.Map;

public class CarFindUser {
    private String userName;
    private String profilePic;
    private String email;
    private Map<String, Object> userInfo;
    private Map<List<String>, Object> ads;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Map<List<String>, Object> getAds() {
        return ads;
    }

    public void setAds(Map<List<String>, Object> ads) {
        this.ads = ads;
    }

    @Override
    public String toString() {
        return "CarFindUser{" +
                "userName='" + userName + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", email='" + email + '\'' +
                ", userInfo=" + userInfo +
                ", ads=" + ads +
                '}';
    }
}

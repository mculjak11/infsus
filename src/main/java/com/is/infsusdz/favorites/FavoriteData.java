package com.is.infsusdz.favorites;

public class FavoriteData {
    private String username;
    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FavoriteData{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

package com.is.infsusdz.favorites;

public class FavoriteGet {
    private String username;

    @Override
    public String toString() {
        return "FavoriteGet{" +
                "username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

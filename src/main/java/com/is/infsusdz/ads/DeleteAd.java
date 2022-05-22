package com.is.infsusdz.ads;

public class DeleteAd {
    private String id;

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DeleteAd{" +
                "id='" + id + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }
}

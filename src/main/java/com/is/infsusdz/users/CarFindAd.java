package com.is.infsusdz.users;

import java.util.List;
import java.util.Map;

public class CarFindAd {
    private String id;
    private String owner;
    private String adType;
    private String title;
    private Map<String, Object> basicInfo;
    private List<Map<String, Object>> pictures;
    private Map<String, Object> buyInfo;
    private Map<String, Object> motorInfo;
    private String hit;
    private String ownerNo;
    private String description;
    private Map<String, Object> place;
    private String chassisNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Object> getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(Map<String, Object> basicInfo) {
        this.basicInfo = basicInfo;
    }

    public List<Map<String, Object>> getPictures() {
        return pictures;
    }

    public void setPictures(List<Map<String, Object>> pictures) {
        this.pictures = pictures;
    }

    public Map<String, Object> getBuyInfo() {
        return buyInfo;
    }

    public void setBuyInfo(Map<String, Object> buyInfo) {
        this.buyInfo = buyInfo;
    }

    public Map<String, Object> getMotorInfo() {
        return motorInfo;
    }

    public void setMotorInfo(Map<String, Object> motorInfo) {
        this.motorInfo = motorInfo;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getOwnerNo() {
        return ownerNo;
    }

    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getPlace() {
        return place;
    }

    public void setPlace(Map<String, Object> place) {
        this.place = place;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    @Override
    public String toString() {
        return "CarFindAd{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", adType='" + adType + '\'' +
                ", title='" + title + '\'' +
                ", basicInfo=" + basicInfo +
                ", pictures=" + pictures +
                ", buyInfo=" + buyInfo +
                ", motorInfo=" + motorInfo +
                ", hit='" + hit + '\'' +
                ", ownerNo='" + ownerNo + '\'' +
                ", description='" + description + '\'' +
                ", place=" + place +
                ", chassisNo='" + chassisNo + '\'' +
                '}';
    }
}

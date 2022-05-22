package com.is.infsusdz.filter;

import java.util.List;
import java.util.Map;

public class FilteredAd {
    private String adType;
    private String make;
    private String model;
    private String category;
    private Integer price1 = 0;
    private Integer price2 = 10000000;
    private Integer year1 = 0;
    private Integer year2 = 2123;
    private Integer kw1 = 0;
    private Integer km1 = 0;
    private Integer cm31 = -1;
    private Integer kw2 = 5000;
    private Integer km2 = 10000000;
    private Integer cm32 = 10000000;
    private String fuel;
    private String shifter;
    private String city;

    @Override
    public String toString() {
        return "FilteredAd{" +
                "adType='" + adType + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                ", price1=" + price1 +
                ", price2=" + price2 +
                ", year1=" + year1 +
                ", year2=" + year2 +
                ", kw1=" + kw1 +
                ", km1=" + km1 +
                ", cm31=" + cm31 +
                ", kw2=" + kw2 +
                ", km2=" + km2 +
                ", cm32=" + cm32 +
                ", fuel='" + fuel + '\'' +
                ", shifter='" + shifter + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice1() {
        return price1;
    }

    public void setPrice1(Integer price1) {
        this.price1 = price1 - 1;
    }

    public Integer getPrice2() {
        return price2;
    }

    public void setPrice2(Integer price2) {
        this.price2 = price2 + 1;
    }

    public Integer getYear1() {
        return year1;
    }

    public void setYear1(Integer year1) {
        this.year1 = year1 - 1;
    }

    public Integer getYear2() {
        return year2;
    }

    public void setYear2(Integer year2) {
        this.year2 = year2 + 1;
    }

    public Integer getKw1() {
        return kw1;
    }

    public void setKw1(Integer kw1) {
        this.kw1 = kw1 - 1;
    }

    public Integer getKm1() {
        return km1;
    }

    public void setKm1(Integer km1) {
        this.km1 = km1 - 1;
    }

    public Integer getCm31() {
        return cm31;
    }

    public void setCm31(Integer cm31) {
        this.cm31 = cm31 - 1;
    }

    public Integer getKw2() {
        return kw2;
    }

    public void setKw2(Integer kw2) {
        this.kw2 = kw2 + 1;
    }

    public Integer getKm2() {
        return km2;
    }

    public void setKm2(Integer km2) {
        this.km2 = km2 + 1;
    }

    public Integer getCm32() {
        return cm32;
    }

    public void setCm32(Integer cm32) {
        this.cm32 = cm32 + 1;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getShifter() {
        return shifter;
    }

    public void setShifter(String shifter) {
        this.shifter = shifter;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

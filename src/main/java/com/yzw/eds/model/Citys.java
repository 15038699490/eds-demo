package com.yzw.eds.model;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "citys")
public class Citys implements Serializable {

    private  Integer id;
    private  String Name;
    private  Integer ParentId;
    private  String ShortName;
    private  String LevelType;
    private  String CityCode;
    private  String ZipCode;
    private  String MergerName;
    private  String lng;
    private  String Lat;
    private  String Pinyin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getParentId() {
        return ParentId;
    }

    public void setParentId(Integer parentId) {
        ParentId = parentId;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getLevelType() {
        return LevelType;
    }

    public void setLevelType(String levelType) {
        LevelType = levelType;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getMergerName() {
        return MergerName;
    }

    public void setMergerName(String mergerName) {
        MergerName = mergerName;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getPinyin() {
        return Pinyin;
    }

    public void setPinyin(String pinyin) {
        Pinyin = pinyin;
    }
}

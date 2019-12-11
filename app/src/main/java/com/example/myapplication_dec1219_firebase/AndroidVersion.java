package com.example.myapplication_dec1219_firebase;

public class AndroidVersion {
    String id, cName, rDate, apiLevel;

    public AndroidVersion(String id, String cName, String rDate, String apiLevel) {
        this.id = id;
        this.cName = cName;
        this.rDate = rDate;
        this.apiLevel = apiLevel;
    }

    public String getId() {
        return id;
    }

    public String getcName() {
        return cName;
    }

    public String getrDate() {
        return rDate;
    }

    public String getApiLevel() {
        return apiLevel;
    }
}

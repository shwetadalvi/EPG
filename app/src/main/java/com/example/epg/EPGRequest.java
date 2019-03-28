package com.example.epg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EPGRequest {
    @SerializedName("Registration")
    @Expose
    private Registration registration;

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }


}

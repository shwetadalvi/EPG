package com.example.epg;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
@Headers({"Content-Type:application/xml","Accept:text/xml-standard-api"})

    @POST(":2443")
    Call<EPGResponse> registerUser(@Body EPGRequest body);

}

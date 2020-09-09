package com.cavista.leaveragesapp.network;

import com.cavista.leaveragesapp.models.LeaveragesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface RetrofitInterface {

    @Headers({"Authorization:Client-ID 137cda6b5008a7c"})
    @GET("search/1?q=vanilla")
    Call<LeaveragesResponse> getLeaverage();

    @Headers({"Authorization:Client-ID 137cda6b5008a7c"})
    @GET
    Call<LeaveragesResponse> getLeaverage(@Url String url);
}

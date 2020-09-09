package com.cavista.leaveragesapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cavista.leaveragesapp.network.AppConstants.BASE_URL;

public class RetroService {

    private static Retrofit retrofit = null;

    public static RetrofitInterface getRetrofitServer() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RetrofitInterface.class);

    }

}

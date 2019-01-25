package com.example.rotelukh.flickrretrofitpicassodatabindingbutterknife;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceAPI {

    private static final String BASE_URL = "https://api.flickr.com";
    private static final String API_KEY = "bda130d457c1267ca7b742d5921db69d";


    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            HttpUrl httpUrl = original.url();

                            HttpUrl newHttpUrl = httpUrl.newBuilder()
                                    .addQueryParameter("api_key", API_KEY)
                                    .addQueryParameter("extras", "url_s")
                                    .build();

                            Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);
                            Request request = requestBuilder.build();

                            return chain.proceed(request);
                        }
                    })
                    .build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit;
        }
        return null;
    }

}

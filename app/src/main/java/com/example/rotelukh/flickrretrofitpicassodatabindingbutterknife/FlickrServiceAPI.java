package com.example.rotelukh.flickrretrofitpicassodatabindingbutterknife;

import com.example.rotelukh.flickrretrofitpicassodatabindingbutterknife.ModelRecentPhotos.Main;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrServiceAPI {


    /*https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=bda130d457c1267ca7b742d5921db69d&per_page=10&format=json*/
    @GET("/services/rest/?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    Call<Main> getRecentPhoto();



}

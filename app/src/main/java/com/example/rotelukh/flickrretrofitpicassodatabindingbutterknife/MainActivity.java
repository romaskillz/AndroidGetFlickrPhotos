package com.example.rotelukh.flickrretrofitpicassodatabindingbutterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rotelukh.flickrretrofitpicassodatabindingbutterknife.ModelRecentPhotos.Main;
import com.example.rotelukh.flickrretrofitpicassodatabindingbutterknife.ModelRecentPhotos.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.dGetPhoto) Button bGetPhoto;
    @BindView(R.id.rvList) RecyclerView rvList;
    private List<Photo> mPhotos;
    AdapterPhoto adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        mPhotos = new ArrayList<>();

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        rvList.setLayoutManager(layoutManager);

//        AdapterPhoto adapter = new AdapterPhoto(mPhotos);
//        rvList.setAdapter(adapter);
        System.out.println();
    }

    @OnClick({R.id.dGetPhoto})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dGetPhoto:
                getPhotoRequest();
                break;
        }

    }

    private void getPhotoRequest() {
        FlickrServiceAPI flickrServiceAPI = ServiceAPI.getRetrofit().create(FlickrServiceAPI.class);

        final Call<Main> call = flickrServiceAPI.getRecentPhoto();
        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {
                if (response.isSuccessful()) {
                    Main main = response.body();
                    mPhotos = main.getPhotos().getPhoto();
                    int s = main.getPhotos().getPhoto().size();
                    adapter = new AdapterPhoto(mPhotos);
                    rvList.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, "GOOD", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {
                Toast.makeText(MainActivity.this, "BAD", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.rotelukh.flickrretrofitpicassodatabindingbutterknife;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rotelukh.flickrretrofitpicassodatabindingbutterknife.ModelRecentPhotos.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterPhoto extends RecyclerView.Adapter<AdapterPhoto.PhotoViewHolder> {

    private List<Photo> mPhotos;

    public AdapterPhoto(List<Photo> photos) {
        setPhotos(photos);
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_photo_item, parent,
                false);
        return new PhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {

        Picasso.get().load(mPhotos.get(position).getUrlS()).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return  mPhotos.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        private final ImageView photo;

        public PhotoViewHolder(View view) {
            super(view);
            photo = (ImageView) view.findViewById(R.id.photo_grid_item_image);
        }
    }

    public void setPhotos(List<Photo> photos) {
        if (photos == null) {
            this.mPhotos = new ArrayList<Photo>(0);
        } else {
            this.mPhotos = photos;
        }

        notifyDataSetChanged();
    }
}

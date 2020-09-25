package com.firstems.erp.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.firstems.erp.R;
import com.firstems.erp.callback.ImageClickCallback;
import com.firstems.erp.model.ImageModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.io.File;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/16/2020.
 */
public class ImageIncludeAdapter extends RecyclerView.Adapter<ImageIncludeAdapter.ViewHolder> {
    private List<ImageModel> imageModels;
    private ImageClickCallback imageClickCallback;
    private boolean isEditable = true;
    
    public ImageIncludeAdapter(List<ImageModel> imageModels, ImageClickCallback imageClickCallback) {
        this.imageModels = imageModels;
        this.imageClickCallback = imageClickCallback;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_image_include,parent,false);
        return new ViewHolder(view);
    }
    
    public void setEditable(boolean editable) {
        isEditable = editable;
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageModel imageModel = imageModels.get(position);
        if (imageModel.getType() == 1){
            String url = imageModel.getImgUrl();
            if (!url.startsWith(SharedPreferencesManager.getInstance().getDomain())){
                url = SharedPreferencesManager.getInstance().getDomain()+url;
            }
            GlideUrl urlGlide = new GlideUrl(url, new LazyHeaders.Builder()
                    .addHeader("TOKEN", SharedPreferencesManager.getInstance().getPrefToken())
                    .build());
            Glide.with(holder.itemView.getContext()).load(urlGlide).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.img);
        }
        if (imageModel.getType() == 2){
            File file = new File(imageModel.getImgPath());
            Uri imageUri = Uri.fromFile(file);
            Glide.with(holder.itemView.getContext())
                    .load(imageUri)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.img);
        }
        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageClickCallback.onRemoveClick(position);
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageClickCallback.onClickImage(position);
            }
        });
        if (isEditable){
            holder.imgRemove.setVisibility(View.VISIBLE);
        }
        else {
            holder.imgRemove.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
      return imageModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img, imgRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.image);
            imgRemove=itemView.findViewById(R.id.imgRemove);
        }
    }
}

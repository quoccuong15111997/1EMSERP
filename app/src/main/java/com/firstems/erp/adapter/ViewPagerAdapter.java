package com.firstems.erp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.firstems.erp.R;
import com.firstems.erp.model.ImageModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<ImageModel> slideModels;
    private Context context;
    private LayoutInflater inflater;
    
    public ViewPagerAdapter(List<ImageModel> slideModels, Context context) {
        this.slideModels = slideModels;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        if (slideModels != null && slideModels.size() != 0) {
            return slideModels.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=inflater.inflate(R.layout.item_view_pager_adapter,container,false);
        ImageView img = view.findViewById(R.id.img_view_pager_product);
        System.out.println(slideModels.get(position));
        if (slideModels.get(position).getType() == 1){
            String url = slideModels.get(position).getImgUrl();
            if (!url.startsWith(SharedPreferencesManager.getInstance().getDomain())){
                url = SharedPreferencesManager.getInstance().getDomain()+url;
            }
            GlideUrl urlGlide = new GlideUrl(url, new LazyHeaders.Builder()
                    .addHeader("TOKEN", SharedPreferencesManager.getInstance().getPrefToken())
                    .build());
            Glide.with(container.getContext()).load(urlGlide).diskCacheStrategy(DiskCacheStrategy.NONE).into(img);
        }
        else if (slideModels.get(position).getType() == 2){
            Glide.with(container.getContext()).load(slideModels.get(position).getImgPath()).diskCacheStrategy(DiskCacheStrategy.NONE).into(img);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}

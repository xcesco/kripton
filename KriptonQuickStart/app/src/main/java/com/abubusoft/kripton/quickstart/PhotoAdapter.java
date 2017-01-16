package com.abubusoft.kripton.quickstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.quickstart.model.Photo;
import com.abubusoft.kripton.quickstart.model.Post;
import com.abubusoft.quickstart.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by xcesco on 06/01/2017.
 */

public class PhotoAdapter extends AbstractRecyclerViewAdapter<Photo, PhotoAdapter.ViewHolder> {

    static class ViewHolder extends AbstractRecyclerViewAdapter.ViewHolder {
        TextView tvPhotoTitle;
        ImageView ivPhoto;

        ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public ViewHolder createViewHolder(View v) {
        ViewHolder holder=new ViewHolder(v);
        holder.tvPhotoTitle = (TextView) v.findViewById(R.id.tvPhotoTitle);
        holder.ivPhoto= (ImageView) v.findViewById(R.id.ivPhoto);
        return holder;
    }

    @Override
    public int getViewLayoutResourceId() {
        return R.layout.layout_photo;
    }

    @Override
    public void onBindItem(ViewHolder holder, final Photo item) {
        holder.tvPhotoTitle.setText(item.title);

        Glide.with(KriptonLibrary.context()).load(item.url.toString())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivPhoto);

        holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CommentActivity.class);
                Bundle b = new Bundle();
                b.putLong("photoId", item.id);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });
    }

}

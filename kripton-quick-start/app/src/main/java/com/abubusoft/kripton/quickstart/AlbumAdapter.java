package com.abubusoft.kripton.quickstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.quickstart.model.Album;
import com.abubusoft.quickstart.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by xcesco on 06/01/2017.
 */

public class AlbumAdapter extends AbstractRecyclerViewAdapter<Album, AlbumAdapter.ViewHolder> {

    static final String baseUrl ="http://lorempixel.com/84/84/nature/";

    static class ViewHolder extends AbstractRecyclerViewAdapter.ViewHolder {
        TextView tvAlbumTitle;
        ImageView ivAlbumImageView;
        ImageButton btnAlbumAction;

        ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public ViewHolder createViewHolder(View v) {
        ViewHolder holder=new ViewHolder(v);
        holder.tvAlbumTitle = (TextView) v.findViewById(R.id.tvAlbumTitle);
        holder.btnAlbumAction= (ImageButton) v.findViewById(R.id.btnAlbumActionPhoto);
        holder.ivAlbumImageView= (ImageView) v.findViewById(R.id.ivAlbum);
        return holder;
    }

    @Override
    public int getViewLayoutResourceId() {
        return R.layout.layout_album;
    }

    @Override
    public void onBindItem(ViewHolder holder, final Album item) {
        holder.tvAlbumTitle.setText(item.title);

        Glide.with(KriptonLibrary.context()).load(baseUrl +item.id)
                .placeholder(R.drawable.ic_collections_blue_grey_400_48dp)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivAlbumImageView);

        holder.btnAlbumAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PhotoActivity.class);
                Bundle b = new Bundle();
                b.putLong("albumId", item.id);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });
    }

}

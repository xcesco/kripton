package com.abubusoft.kripton.quickstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abubusoft.kripton.quickstart.model.Album;
import com.abubusoft.quickstart.R;

/**
 * Created by xcesco on 06/01/2017.
 */

public class AlbumAdapter extends AbstractRecyclerViewAdapter<Album, AlbumAdapter.ViewHolder> {

    static class ViewHolder extends AbstractRecyclerViewAdapter.ViewHolder {
        TextView tvAlbumTitle;
        ImageView ivAlbumAction;

        ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public ViewHolder createViewHolder(View v) {
        ViewHolder holder=new ViewHolder(v);
        holder.tvAlbumTitle = (TextView) v.findViewById(R.id.tvAlbumTitle);
        holder.ivAlbumAction= (ImageView) v.findViewById(R.id.ivAlbumActionPhoto);
        return holder;
    }

    @Override
    public int getViewLayoutResourceId() {
        return R.layout.layout_album;
    }

    @Override
    public void onBindItem(ViewHolder holder, final Album item) {
        holder.tvAlbumTitle.setText(item.title);

        holder.ivAlbumAction.setOnClickListener(new View.OnClickListener() {
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

package com.abubusoft.kripton.quickstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.quickstart.R;
import com.abubusoft.kripton.quickstart.model.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by xcesco on 06/01/2017.
 */

public class UserAdapter extends AbstractRecyclerViewAdapter<User, UserAdapter.ViewHolder> {

    static final String baseUrl ="http://lorempixel.com/84/84/people/";

    static class ViewHolder extends AbstractRecyclerViewAdapter.ViewHolder {
       // public TextView tvId;
        // each data item is just a string in this case
        TextView tvUsername;
        TextView tvName;
        TextView tvEmail;
        TextView tvPhone;
        TextView tvAddress;
        TextView tvCompany;
        TextView tvWebsite;

        ImageView ivUser;

        ImageButton btnUserActionAlbum;
        ImageButton btnUserActionTodo;
        ImageButton btnUserActionPost;

        ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public ViewHolder createViewHolder(View v) {
        ViewHolder holder=new ViewHolder(v);
        holder.tvUsername= (TextView) v.findViewById(R.id.tvUsername);
        holder.tvName= (TextView) v.findViewById(R.id.tvName);
        holder.tvAddress=(TextView)v.findViewById(R.id.tvAddress);
        holder.tvEmail=(TextView)v.findViewById(R.id.tvEmail);
        holder.tvPhone=(TextView)v.findViewById(R.id.tvPhone);
        holder.tvCompany=(TextView)v.findViewById(R.id.tvCompany);
        holder.tvWebsite=(TextView)v.findViewById(R.id.tvWebsite);

        holder.btnUserActionAlbum=(ImageButton) v.findViewById(R.id.btnUserActionAlbum);
        holder.btnUserActionTodo=(ImageButton) v.findViewById(R.id.btnUserActionTodo);
        holder.btnUserActionPost=(ImageButton) v.findViewById(R.id.btnUserActionPost);

        holder.ivUser=(ImageView) v.findViewById(R.id.ivUser);

        return holder;
    }

    @Override
    public int getViewLayoutResourceId() {
        return R.layout.layout_user;
    }

    @Override
    public void onBindItem(ViewHolder holder, final User item) {
        holder.tvUsername.setText(item.username);
        holder.tvEmail.setText(item.email);
        holder.tvName.setText(item.name);
        holder.tvPhone.setText(item.phone);
        holder.tvWebsite.setText(item.website);
        holder.tvCompany.setText(item.company!=null?item.company.toString():"");
        holder.tvAddress.setText(item.address!=null?item.address.toString():"");

        Glide.with(KriptonLibrary.context()).load(baseUrl +item.id)
                .placeholder(R.drawable.ic_account_circle_blue_grey_400_48dp)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivUser);

        holder.btnUserActionAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AlbumActivity.class);
                Bundle b = new Bundle();
                b.putLong("userId", item.id);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });

        holder.btnUserActionTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TodoActivity.class);
                Bundle b = new Bundle();
                b.putLong("userId", item.id);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });

        holder.btnUserActionPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PostActivity.class);
                Bundle b = new Bundle();
                b.putLong("userId", item.id);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });

    }

}

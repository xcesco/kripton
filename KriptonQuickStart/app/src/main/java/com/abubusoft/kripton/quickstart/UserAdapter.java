package com.abubusoft.kripton.quickstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abubusoft.quickstart.R;
import com.abubusoft.kripton.quickstart.model.User;

/**
 * Created by xcesco on 06/01/2017.
 */

public class UserAdapter extends AbstractRecyclerViewAdapter<User, UserAdapter.ViewHolder> {

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

        ImageView ivUserActionAlbum;
        ImageView ivUserActionTodo;
        ImageView ivUserActionPost;

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

        holder.ivUserActionAlbum=(ImageView) v.findViewById(R.id.ivUserActionAlbum);
        holder.ivUserActionTodo=(ImageView) v.findViewById(R.id.ivUserActionTodo);
        holder.ivUserActionPost=(ImageView) v.findViewById(R.id.ivUserActionPost);
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
        holder.tvCompany.setText(item.company.toString());
        holder.tvAddress.setText(item.address.toString());

        holder.ivUserActionAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AlbumActivity.class);
                Bundle b = new Bundle();
                b.putLong("userId", item.id);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });

        holder.ivUserActionTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TodoActivity.class);
                Bundle b = new Bundle();
                b.putLong("userId", item.id);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });

        holder.ivUserActionPost.setOnClickListener(new View.OnClickListener() {
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

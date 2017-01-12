package com.abubusoft.kriptonquickstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.abubusoft.kriptonquickstart.model.Post;
import com.abubusoft.kriptonquickstart.model.User;

/**
 * Created by xcesco on 06/01/2017.
 */

public class PostAdapter extends AbstractRecyclerViewAdapter<Post, PostAdapter.ViewHolder> {

    static class ViewHolder extends AbstractRecyclerViewAdapter.ViewHolder {
       // public TextView tvId;
        // each data item is just a string in this case
        TextView tvTitle;
        TextView tvBody;

        ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public ViewHolder createViewHolder(View v) {
        ViewHolder holder=new ViewHolder(v);
        holder.tvTitle= (TextView) v.findViewById(R.id.tvPostTitle);
        holder.tvBody= (TextView) v.findViewById(R.id.tvPostBody);
        return holder;
    }

    @Override
    public int getViewLayoutResourceId() {
        return R.layout.layout_post;
    }

    @Override
    public void onBindItem(ViewHolder holder, final Post item) {
       // holder.tvId.setText(Long.toString(item.id));
        holder.tvTitle.setText(item.title);
        holder.tvBody.setText(item.body);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Selected item "+item.title, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(v.getContext(), PostActivity.class);
                Bundle b = new Bundle();
                b.putLong("postId", item.id);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });
    }

}

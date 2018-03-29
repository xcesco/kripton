package com.abubusoft.kripton.quickstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abubusoft.quickstart.R;
import com.abubusoft.kripton.quickstart.model.Post;

/**
 * Created by xcesco on 06/01/2017.
 */

public class PostAdapter extends AbstractRecyclerViewAdapter<Post, PostAdapter.ViewHolder> {

    static class ViewHolder extends AbstractRecyclerViewAdapter.ViewHolder {
        TextView tvPostTitle;
        TextView tvPostBody;
        ImageButton btnPostActionComment;

        ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public ViewHolder createViewHolder(View v) {
        ViewHolder holder=new ViewHolder(v);
        holder.tvPostTitle = (TextView) v.findViewById(R.id.tvPostTitle);
        holder.tvPostBody = (TextView) v.findViewById(R.id.tvPostBody);
        holder.btnPostActionComment= (ImageButton) v.findViewById(R.id.btnPostActionComment);
        return holder;
    }

    @Override
    public int getViewLayoutResourceId() {
        return R.layout.layout_post;
    }

    @Override
    public void onBindItem(ViewHolder holder, final Post item) {
        holder.tvPostTitle.setText(item.title);
        holder.tvPostBody.setText(item.body);

        holder.btnPostActionComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Selected item "+item.title, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(v.getContext(), CommentActivity.class);
                Bundle b = new Bundle();
                b.putLong("postId", item.id);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });
    }

}

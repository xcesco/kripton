package com.abubusoft.kripton.quickstart;

import android.view.View;
import android.widget.TextView;

import com.abubusoft.kripton.quickstart.model.Comment;
import com.abubusoft.quickstart.R;

/**
 * Created by xcesco on 06/01/2017.
 */

public class CommentAdapter extends AbstractRecyclerViewAdapter<Comment, CommentAdapter.ViewHolder> {

    static class ViewHolder extends AbstractRecyclerViewAdapter.ViewHolder {
        TextView tvPostTitle;
        TextView tvPostBody;
        TextView tvPostEmail;

        ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public ViewHolder createViewHolder(View v) {
        ViewHolder holder=new ViewHolder(v);
        holder.tvPostTitle= (TextView) v.findViewById(R.id.tvCommentName);
        holder.tvPostBody= (TextView) v.findViewById(R.id.tvCommentBody);
        holder.tvPostEmail= (TextView) v.findViewById(R.id.tvCommentEmail);
        return holder;
    }

    @Override
    public int getViewLayoutResourceId() {
        return R.layout.layout_post;
    }

    @Override
    public void onBindItem(ViewHolder holder, final Comment item) {
        holder.tvPostTitle.setText(item.name);
        holder.tvPostBody.setText(item.body);
        holder.tvPostEmail.setText(item.email);

    }

}

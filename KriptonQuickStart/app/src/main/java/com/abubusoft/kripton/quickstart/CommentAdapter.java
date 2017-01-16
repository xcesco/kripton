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
        TextView tvCommentTitle;
        TextView tvCommentBody;
        TextView tvCommentEmail;

        ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public ViewHolder createViewHolder(View v) {
        ViewHolder holder=new ViewHolder(v);
        holder.tvCommentTitle = (TextView) v.findViewById(R.id.tvCommentName);
        holder.tvCommentBody = (TextView) v.findViewById(R.id.tvCommentBody);
        holder.tvCommentEmail = (TextView) v.findViewById(R.id.tvCommentEmail);
        return holder;
    }

    @Override
    public int getViewLayoutResourceId() {
        return R.layout.layout_comment;
    }

    @Override
    public void onBindItem(ViewHolder holder, final Comment item) {
        holder.tvCommentTitle.setText(item.name);
        holder.tvCommentBody.setText(item.body);
        holder.tvCommentEmail.setText(item.email);

    }

}

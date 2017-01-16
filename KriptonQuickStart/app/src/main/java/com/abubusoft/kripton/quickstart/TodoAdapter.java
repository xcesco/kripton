package com.abubusoft.kripton.quickstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abubusoft.quickstart.R;
import com.abubusoft.kripton.quickstart.model.Todo;

/**
 * Created by xcesco on 06/01/2017.
 */

public class TodoAdapter extends AbstractRecyclerViewAdapter<Todo, TodoAdapter.ViewHolder> {

    static class ViewHolder extends AbstractRecyclerViewAdapter.ViewHolder {
       // public TextView tvId;
        // each data item is just a string in this case
        TextView tvTitle;
        TextView tvBody;
        ImageView ivTodo;

        ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public ViewHolder createViewHolder(View v) {
        ViewHolder holder=new ViewHolder(v);
        holder.tvTitle= (TextView) v.findViewById(R.id.tvTodoTitle);
        holder.tvBody= (TextView) v.findViewById(R.id.tvTodoBody);
        holder.ivTodo=(ImageView)v.findViewById(R.id.ivTodo);
        return holder;
    }

    @Override
    public int getViewLayoutResourceId() {
        return R.layout.layout_todo;
    }

    @Override
    public void onBindItem(ViewHolder holder, final Todo item) {
       // holder.tvId.setText(Long.toString(item.id));
        holder.tvTitle.setText(item.title);
        holder.tvBody.setText(item.completed? "completed!": "not yet finished");
        holder.ivTodo.setActivated(item.completed);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Selected item "+item.title, Toast.LENGTH_LONG).show();
/*
                Intent intent = new Intent(v.getContext(), PostActivity.class);
                Bundle b = new Bundle();
                b.putLong("todoId", item.id);
                intent.putExtras(b);
                v.getContext().startActivity(intent);*/
            }
        });
    }

}

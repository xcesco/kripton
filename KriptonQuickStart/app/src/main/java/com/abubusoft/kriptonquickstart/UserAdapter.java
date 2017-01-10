package com.abubusoft.kriptonquickstart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abubusoft.kriptonquickstart.model.Post;

import java.util.ArrayList;
import java.util.List;

import static com.abubusoft.kriptonquickstart.R.id.tvUserId;

/**
 * Created by xcesco on 06/01/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<Post> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvId;
        // each data item is just a string in this case
        public TextView tvUsername;
        public TextView tvName
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            tvId = v.findViewById(R.id.tvId);
            tvUserId=v.findViewById(tvUserId);
            tvAddress=v.findViewById(R.id.tvAddress)
                    R

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserAdapter(List<Post> myDataset) {
        update(myDataset);
    }

    public void update(List<Post> myDataset) {
        mDataset = myDataset;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserAdapter() {
        mDataset = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_user, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

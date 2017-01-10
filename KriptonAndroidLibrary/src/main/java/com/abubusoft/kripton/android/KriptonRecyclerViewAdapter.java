package com.abubusoft.kripton.android;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 * @param <VH>
 */
public abstract class KriptonRecyclerViewAdapter<T, VH extends KriptonRecyclerViewAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {

    public KriptonRecyclerViewAdapter() {
        dataset=new ArrayList<T>();
    }

    public ArrayList<T> dataset;

    public void removeAt(int position) {
        dataset.remove(position);
    }

    public T get(int position) {
        return dataset.get(position);
    }

    public boolean isEmpty() {
        return dataset.isEmpty();
    }

    public void clear() {
        dataset.clear();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View v) {
            super(v);

        }
    }

    public void add(int position, T item) {
        dataset.add(position, item);
        notifyItemInserted(position);
    }

    public void update(List<T> items) {
        dataset.clear();

        dataset.addAll(items);
        notifyDataSetChanged();
    }

    public void remove(T item) {
        int position = dataset.indexOf(item);
        dataset.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public KriptonRecyclerViewAdapter(Activity activity, ArrayList<T> myDataset) {
        dataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VH onCreateViewHolder(ViewGroup parent,
                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(getViewLayoutResourceId(), parent, false);
        // set the view's size, margins, paddings and layout parameters
        VH vh = createViewHolder(v);
        return vh;
    }

    public abstract VH createViewHolder(View view);

    public abstract int getViewLayoutResourceId();

    public abstract void onBindItem(VH holder, T item);


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(VH holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final T item = dataset.get(position);

        onBindItem(holder, item);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }

}  
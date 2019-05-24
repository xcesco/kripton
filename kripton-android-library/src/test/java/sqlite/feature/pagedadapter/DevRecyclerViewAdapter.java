package sqlite.feature.pagedadapter;

import android.view.View;

public class DevRecyclerViewAdapter extends KriptonRecyclerViewAdapter<Person, DevViewHolder> {

	public DevRecyclerViewAdapter(PagedLiveData<List<Person>> pagedResult, int viewPageSize,
			CustomDiffCallback<Person> diff) {
		super(pagedResult, viewPageSize, diff);
	}

	public static class DevViewHolder extends KriptonRecyclerViewAdapter.KriptonViewHolder<Person> {

		public DevViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
		}

	}
}
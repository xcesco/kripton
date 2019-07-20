package sqlite.feature.pagedadapter;

import com.abubusoft.kripton.androidx.widgets.CustomDiffCallback;

public class PersonDiffCallback extends CustomDiffCallback<Person>{


	@Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersons.get(oldItemPosition).id == newPersons.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersons.get(oldItemPosition).equals(newPersons.get(newItemPosition));
    }


}
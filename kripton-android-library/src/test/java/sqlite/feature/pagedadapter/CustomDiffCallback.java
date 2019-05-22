package sqlite.feature.pagedadapter;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public abstract class CustomDiffCallback<E> extends DiffUtil.Callback {

	public interface ItemComparer<E> {
		boolean sameId(E value1, E value2);

		boolean sameValue(E value1, E value2);
	}

	List<E> oldPersons;
	List<E> newPersons;
	

	public CustomDiffCallback() {				
	}
	
	public void setOldList(List<E> newPersons) {
		this.newPersons=newPersons;
	}

	
	public void setIncomingList(List<E> newPersons) {
		this.newPersons=newPersons;
	}

	@Override
	public int getOldListSize() {
		return oldPersons == null ? 0 : oldPersons.size();
	}

	@Override
	public int getNewListSize() {
		return newPersons == null ? 0 : newPersons.size();
	}

	
	@Override
	public Object getChangePayload(int oldItemPosition, int newItemPosition) {
		// you can return particular field for changed item.
		return super.getChangePayload(oldItemPosition, newItemPosition);
	}
		
}
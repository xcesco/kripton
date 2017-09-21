package sqlite.stack44633883;

import java.util.HashSet;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable(name = "SchoolLunches")
public class SchoolLunch {
    
    @BindColumn(columnType=ColumnType.PRIMARY_KEY)
    private long lunchId;

    public long getLunchId() {
		return lunchId;
	}

	public void setLunchId(long lunchId) {
		this.lunchId = lunchId;
	}

	private boolean fresh;

    public boolean isFresh() {
		return fresh;
	}

	public void setFresh(boolean fresh) {
		this.fresh = fresh;
	}

	private boolean containsMeat;

    private HashSet<String> fruits;

    public boolean isContainsMeat() {
        return containsMeat;
    }

    public void setContainsMeat(boolean containsMeat) {
        this.containsMeat = containsMeat;
    }

    public HashSet<String> getFruits() {
        return fruits;
    }

    public void setFruits(HashSet<String> fruits) {
        this.fruits = fruits;
    }
}
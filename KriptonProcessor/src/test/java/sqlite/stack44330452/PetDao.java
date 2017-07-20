package sqlite.stack44330452;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Pet.class)
public interface PetDao {
	@BindSqlSelect
	public List<Pet> loadPet();
}

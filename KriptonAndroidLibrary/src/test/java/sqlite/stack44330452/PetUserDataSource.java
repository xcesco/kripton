package sqlite.stack44330452;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet= { UserDao.class, PetDao.class }, fileName = "pet.db")
public interface PetUserDataSource {

}

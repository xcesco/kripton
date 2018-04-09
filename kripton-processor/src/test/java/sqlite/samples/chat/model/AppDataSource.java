package sqlite.samples.chat.model;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(
        daoSet = {DaoUser.class, DaoMessage.class}, fileName = "person.db")
public interface AppDataSource {
}

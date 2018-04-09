package sqlite.samples.chat.model;

import com.abubusoft.kripton.android.annotation.BindDao;

@BindDao(User.class)
public interface DaoUser extends DaoBase<User> {
}

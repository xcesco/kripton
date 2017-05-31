package sqlite.adapter.example01;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(dao=PersonDAO.class, fileName = "example.db")
public interface Example01DataSource {

}

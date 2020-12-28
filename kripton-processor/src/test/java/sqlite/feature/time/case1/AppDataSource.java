package sqlite.feature.time.case1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = {DaoBean.class, DaoImmutableBean.class, DaoBeanWithAccessors.class}, fileName = "app.db")
public interface AppDataSource {
}

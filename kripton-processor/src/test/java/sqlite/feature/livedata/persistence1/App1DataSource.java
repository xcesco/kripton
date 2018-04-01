package sqlite.feature.livedata.persistence1;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindContentProvider(authority="com.abubsoft.kripton")
@BindDataSource(fileName="app.db", version=2, daoSet={DaoPerson1.class})
public interface App1DataSource {
 
} 

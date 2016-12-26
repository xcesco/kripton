package commons.benchmark.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName = "benchmark.db", dao = { UserDao.class })
public interface BenchmarkDataSource {

}

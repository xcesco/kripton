package commons.benchmark;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import commons.benchmark.model.Friend;
import commons.benchmark.model.Image;
import commons.benchmark.model.Name;
import commons.benchmark.model.Response;
import commons.benchmark.model.User;
import commons.benchmark.persistence.BenchmarkDataSource;
import commons.benchmark.persistence.UserDao;
import sqlite.AbstractBindSQLiteProcessorTest;

public class TestBenchmarkCompile extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testCompile_1() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Friend.class, Image.class, Name.class, Response.class, User.class);
	//	buildDataSourceProcessorTest(BenchmarkDataSource.class, UserDao.class, Friend.class, Image.class, Name.class, Response.class, User.class);
	}
	
	
}

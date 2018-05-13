package sqlite.feature.relations.error4;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Artist {

	public long id;

	public String name;

	@BindSqlRelation(foreignKey = "albumId")
	public List<Song> songs;
}

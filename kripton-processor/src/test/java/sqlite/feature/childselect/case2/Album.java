package sqlite.feature.childselect.case2;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Album {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String name;

	@BindSqlRelation(foreignKey = "albumId")
	protected List<Song> songs;

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
}

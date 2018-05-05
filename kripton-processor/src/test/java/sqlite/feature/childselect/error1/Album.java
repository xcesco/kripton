package sqlite.feature.childselect.error1;

import java.util.Set;

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
	protected Set<Song> songs;

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
}

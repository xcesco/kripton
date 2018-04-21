package sqlite.feature.relations.case1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindRelation;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Album {
	public long id;
	public String name;
	
	@BindRelation(foreignKey="albumId")
	protected List<Song> songs;

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
}

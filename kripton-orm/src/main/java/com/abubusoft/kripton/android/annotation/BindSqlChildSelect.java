/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * DAOs work with associated entities, so usually it can be used to
 * insert/update/select/delete a specific type of bean. Suppose you have a data
 * source which data model is composed by two entities: album and song. There is
 * a one-2-many relation between them.
 * </p>
 * 
 * <pre>
 * &#64;BindTable
 * public class Album {
 * 	public long id;
 * 	public String name;
 * 
 * 	&#64;BindRelation(foreignKey = "albumId")
 * 	public List<Song> songs;
 * }
 * </pre>
 * 
 * <pre>
 * &#64;BindTable
 * public class Song {
 * 	public long id;
 * 	public String name;
 * 
 * 	&#64;BindColumn(parentEntity = Album.class)
 * 	public long albumId;
 * }
 * </pre>
 * <p>
 * albumId is the foreign key of the relation. songs field, marked with
 * <code>&#64;BindRelation</code>, can contains all album's songs and it can not be
 * stored in a table column. Every class is managed by its DAO. If you want, you
 * can link associated DAO to load an album and its songs with the use of child
 * selects.
 * </p>
 * 
 * <pre>
 * &#64;BindDao(Album.class)
 * public interface DaoAlbum extends DaoBase<Album> {
 * 	&#64;BindSqlSelect(childrenSelects = { &#64;BindSqlChildSelect(relation = "songs", method = "selectByAlbumId") })
 * 	List<Album> selectAlbums();
 * }
 * 
 * &#64;BindDao(Song.class)
 * public interface DaoSong extends DaoBase<Song> {
 * 
 * 	&#64;BindSqlSelect
 * 	List<Song> selectAll();
 * 
 * 	&#64;BindSqlSelect(where = "albumId=${albumId}")
 * 	List<Song> selectByAlbumId(&#64;BindSqlParam("albumId") long dummy);
 * }
 * </pre>
 * <p>
 * In the above DAO definitions, method selectAlbum load all albums and for each
 * album, to valorize songs field, uses the DaoSong#selectByAlbumId.
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface BindSqlChildSelect {

	/**
	 * Field annnotated with <code>BindRelation</code> that need to be valorized
	 * with this subquery.
	 * 
	 * @return name of relation
	 */
	String field();

	/**
	 * method's name of dao associated to child entity of relation to invoke to fill
	 * field. This method must have only one parameter: the foreign key value
	 * 
	 * @return method of child dao to use
	 */
	String method();

}

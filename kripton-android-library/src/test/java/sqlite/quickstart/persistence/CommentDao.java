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
package sqlite.quickstart.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.quickstart.model.Comment;


/**
 * Created by xcesco on 04/01/2017.
 */
@BindDao(Comment.class)
public interface CommentDao {
    
    /**
     * Insert.
     *
     * @param bean the bean
     */
    @BindSqlInsert(includePrimaryKey = true)
    void insert(Comment bean);

    /**
     * Select by post id.
     *
     * @param postId the post id
     * @return the list
     */
    @BindSqlSelect(where="postId = ${value}")
    List<Comment> selectByPostId(@BindSqlParam("value") long postId);

    /**
     * Select one by post id.
     *
     * @param postId the post id
     * @return the comment
     */
    @BindSqlSelect(where="id = ${value}")
    Comment selectOneByPostId(@BindSqlParam("value") long postId);

}

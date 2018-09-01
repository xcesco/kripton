/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sqlite.feature.custombean.err1;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

import java.util.List;

@BindDao(User.class)
public interface UserDao {
    @BindSqlSelect
    List<User> loadAllUsers();

    @BindSqlSelect(where="id = :id")
    User loadUserById(int id);

    @BindSqlSelect(where="name = :firstName and lastName = :lastName")
    List<User> findUserByNameAndLastName(String firstName, String lastName);

    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.IGNORE)
    void insertUser(User user);

    @BindSqlDelete
    void deleteUser(User user);

    @BindSqlDelete(where="name like :badName OR lastName like :badName")
    int deleteUsersByName(String badName);

    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.IGNORE)
    void insertOrReplaceUsers(User users);

    @BindSqlDelete(where="id=:user.id")
    void deleteUsers(User user);

    @BindSqlSelect(where="age = :age") // TODO: Fix this!
    List<User> findUsersYoungerThan(int age);

    @BindSqlSelect(where="age < :age")
    List<User> findUsersYoungerThanSolution(int age);

    @BindSqlDelete
    void deleteAll();
}
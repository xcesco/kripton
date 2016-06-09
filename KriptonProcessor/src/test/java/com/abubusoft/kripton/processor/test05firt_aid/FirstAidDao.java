package com.abubusoft.kripton.processor.test05firt_aid;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;

import java.util.List;

/**
 * Created by xcesco on 09/06/2016.
 */
@BindDao(value = FirstAid.class)
public interface FirstAidDao {

    @BindSelect(orderBy = "name")
    List<FirstAid> selectAll();

    @BindDelete(where="1=1")
    int deleteAll();

    @BindInsert
    int insert(FirstAid bean);

}

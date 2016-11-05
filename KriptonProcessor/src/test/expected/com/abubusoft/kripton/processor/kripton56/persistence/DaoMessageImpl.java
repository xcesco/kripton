package com.abubusoft.kripton.processor.kripton56.persistence;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.processor.kripton56.entities.OwnerType;

/**
 * <p>
 * DAO implementation for entity <code>MessageEntity</code>, based on interface <code>DaoMessage</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton56.entities.MessageEntity
 *  @see DaoMessage
 *  @see com.abubusoft.kripton.processor.kripton56.entities.MessageEntity$Table
 */
public class DaoMessageImpl extends AbstractDao implements DaoMessage {
  public DaoMessageImpl(BindWhisperDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE message SET ownerType=${ownerType} WHERE  id = ${id}</pre>
   *
   * @param id
   * 	used in where condition
   * @param ownerType
   * 	used as updated field
   *
   * @return true if record is updated */
  @Override
  public boolean updateById(long id, OwnerType ownerType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (ownerType!=null) {
      contentValues.put("owner_type", ownerType.toString());
    } else {
      contentValues.putNull("owner_type");
    }

    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("UPDATE message SET ownerType='"+StringUtil.checkSize(contentValues.get("owner_type"))+"' WHERE  id = %s"), (Object[])whereConditions);
    int result = database().update("message", contentValues, " id = ?", whereConditions);
    return result!=0;
  }
}

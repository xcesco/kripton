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
 *
 *  @see com.abubusoft.kripton.processor.kripton56.entities.MessageEntity
 *  @see DaoMessage
 *  @see com.abubusoft.kripton.processor.kripton56.entities.MessageEntityTable
 */
public class DaoMessageImpl extends AbstractDao implements DaoMessage {
  public DaoMessageImpl(BindWhisperDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL update:</p>
   * <pre>UPDATE message SET ownerType=${ownerType} WHERE  id = ${id}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>owner_type</dt><dd>is binded to query's parameter <strong>${ownerType}</strong> and method's parameter <strong>ownerType</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param ownerType
   * 	is used as updated field <strong>ownerType</strong>
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
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

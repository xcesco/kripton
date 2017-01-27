package sqlite.kripton56.persistence;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import sqlite.kripton56.entities.OwnerType;

/**
 * <p>
 * DAO implementation for entity <code>MessageEntity</code>, based on interface <code>DaoMessage</code>
 * </p>
 *
 *  @see sqlite.kripton56.entities.MessageEntity
 *  @see DaoMessage
 *  @see sqlite.kripton56.entities.MessageEntityTable
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

    String[] whereConditionsArray={String.valueOf(id)};

    Logger.info(StringUtils.formatSQL("UPDATE message SET ownerType='"+StringUtils.checkSize(contentValues.get("owner_type"))+"' WHERE  id = %s", (Object[])whereConditionsArray));
    int result = database().update("message", contentValues, " id = ?", whereConditionsArray);
    return result!=0;
  }
}

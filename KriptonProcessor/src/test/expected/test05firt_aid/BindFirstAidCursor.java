package test05firt_aid;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>FirstAid</code>
 * </p>
 *  @see FirstAid
 */
public class BindFirstAidCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "uid"
   */
  protected int index1;

  /**
   * Index for column "description"
   */
  protected int index2;

  /**
   * Index for column "info"
   */
  protected int index3;

  /**
   * Index for column "longitude"
   */
  protected int index4;

  /**
   * Index for column "latitude"
   */
  protected int index5;

  /**
   * Index for column "address"
   */
  protected int index6;

  /**
   * Index for column "address2"
   */
  protected int index7;

  /**
   * Index for column "city"
   */
  protected int index8;

  /**
   * Index for column "phone"
   */
  protected int index9;

  /**
   * Index for column "totalPatientCount"
   */
  protected int index10;

  /**
   * Index for column "whiteWaitingPatients"
   */
  protected int index11;

  /**
   * Index for column "whiteVisitingPatients"
   */
  protected int index12;

  /**
   * Index for column "whiteAverageWaitingTime"
   */
  protected int index13;

  /**
   * Index for column "greenWaitingPatients"
   */
  protected int index14;

  /**
   * Index for column "greenVisitingPatients"
   */
  protected int index15;

  /**
   * Index for column "greenAverageWaitingTime"
   */
  protected int index16;

  /**
   * Index for column "yellowWaitingPatients"
   */
  protected int index17;

  /**
   * Index for column "yellowVisitingPatients"
   */
  protected int index18;

  /**
   * Index for column "yellowAverageWaitingTime"
   */
  protected int index19;

  /**
   * Index for column "redWaitingPatients"
   */
  protected int index20;

  /**
   * Index for column "redAverageWaitingTime"
   */
  protected int index21;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindFirstAidCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindFirstAidCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("uid");
    index2=cursor.getColumnIndex("description");
    index3=cursor.getColumnIndex("info");
    index4=cursor.getColumnIndex("longitude");
    index5=cursor.getColumnIndex("latitude");
    index6=cursor.getColumnIndex("address");
    index7=cursor.getColumnIndex("address2");
    index8=cursor.getColumnIndex("city");
    index9=cursor.getColumnIndex("phone");
    index10=cursor.getColumnIndex("total_patient_count");
    index11=cursor.getColumnIndex("white_waiting_patients");
    index12=cursor.getColumnIndex("white_visiting_patients");
    index13=cursor.getColumnIndex("white_average_waiting_time");
    index14=cursor.getColumnIndex("green_waiting_patients");
    index15=cursor.getColumnIndex("green_visiting_patients");
    index16=cursor.getColumnIndex("green_average_waiting_time");
    index17=cursor.getColumnIndex("yellow_waiting_patients");
    index18=cursor.getColumnIndex("yellow_visiting_patients");
    index19=cursor.getColumnIndex("yellow_average_waiting_time");
    index20=cursor.getColumnIndex("red_waiting_patients");
    index21=cursor.getColumnIndex("red_average_waiting_time");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<FirstAid> execute() {

    LinkedList<FirstAid> resultList=new LinkedList<FirstAid>();
    FirstAid resultBean=new FirstAid();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new FirstAid();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.uid=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.description=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.info=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.longitude=cursor.getFloat(index4);}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.latitude=cursor.getFloat(index5);}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.address=cursor.getString(index6);}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.address2=cursor.getString(index7);}
        if (index8>=0 && !cursor.isNull(index8)) { resultBean.city=cursor.getString(index8);}
        if (index9>=0 && !cursor.isNull(index9)) { resultBean.phone=cursor.getString(index9);}
        if (index10>=0 && !cursor.isNull(index10)) { resultBean.totalPatientCount=cursor.getInt(index10);}
        if (index11>=0 && !cursor.isNull(index11)) { resultBean.whiteWaitingPatients=cursor.getInt(index11);}
        if (index12>=0 && !cursor.isNull(index12)) { resultBean.whiteVisitingPatients=cursor.getInt(index12);}
        if (index13>=0 && !cursor.isNull(index13)) { resultBean.whiteAverageWaitingTime=cursor.getString(index13);}
        if (index14>=0 && !cursor.isNull(index14)) { resultBean.greenWaitingPatients=cursor.getInt(index14);}
        if (index15>=0 && !cursor.isNull(index15)) { resultBean.greenVisitingPatients=cursor.getInt(index15);}
        if (index16>=0 && !cursor.isNull(index16)) { resultBean.greenAverageWaitingTime=cursor.getString(index16);}
        if (index17>=0 && !cursor.isNull(index17)) { resultBean.yellowWaitingPatients=cursor.getInt(index17);}
        if (index18>=0 && !cursor.isNull(index18)) { resultBean.yellowVisitingPatients=cursor.getInt(index18);}
        if (index19>=0 && !cursor.isNull(index19)) { resultBean.yellowAverageWaitingTime=cursor.getString(index19);}
        if (index20>=0 && !cursor.isNull(index20)) { resultBean.redWaitingPatients=cursor.getInt(index20);}
        if (index21>=0 && !cursor.isNull(index21)) { resultBean.redAverageWaitingTime=cursor.getString(index21);}

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * Method executed for each row extracted from database. For each row specified listener will be invoked.
   *
   * @param listener listener to invoke for each row
   */
  public void executeListener(OnFirstAidListener listener) {
    FirstAid resultBean=new FirstAid();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.uid=null;}
        if (index2>=0) { resultBean.description=null;}
        if (index3>=0) { resultBean.info=null;}
        if (index4>=0) { resultBean.longitude=null;}
        if (index5>=0) { resultBean.latitude=null;}
        if (index6>=0) { resultBean.address=null;}
        if (index7>=0) { resultBean.address2=null;}
        if (index8>=0) { resultBean.city=null;}
        if (index9>=0) { resultBean.phone=null;}
        if (index10>=0) { resultBean.totalPatientCount=0;}
        if (index11>=0) { resultBean.whiteWaitingPatients=0;}
        if (index12>=0) { resultBean.whiteVisitingPatients=0;}
        if (index13>=0) { resultBean.whiteAverageWaitingTime=null;}
        if (index14>=0) { resultBean.greenWaitingPatients=0;}
        if (index15>=0) { resultBean.greenVisitingPatients=0;}
        if (index16>=0) { resultBean.greenAverageWaitingTime=null;}
        if (index17>=0) { resultBean.yellowWaitingPatients=0;}
        if (index18>=0) { resultBean.yellowVisitingPatients=0;}
        if (index19>=0) { resultBean.yellowAverageWaitingTime=null;}
        if (index20>=0) { resultBean.redWaitingPatients=0;}
        if (index21>=0) { resultBean.redAverageWaitingTime=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.uid=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.description=cursor.getString(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.info=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.longitude=cursor.getFloat(index4);}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.latitude=cursor.getFloat(index5);}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.address=cursor.getString(index6);}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.address2=cursor.getString(index7);}
        if (index8>=0 && !cursor.isNull(index8)) { resultBean.city=cursor.getString(index8);}
        if (index9>=0 && !cursor.isNull(index9)) { resultBean.phone=cursor.getString(index9);}
        if (index10>=0 && !cursor.isNull(index10)) { resultBean.totalPatientCount=cursor.getInt(index10);}
        if (index11>=0 && !cursor.isNull(index11)) { resultBean.whiteWaitingPatients=cursor.getInt(index11);}
        if (index12>=0 && !cursor.isNull(index12)) { resultBean.whiteVisitingPatients=cursor.getInt(index12);}
        if (index13>=0 && !cursor.isNull(index13)) { resultBean.whiteAverageWaitingTime=cursor.getString(index13);}
        if (index14>=0 && !cursor.isNull(index14)) { resultBean.greenWaitingPatients=cursor.getInt(index14);}
        if (index15>=0 && !cursor.isNull(index15)) { resultBean.greenVisitingPatients=cursor.getInt(index15);}
        if (index16>=0 && !cursor.isNull(index16)) { resultBean.greenAverageWaitingTime=cursor.getString(index16);}
        if (index17>=0 && !cursor.isNull(index17)) { resultBean.yellowWaitingPatients=cursor.getInt(index17);}
        if (index18>=0 && !cursor.isNull(index18)) { resultBean.yellowVisitingPatients=cursor.getInt(index18);}
        if (index19>=0 && !cursor.isNull(index19)) { resultBean.yellowAverageWaitingTime=cursor.getString(index19);}
        if (index20>=0 && !cursor.isNull(index20)) { resultBean.redWaitingPatients=cursor.getInt(index20);}
        if (index21>=0 && !cursor.isNull(index21)) { resultBean.redAverageWaitingTime=cursor.getString(index21);}

        listener.onRow(resultBean, cursor.getPosition(),cursor.getCount());
      } while (cursor.moveToNext());
    }
    cursor.close();
  }

  /**
   * <p>Create a binded cursor starting from a cursor</p>
   *
   * @param cursor to wrap
   */
  public static BindFirstAidCursor create(Cursor cursor) {
    return new BindFirstAidCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnFirstAidListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(FirstAid bean, int rowPosition, int rowCount);
  }
}

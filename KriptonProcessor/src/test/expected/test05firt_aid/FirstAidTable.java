package test05firt_aid;

import java.lang.String;

/**
 * <p>
 * Entity <code>FirstAid</code> is associated to table <code>first_aid</code>
 * This class represents table associated to entity.
 * </p>
 *  @see FirstAid
 */
public class FirstAidTable {
  /**
   * Costant represents name of table first_aid
   */
  public static final String TABLE_NAME = "first_aid";

  /**
   * <p>
   * DDL to create table first_aid
   * </p>
   *
   * <pre>CREATE TABLE first_aid (id INTEGER PRIMARY KEY AUTOINCREMENT, uid TEXT UNIQUE, description TEXT, info TEXT, longitude REAL, latitude REAL, address TEXT, address2 TEXT, city TEXT, phone TEXT, total_patient_count INTEGER, white_waiting_patients INTEGER, white_visiting_patients INTEGER, white_average_waiting_time TEXT, green_waiting_patients INTEGER, green_visiting_patients INTEGER, green_average_waiting_time TEXT, yellow_waiting_patients INTEGER, yellow_visiting_patients INTEGER, yellow_average_waiting_time TEXT, red_waiting_patients INTEGER, red_average_waiting_time TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE first_aid (id INTEGER PRIMARY KEY AUTOINCREMENT, uid TEXT UNIQUE, description TEXT, info TEXT, longitude REAL, latitude REAL, address TEXT, address2 TEXT, city TEXT, phone TEXT, total_patient_count INTEGER, white_waiting_patients INTEGER, white_visiting_patients INTEGER, white_average_waiting_time TEXT, green_waiting_patients INTEGER, green_visiting_patients INTEGER, green_average_waiting_time TEXT, yellow_waiting_patients INTEGER, yellow_visiting_patients INTEGER, yellow_average_waiting_time TEXT, red_waiting_patients INTEGER, red_average_waiting_time TEXT);";

  /**
   * <p>
   * DDL to drop table first_aid
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS first_aid;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS first_aid;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see FirstAid#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>uid</code> is associated to table column <code>uid</code>. This costant represents column name.
   *
   *  @see FirstAid#uid
   */
  public static final String COLUMN_UID = "uid";

  /**
   * Entity's property <code>description</code> is associated to table column <code>description</code>. This costant represents column name.
   *
   *  @see FirstAid#description
   */
  public static final String COLUMN_DESCRIPTION = "description";

  /**
   * Entity's property <code>info</code> is associated to table column <code>info</code>. This costant represents column name.
   *
   *  @see FirstAid#info
   */
  public static final String COLUMN_INFO = "info";

  /**
   * Entity's property <code>longitude</code> is associated to table column <code>longitude</code>. This costant represents column name.
   *
   *  @see FirstAid#longitude
   */
  public static final String COLUMN_LONGITUDE = "longitude";

  /**
   * Entity's property <code>latitude</code> is associated to table column <code>latitude</code>. This costant represents column name.
   *
   *  @see FirstAid#latitude
   */
  public static final String COLUMN_LATITUDE = "latitude";

  /**
   * Entity's property <code>address</code> is associated to table column <code>address</code>. This costant represents column name.
   *
   *  @see FirstAid#address
   */
  public static final String COLUMN_ADDRESS = "address";

  /**
   * Entity's property <code>address2</code> is associated to table column <code>address2</code>. This costant represents column name.
   *
   *  @see FirstAid#address2
   */
  public static final String COLUMN_ADDRESS2 = "address2";

  /**
   * Entity's property <code>city</code> is associated to table column <code>city</code>. This costant represents column name.
   *
   *  @see FirstAid#city
   */
  public static final String COLUMN_CITY = "city";

  /**
   * Entity's property <code>phone</code> is associated to table column <code>phone</code>. This costant represents column name.
   *
   *  @see FirstAid#phone
   */
  public static final String COLUMN_PHONE = "phone";

  /**
   * Entity's property <code>totalPatientCount</code> is associated to table column <code>total_patient_count</code>. This costant represents column name.
   *
   *  @see FirstAid#totalPatientCount
   */
  public static final String COLUMN_TOTAL_PATIENT_COUNT = "total_patient_count";

  /**
   * Entity's property <code>whiteWaitingPatients</code> is associated to table column <code>white_waiting_patients</code>. This costant represents column name.
   *
   *  @see FirstAid#whiteWaitingPatients
   */
  public static final String COLUMN_WHITE_WAITING_PATIENTS = "white_waiting_patients";

  /**
   * Entity's property <code>whiteVisitingPatients</code> is associated to table column <code>white_visiting_patients</code>. This costant represents column name.
   *
   *  @see FirstAid#whiteVisitingPatients
   */
  public static final String COLUMN_WHITE_VISITING_PATIENTS = "white_visiting_patients";

  /**
   * Entity's property <code>whiteAverageWaitingTime</code> is associated to table column <code>white_average_waiting_time</code>. This costant represents column name.
   *
   *  @see FirstAid#whiteAverageWaitingTime
   */
  public static final String COLUMN_WHITE_AVERAGE_WAITING_TIME = "white_average_waiting_time";

  /**
   * Entity's property <code>greenWaitingPatients</code> is associated to table column <code>green_waiting_patients</code>. This costant represents column name.
   *
   *  @see FirstAid#greenWaitingPatients
   */
  public static final String COLUMN_GREEN_WAITING_PATIENTS = "green_waiting_patients";

  /**
   * Entity's property <code>greenVisitingPatients</code> is associated to table column <code>green_visiting_patients</code>. This costant represents column name.
   *
   *  @see FirstAid#greenVisitingPatients
   */
  public static final String COLUMN_GREEN_VISITING_PATIENTS = "green_visiting_patients";

  /**
   * Entity's property <code>greenAverageWaitingTime</code> is associated to table column <code>green_average_waiting_time</code>. This costant represents column name.
   *
   *  @see FirstAid#greenAverageWaitingTime
   */
  public static final String COLUMN_GREEN_AVERAGE_WAITING_TIME = "green_average_waiting_time";

  /**
   * Entity's property <code>yellowWaitingPatients</code> is associated to table column <code>yellow_waiting_patients</code>. This costant represents column name.
   *
   *  @see FirstAid#yellowWaitingPatients
   */
  public static final String COLUMN_YELLOW_WAITING_PATIENTS = "yellow_waiting_patients";

  /**
   * Entity's property <code>yellowVisitingPatients</code> is associated to table column <code>yellow_visiting_patients</code>. This costant represents column name.
   *
   *  @see FirstAid#yellowVisitingPatients
   */
  public static final String COLUMN_YELLOW_VISITING_PATIENTS = "yellow_visiting_patients";

  /**
   * Entity's property <code>yellowAverageWaitingTime</code> is associated to table column <code>yellow_average_waiting_time</code>. This costant represents column name.
   *
   *  @see FirstAid#yellowAverageWaitingTime
   */
  public static final String COLUMN_YELLOW_AVERAGE_WAITING_TIME = "yellow_average_waiting_time";

  /**
   * Entity's property <code>redWaitingPatients</code> is associated to table column <code>red_waiting_patients</code>. This costant represents column name.
   *
   *  @see FirstAid#redWaitingPatients
   */
  public static final String COLUMN_RED_WAITING_PATIENTS = "red_waiting_patients";

  /**
   * Entity's property <code>redAverageWaitingTime</code> is associated to table column <code>red_average_waiting_time</code>. This costant represents column name.
   *
   *  @see FirstAid#redAverageWaitingTime
   */
  public static final String COLUMN_RED_AVERAGE_WAITING_TIME = "red_average_waiting_time";
}

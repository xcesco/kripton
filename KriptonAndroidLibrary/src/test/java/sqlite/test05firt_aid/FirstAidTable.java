package sqlite.test05firt_aid;

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
   * <pre>CREATE TABLE first_aid (ID INTEGER PRIMARY KEY AUTOINCREMENT, UID TEXT UNIQUE, DESCRIPTION TEXT, INFO TEXT, LONGITUDE REAL, LATITUDE REAL, ADDRESS TEXT, ADDRESS2 TEXT, CITY TEXT, PHONE TEXT, TOTAL_PATIENT_COUNT INTEGER, WHITE_WAITING_PATIENTS INTEGER, WHITE_VISITING_PATIENTS INTEGER, WHITE_AVERAGE_WAITING_TIME TEXT, GREEN_WAITING_PATIENTS INTEGER, GREEN_VISITING_PATIENTS INTEGER, GREEN_AVERAGE_WAITING_TIME TEXT, YELLOW_WAITING_PATIENTS INTEGER, YELLOW_VISITING_PATIENTS INTEGER, YELLOW_AVERAGE_WAITING_TIME TEXT, RED_WAITING_PATIENTS INTEGER, RED_AVERAGE_WAITING_TIME TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE first_aid (ID INTEGER PRIMARY KEY AUTOINCREMENT, UID TEXT UNIQUE, DESCRIPTION TEXT, INFO TEXT, LONGITUDE REAL, LATITUDE REAL, ADDRESS TEXT, ADDRESS2 TEXT, CITY TEXT, PHONE TEXT, TOTAL_PATIENT_COUNT INTEGER, WHITE_WAITING_PATIENTS INTEGER, WHITE_VISITING_PATIENTS INTEGER, WHITE_AVERAGE_WAITING_TIME TEXT, GREEN_WAITING_PATIENTS INTEGER, GREEN_VISITING_PATIENTS INTEGER, GREEN_AVERAGE_WAITING_TIME TEXT, YELLOW_WAITING_PATIENTS INTEGER, YELLOW_VISITING_PATIENTS INTEGER, YELLOW_AVERAGE_WAITING_TIME TEXT, RED_WAITING_PATIENTS INTEGER, RED_AVERAGE_WAITING_TIME TEXT);";

  /**
   * <p>
   * DDL to drop table first_aid
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS first_aid;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS first_aid;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>ID</code>. This costant represents column name.
   *
   *  @see FirstAid#id
   */
  public static final String COLUMN_ID = "ID";

  /**
   * Entity's property <code>uid</code> is associated to table column <code>UID</code>. This costant represents column name.
   *
   *  @see FirstAid#uid
   */
  public static final String COLUMN_UID = "UID";

  /**
   * Entity's property <code>description</code> is associated to table column <code>DESCRIPTION</code>. This costant represents column name.
   *
   *  @see FirstAid#description
   */
  public static final String COLUMN_DESCRIPTION = "DESCRIPTION";

  /**
   * Entity's property <code>info</code> is associated to table column <code>INFO</code>. This costant represents column name.
   *
   *  @see FirstAid#info
   */
  public static final String COLUMN_INFO = "INFO";

  /**
   * Entity's property <code>longitude</code> is associated to table column <code>LONGITUDE</code>. This costant represents column name.
   *
   *  @see FirstAid#longitude
   */
  public static final String COLUMN_LONGITUDE = "LONGITUDE";

  /**
   * Entity's property <code>latitude</code> is associated to table column <code>LATITUDE</code>. This costant represents column name.
   *
   *  @see FirstAid#latitude
   */
  public static final String COLUMN_LATITUDE = "LATITUDE";

  /**
   * Entity's property <code>address</code> is associated to table column <code>ADDRESS</code>. This costant represents column name.
   *
   *  @see FirstAid#address
   */
  public static final String COLUMN_ADDRESS = "ADDRESS";

  /**
   * Entity's property <code>address2</code> is associated to table column <code>ADDRESS2</code>. This costant represents column name.
   *
   *  @see FirstAid#address2
   */
  public static final String COLUMN_ADDRESS2 = "ADDRESS2";

  /**
   * Entity's property <code>city</code> is associated to table column <code>CITY</code>. This costant represents column name.
   *
   *  @see FirstAid#city
   */
  public static final String COLUMN_CITY = "CITY";

  /**
   * Entity's property <code>phone</code> is associated to table column <code>PHONE</code>. This costant represents column name.
   *
   *  @see FirstAid#phone
   */
  public static final String COLUMN_PHONE = "PHONE";

  /**
   * Entity's property <code>totalPatientCount</code> is associated to table column <code>TOTAL_PATIENT_COUNT</code>. This costant represents column name.
   *
   *  @see FirstAid#totalPatientCount
   */
  public static final String COLUMN_TOTAL_PATIENT_COUNT = "TOTAL_PATIENT_COUNT";

  /**
   * Entity's property <code>whiteWaitingPatients</code> is associated to table column <code>WHITE_WAITING_PATIENTS</code>. This costant represents column name.
   *
   *  @see FirstAid#whiteWaitingPatients
   */
  public static final String COLUMN_WHITE_WAITING_PATIENTS = "WHITE_WAITING_PATIENTS";

  /**
   * Entity's property <code>whiteVisitingPatients</code> is associated to table column <code>WHITE_VISITING_PATIENTS</code>. This costant represents column name.
   *
   *  @see FirstAid#whiteVisitingPatients
   */
  public static final String COLUMN_WHITE_VISITING_PATIENTS = "WHITE_VISITING_PATIENTS";

  /**
   * Entity's property <code>whiteAverageWaitingTime</code> is associated to table column <code>WHITE_AVERAGE_WAITING_TIME</code>. This costant represents column name.
   *
   *  @see FirstAid#whiteAverageWaitingTime
   */
  public static final String COLUMN_WHITE_AVERAGE_WAITING_TIME = "WHITE_AVERAGE_WAITING_TIME";

  /**
   * Entity's property <code>greenWaitingPatients</code> is associated to table column <code>GREEN_WAITING_PATIENTS</code>. This costant represents column name.
   *
   *  @see FirstAid#greenWaitingPatients
   */
  public static final String COLUMN_GREEN_WAITING_PATIENTS = "GREEN_WAITING_PATIENTS";

  /**
   * Entity's property <code>greenVisitingPatients</code> is associated to table column <code>GREEN_VISITING_PATIENTS</code>. This costant represents column name.
   *
   *  @see FirstAid#greenVisitingPatients
   */
  public static final String COLUMN_GREEN_VISITING_PATIENTS = "GREEN_VISITING_PATIENTS";

  /**
   * Entity's property <code>greenAverageWaitingTime</code> is associated to table column <code>GREEN_AVERAGE_WAITING_TIME</code>. This costant represents column name.
   *
   *  @see FirstAid#greenAverageWaitingTime
   */
  public static final String COLUMN_GREEN_AVERAGE_WAITING_TIME = "GREEN_AVERAGE_WAITING_TIME";

  /**
   * Entity's property <code>yellowWaitingPatients</code> is associated to table column <code>YELLOW_WAITING_PATIENTS</code>. This costant represents column name.
   *
   *  @see FirstAid#yellowWaitingPatients
   */
  public static final String COLUMN_YELLOW_WAITING_PATIENTS = "YELLOW_WAITING_PATIENTS";

  /**
   * Entity's property <code>yellowVisitingPatients</code> is associated to table column <code>YELLOW_VISITING_PATIENTS</code>. This costant represents column name.
   *
   *  @see FirstAid#yellowVisitingPatients
   */
  public static final String COLUMN_YELLOW_VISITING_PATIENTS = "YELLOW_VISITING_PATIENTS";

  /**
   * Entity's property <code>yellowAverageWaitingTime</code> is associated to table column <code>YELLOW_AVERAGE_WAITING_TIME</code>. This costant represents column name.
   *
   *  @see FirstAid#yellowAverageWaitingTime
   */
  public static final String COLUMN_YELLOW_AVERAGE_WAITING_TIME = "YELLOW_AVERAGE_WAITING_TIME";

  /**
   * Entity's property <code>redWaitingPatients</code> is associated to table column <code>RED_WAITING_PATIENTS</code>. This costant represents column name.
   *
   *  @see FirstAid#redWaitingPatients
   */
  public static final String COLUMN_RED_WAITING_PATIENTS = "RED_WAITING_PATIENTS";

  /**
   * Entity's property <code>redAverageWaitingTime</code> is associated to table column <code>RED_AVERAGE_WAITING_TIME</code>. This costant represents column name.
   *
   *  @see FirstAid#redAverageWaitingTime
   */
  public static final String COLUMN_RED_AVERAGE_WAITING_TIME = "RED_AVERAGE_WAITING_TIME";
}

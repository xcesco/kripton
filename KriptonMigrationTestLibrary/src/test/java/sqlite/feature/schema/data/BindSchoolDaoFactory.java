package sqlite.feature.schema.data;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for SchoolDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SchoolDataSource
 * @see DaoProfessor
 * @see DaoProfessorImpl
 * @see Professor
 * @see DaoSeminar
 * @see DaoSeminarImpl
 * @see Seminar
 * @see DaoSeminar2Student
 * @see DaoSeminar2StudentImpl
 * @see Seminar2Student
 * @see DaoStudent
 * @see DaoStudentImpl
 * @see Student
 */
public interface BindSchoolDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoProfessor
   */
  DaoProfessorImpl getDaoProfessor();

  /**
   *
   * retrieve dao DaoSeminar
   */
  DaoSeminarImpl getDaoSeminar();

  /**
   *
   * retrieve dao DaoSeminar2Student
   */
  DaoSeminar2StudentImpl getDaoSeminar2Student();

  /**
   *
   * retrieve dao DaoStudent
   */
  DaoStudentImpl getDaoStudent();
}

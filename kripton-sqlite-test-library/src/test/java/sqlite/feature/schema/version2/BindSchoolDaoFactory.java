package sqlite.feature.schema.version2;

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
   * Retrieve dao DaoProfessor.
   *
   * @return dao implementation
   */
  DaoProfessorImpl getDaoProfessor();

  /**
   * Retrieve dao DaoSeminar.
   *
   * @return dao implementation
   */
  DaoSeminarImpl getDaoSeminar();

  /**
   * Retrieve dao DaoSeminar2Student.
   *
   * @return dao implementation
   */
  DaoSeminar2StudentImpl getDaoSeminar2Student();

  /**
   * Retrieve dao DaoStudent.
   *
   * @return dao implementation
   */
  DaoStudentImpl getDaoStudent();
}

package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

import sqlite.feature.schema.version2.DaoProfessor;
import sqlite.feature.schema.version2.DaoProfessorImpl;
import sqlite.feature.schema.version2.DaoSeminar;
import sqlite.feature.schema.version2.DaoSeminar2Student;
import sqlite.feature.schema.version2.DaoSeminar2StudentImpl;
import sqlite.feature.schema.version2.DaoSeminarImpl;
import sqlite.feature.schema.version2.DaoStudent;
import sqlite.feature.schema.version2.DaoStudentImpl;
import sqlite.feature.schema.version2.Professor;
import sqlite.feature.schema.version2.SchoolDataSource;
import sqlite.feature.schema.version2.Seminar;
import sqlite.feature.schema.version2.Seminar2Student;
import sqlite.feature.schema.version2.Student;

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

/**
 * 
 */
package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Seminar2Student.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@BindType
@BindTable(name="seminar_2_student", uniqueIndexes={"studentId asc, seminarId desc"})
public class Seminar2Student  {

	/** The id. */
	public long id;
	
	/** The student id. */
	@BindColumn(foreignKey=Student.class)
	public long studentId;
	
	/** The seminar id. */
	@BindColumn(foreignKey=Seminar.class)
	public long seminarId;
}

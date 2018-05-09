/**
 * 
 */
package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindIndex;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Seminar2Student.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@BindType
@BindSqlType(name="seminar_2_student", indexes=@BindIndex(value={"studentId asc"," seminarId desc"}, unique=true))
public class Seminar2Student  {

	/** The id. */
	public long id;
	
	/** The student id. */
	@BindSqlColumn(parentEntity=Student.class)
	public long studentId;
	
	/** The seminar id. */
	@BindSqlColumn(parentEntity=Seminar.class)
	public long seminarId;
}

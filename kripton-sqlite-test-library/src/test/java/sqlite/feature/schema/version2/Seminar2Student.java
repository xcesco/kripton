/**
 * 
 */
package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindIndex;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

/**
 * The Class Seminar2Student.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@BindType
@BindTable(name="seminar_2_student", indexes=@BindIndex(value={"studentId asc"," seminarId desc"}, unique=true))
public class Seminar2Student  {

	/** The id. */
	public long id;
	
	/** The student id. */
	@BindColumn(parentEntity=Student.class)
	public long studentId;
	
	/** The seminar id. */
	@BindColumn(parentEntity=Seminar.class)
	public long seminarId;
}

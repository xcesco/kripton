/**
 * 
 */
package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@BindType
@BindTable(name="seminar_2_student", uniqueIndexes={"studentId asc, seminarId desc"})
public class Seminar2Student  {

	public long id;
	
	@BindColumn(foreignKey=Student.class)
	public long studentId;
	
	@BindColumn(foreignKey=Seminar.class)
	public long seminarId;
}

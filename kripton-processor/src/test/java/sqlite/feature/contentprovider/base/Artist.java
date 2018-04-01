package sqlite.feature.contentprovider.base;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

/**
 * Created by xcesco on 02/10/2017.
 */
@BindType
@BindTable
public class Artist extends Entity {
	public String name;
}

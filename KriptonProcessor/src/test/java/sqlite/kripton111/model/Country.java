package sqlite.kripton111.model;

import android.graphics.Bitmap;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Country {

    @BindColumn(columnType = ColumnType.PRIMARY_KEY)
	public long id;

	public long area;

    @BindColumn(nullable = false, columnType = ColumnType.UNIQUE)
	public String code;

    @BindColumn(nullable = false)
	public String callingCode;

	public String region;

    @BindColumn(nullable = false)
	public String name;


    @BindDisabled
    public Bitmap bitmap;
}

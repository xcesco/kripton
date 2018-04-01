package sqlite.feature.typeadapter.bitmap;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.annotation.BindTable;

import android.graphics.Bitmap;

@BindTable
public class Person {
	
	public long id;
	
	@BindSqlAdapter(adapter = BitmapTypeAdapter.class)
	@BindColumn()
	public Bitmap image;

}

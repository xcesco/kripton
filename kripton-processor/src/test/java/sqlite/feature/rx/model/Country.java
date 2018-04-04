package sqlite.feature.rx.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;

import android.graphics.Bitmap;

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

	public Map<Translation, String> translatedName = new LinkedHashMap<Translation, String>();

	public boolean hasTranslationFor(Translation type) {
		return translatedName.containsKey(type);
	}

	public String getTranslatedName(String language) {
		Translation t=null;
		language=language.toUpperCase();
		for (Translation item: Translation.values())
		{
			if (language.equals(item.toString()))
			{
				t = item;
				break;
			}
		}

		if (t != null && translatedName.get(t)!=null) {
			return translatedName.get(t);
		}



		return name;
	}

    @BindDisabled
    public Bitmap bitmap;
}

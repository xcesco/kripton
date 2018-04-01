package sqlite.feature.typeadapter.bitmap;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import com.abubusoft.kripton.android.BindSQLTypeAdapter;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapTypeAdapter implements BindSQLTypeAdapter<Bitmap, byte[]> {

	@Override
	public Bitmap toJava(byte[] dataValue) {
		if (dataValue == null)
			return null;
		return BitmapFactory.decodeByteArray(dataValue, 0, dataValue.length);
	}

	@Override
	public byte[] toData(Bitmap bitmap) {
		if (bitmap == null)
			return null;

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		return stream.toByteArray();
	}

	@Override
	public String toString(Bitmap javaValue) {
		throw (new KriptonRuntimeException("Unsupported operation!"));
	}

}
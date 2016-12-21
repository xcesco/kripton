package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.BindTypeAdapter;

public class BooleanByteArrayTypeAdapter implements BindTypeAdapter<Boolean, Long> {

	@Override
	public Boolean toJava(Long dataValue) throws Exception {
		if (dataValue>0) return true;
		return false;
	}

	@Override
	public Long toData(Boolean javaValue) throws Exception {
		if (javaValue) return 1L;
		
		return 0L;
	}
	
}


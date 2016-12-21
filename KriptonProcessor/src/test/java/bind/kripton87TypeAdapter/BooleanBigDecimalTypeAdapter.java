package bind.kripton87TypeAdapter;

import java.math.BigDecimal;

import com.abubusoft.kripton.BindTypeAdapter;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class BooleanBigDecimalTypeAdapter implements BindTypeAdapter<Boolean, BigDecimal> {

	@Override
	public Boolean toJava(BigDecimal dataValue) throws Exception {
		return dataValue.longValue()>0;
	}

	@Override
	public BigDecimal toData(Boolean javaValue) throws Exception {
		return new BigDecimal(javaValue?1:0);
	}

}

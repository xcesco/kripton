package bind.kripton87TypeAdapter;

import java.math.BigDecimal;

import com.abubusoft.kripton.BindTypeAdapter;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Enum87BigIntegerTypeAdapter implements BindTypeAdapter<Enum87A, BigDecimal> {

	@Override
	public Enum87A toJava(BigDecimal dataValue) throws Exception {
		return Enum87A.values()[dataValue.intValue()];
	}

	@Override
	public BigDecimal toData(Enum87A javaValue) throws Exception {
		return new BigDecimal(javaValue.ordinal());
	}

}

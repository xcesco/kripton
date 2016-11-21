package kripton70;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
@BindTypeXml
public class BeanAttribute70 {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanAttribute70 other = (BeanAttribute70) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@BindXml(value="name", xmlType=XmlType.ATTRIBUTE)
	protected long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public boolean valueBoolType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public short valueShortType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public byte valueByteType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public char valueCharType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public int valueIntType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public long valueLongType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public float valueFloatType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public double valueDoubleType;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Boolean valueBool;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Short valueShort;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	private Byte valueByte;
	
	public Byte getValueByte() {
		return valueByte;
	}

	public void setValueByte(Byte valueByte) {
		this.valueByte = valueByte;
	}

	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Character valueChar;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Integer valueInt;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Long valueLong;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Float valueFloat;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Double valueDouble;
	
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String valueString;
	
}

package com.example01.datamodel;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.annotation.BindTransform;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class TempiAttesa {

	@BindTransform(DateTransform.class)
	public Date dataAggiornamento;

    public List<Azienda> aziende;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 2;
		return "TempiAttesa [\n  " + (dataAggiornamento != null ? "dataAggiornamento=" + dataAggiornamento + ", \n  " : "") + (aziende != null ? "aziende=" + aziende.subList(0, Math.min(aziende.size(), maxLen)) : "") + "\n]";
	}
	
	
}

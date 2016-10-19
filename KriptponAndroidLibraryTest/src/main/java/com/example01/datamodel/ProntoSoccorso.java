package com.example01.datamodel;


import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

/**
 *
 */
@BindType
public class ProntoSoccorso {
	public String id;
	
	public String descrizione;
	
	public List<Dipartimento> dipartimenti;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 2;
		return "ProntoSoccorso [\n  " + (id != null ? "id=" + id + ", \n  " : "") + (descrizione != null ? "descrizione=" + descrizione + ", \n  " : "")
				+ (dipartimenti != null ? "dipartimenti=" + dipartimenti.subList(0, Math.min(dipartimenti.size(), maxLen)) : "") + "\n]";
	}
}

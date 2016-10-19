package com.example01.datamodel;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class CodiceColore {

	public String id;
	
	public String descrizione;
	
	public String rgb;
	
	public long priorita;
	
	public SituazionePazienti situazionePazienti;

	@Override
	public String toString() {
		return "CodiceColore [id=" + id + ", descrizione=" + descrizione
				+ ", rgb=" + rgb + ", priorita=" + priorita
				+ ", situazionePazienti=" + situazionePazienti + "]";
	}
}

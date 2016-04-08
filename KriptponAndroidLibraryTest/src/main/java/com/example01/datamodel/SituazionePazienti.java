package com.example01.datamodel;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class SituazionePazienti {

	public long numeroPazienti;

	public long numeroPazientiInVisita;
	
	public String mediaAttesa;
	
	public long numeroPazientiInAttesa;

	@Override
	public String toString() {
		return "SituazionePazienti [numeroPazienti=" + numeroPazienti
				+ ", numeroPazientiInVisita=" + numeroPazientiInVisita
				+ ", mediaAttesa=" + mediaAttesa + ", numeroPazientiInAttesa="
				+ numeroPazientiInAttesa + "]";
	}
}

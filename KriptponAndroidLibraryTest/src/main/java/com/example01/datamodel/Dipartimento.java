package com.example01.datamodel;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Dipartimento {

	public String id;
	
	public String descrizione;
	
	public String info;	
	
	public Float longitudine;
	
	public float latitudine;
	
	public String indirizzo;
	
	public String localita;
	
	public String comune;
	
	public String telefono;
	
	public List<CodiceColore> codiciColore;

	@Override
	public String toString() {
		return "Dipartimento [id=" + id + ", descrizione=" + descrizione
				+ ", info=" + info + ", indirizzo=" + indirizzo + ", localita="
				+ localita + ", comune=" + comune + ", telefono=" + telefono
				+ ", codiciColore=" + codiciColore + "]";
	}


}

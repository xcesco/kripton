package com.example01.datamodel;

import java.util.List;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder.database.ColumnType;

@BindType
@BindAllFields
public class Azienda {

	public String descrizione;

	@BindColumn(ColumnType.PRIMARY_KEY)
	public long id;

	public List<ProntoSoccorso> prontoSoccorsi;

	@Override
	public String toString() {
		return "Azienda [id=" + id + ", descrizione=" + descrizione +"]";
	}

}

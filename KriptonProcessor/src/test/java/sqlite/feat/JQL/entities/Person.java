package sqlite.feat.JQL.entities;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

import sqlite.feat.JQL.entities.Bean;
import sqlite.feat.JQL.entities.Child;

@BindType
public class Person extends Bean {
	

	public List<Child> listChild;
	
	public byte[] image;
}

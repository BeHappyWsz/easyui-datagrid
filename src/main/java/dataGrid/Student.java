package dataGrid;

import java.io.Serializable;

public class Student implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String idcard;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
}

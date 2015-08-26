package day9.Shop.reference;

import day9.Shop.Shop;

public class Reference extends Shop {
	
	private int id;
	private String name;
	
	public Reference() {
	}
		
	public void Save(){
		int newId = getDb().getNewId(this);
		setId(newId);
		getDb().addNewRecord(this); 
		
	}

	
	
	//*******************************************************************
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};

	@Override
	public String toString() {
		return getName();
	}
}

package TEMP;

public enum Category {
	
	Fantasy(1), History(2), Computers(3), Fiction(4), Food(5);
	
	private int id;
	
	private Category(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

}

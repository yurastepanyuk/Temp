package day9.Shop.reference;


/*
 * Spravo4nik klientov
 * Extends Reference
 */

public class Client extends Reference {
	
	public Client() {
		
	}

	
	//*******************************************************************
	//GETTERS AND SETTERS
	
	/*
	 * search object by ID Client
	 */
	public Client getObjectById(int id){
		
		for (Client element : getDb().getClient()) {
			if (element.getId() == id) {
				return element;
			}
		}
		
		return null;
	}

	/*
	 * search object by Name of Client
	 */
	public Client getObjectByName(String name){

		for (Client element : getDb().getClient()) {
			if (element.getName().indexOf(name) >= 0) {
				return element;
			}
		}

		return null;
	}
		
}


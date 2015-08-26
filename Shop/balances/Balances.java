package day9.Shop.balances;

import day9.Shop.Shop;

/*
 * Super class for all Balanses class
 * Klassi dannoy kategorii hranyat informaciyu o balance po raznim tablicam...
 */
public class Balances extends Shop {
	
	public void Save(){
		getDb().addNewRecord(this); 		
	}; 

}

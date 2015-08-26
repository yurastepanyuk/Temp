package day9.Shop.reports;

import day9.Shop.balances.BalancesAutoParts;
import day9.Shop.reference.AutoParts;

public class ReportsBalances extends Reports {
	
	public ReportsBalances() {
		
	}	
	
	public void currentBalancesAutoParts() {
	
		BalancesAutoParts[] selection = getDb().getBalancesAutoParts();
		
		for (BalancesAutoParts balancesAutoParts : selection) {
			int idAutoParts = balancesAutoParts.getIdAutoParts();
			
			AutoParts autoParts = new AutoParts().getObjectById(idAutoParts);
			
			System.out.println("Tovar " + autoParts.getName() + ", oststok " + balancesAutoParts.getQty());
			
		}
		
	}

}

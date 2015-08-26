package day9.Shop.reports;

import day9.Shop.inforamation.Prices;
import day9.Shop.reference.AutoParts;


/*
 * klas dlya polu4eniya dannuh from tables Information
 * extends Reports
 */
public class ReportsInformation extends Reports {
	
	/*
	 * Print price for good
	 */
	public void printPriceForGoods(){
		
		Prices[] selection = getDb().getPrices();
		
		for (Prices prices : selection) {
			int idAutoParts = prices.getIdAutoParts();
			AutoParts autoParts = new AutoParts().getObjectById(idAutoParts);
			
			System.out.println("Tovar " + autoParts.getName() + ", price " + prices.getPrise());
			
		}		
		
	}

}

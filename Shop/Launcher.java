package day9.Shop;


import day9.Shop.GUI.TransactSellUI;

import java.awt.*;

//Inicializaciya rabotu
public class Launcher {
	
	public static void main(String[] args) throws Exception {

		SplashScreen splashScreen = SplashScreen.getSplashScreen();
		Thread.sleep(1000);

		Graphics2D graphics2D = splashScreen.createGraphics();
		graphics2D.setColor(Color.RED);
		graphics2D.drawString("Loading...", 15, 70);
		splashScreen.update();
		Thread.sleep(5000);

		splashScreen.close();

		Shop shop = new Shop();
		shop.initialisation();

		//SellingUI sellingUI = new SellingUI(shop);

		TransactSellUI transactSellUI = new TransactSellUI(shop);
		
	}

}

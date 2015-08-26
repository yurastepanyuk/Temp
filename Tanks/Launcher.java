package day9.Tanks;

//import sun.net.www.protocol.http.HttpURLConnection.TunnelState;

/*METHODS
* Launcher
* 			* runTheGame() - Osnovnoy metod, zapuskaemiy posle inicializacii klassa. Upravlyaet algoritmom povedeniya tanka na pole boya
* 
*/

import day9.Tanks.enums.Direction;
import day9.Tanks.tanksobject.AbstractTank;

public class Launcher extends Tanks {
	
	public boolean COLORED_MODE = true;

	/* Method: static void TanksLauncher(TanksActionField actionField)
	 * Parameters:
	 * 		not
	 * Return value:
	 * 		not
	 * Use:
	 * 		Konstruktor klassa
	 * Example:
	 * 		TanksLauncher TL = new TanksLauncher(actionField)
	 */
	Launcher( ) throws Exception {
		
		actionField = new ActionField();

		//actionField.runTheGame();
		
	}
	
	/* Method: runTheGame() throws InterruptedException 
	 * Parameters:
	 * 		null
	 * Return value:
	 * 		void
	 * Use:
	 * 		Osnovnoy metod, zapuskaemiy posle inicializacii klassa
	 * 		upravlyaet algoritmom povedeniya tanka na pole boya
	 */	
	void runTheGame() throws Exception {
		
		actionField.repaint();
		
		//battleField.inicialisationBF();

		AbstractTank tank  = actionField.getTank();
		
		tank.fire();
		
		while (true) {
			int xFaktor = tank.getDirection().getID() % 4;
			Thread.sleep(tank.getSpeed() - xFaktor);

			actionField.repaint();
			
			BattleField battleField = actionField.getBattleField();
			battleField.printCoordinates("e", "9");
			int [] IntKoordinats =  battleField.getIntKoordinats("a", "2");
			//tank.moveToQuadrant(IntKoordinats[0], IntKoordinats[1]);
			tank.fire();
			Thread.sleep(2000);
			
			IntKoordinats =  battleField.getIntKoordinats("a", "1");
			//tank.moveToQuadrant(IntKoordinats[0], IntKoordinats[1]);
			tank.setTankDirection(Direction.RIGHT);
			tank.fire();
			tank.fire();
			tank.fire();
			Thread.sleep(2000);

			// Random movement 20 raz
			 for (int i = 0; i < 20; i++) {
				 Thread.sleep(tank.getSpeed());
				 tank.moveRandom();
			}	
		break;	
		}
			
			tank.clean();
			
			tank.destroy();
			
			return;			
			
		}	

}

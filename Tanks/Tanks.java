package day9.Tanks;

//STRUKTURA
//Glavniy klass igru TanksBullet. Yavlyaetsya superklassom dlya klassa TanksLauncher. TanksLauncher inicialisiruet klass 
// TanksActionField i zapuskaet metod runTheGame(actionField). Pri inicializacii klassa TanksActionField 
//inicialisiruyutsya klassu TanksBattleField i TanksTank. Klass TanksTank ispolzuet klass TanksBullet.

import day9.Tanks.ActionField;
import day9.Tanks.Launcher;

public class Tanks {
	
	public ActionField actionField;

	public static void main(String[] args) throws Exception {

		Launcher launcher = new Launcher();

	}

}

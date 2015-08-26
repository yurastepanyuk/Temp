package day9.Tanks;

/*METHODS
* 
* BattleField
* 			* inicialisationBF() 					- Inicializaciya polya boya, rasstanocka pregrad
* 			* getIntKoordinats(String Y,String X) 	- vuchislyaet znachenie quadranta v Integer po peredannim znacheniyam String.
* 			* getValueOfXY(int X, int Y) 			- vuchislyaet ZNACHENIE V quadrente po vhodyaschim znacheniyap Pikselov X i Y
* 			* isItObstacle(int X, int Y) 			- proveryaet nalichie prepyatstviya po koordinates 
* 			* getQtyBrick(int Y) 					- pods4itivaet quantity of bricks on horizontal Y
* 			* getQudrant(String v, String h)		- get first koordinates of quadrant as String
* 			* printCoordinates(String v, String h) 	- Print in console na4ala coordinates of quadrant po polu4ennim values qudrant
* 			* scanQuadrant(int Y, int X) 			- Vozvras4aet to 4to nahoditsya v quadrante
* 			* updateQuadrant(int Y, int X, String newValue) - set new value in quadrant
* 			* getDimentionX()						- dlya polu4eniya qty quadrantov polya boya po osi X 
* 			* getDimentionY()						- dlya polu4eniya qty quadrantov polya boya po osi Y
*/

import day9.Tanks.battlefieldobjects.*;
import day9.Tanks.commoninterface.Drawable;
import day9.Tanks.enums.Behavior;
import day9.Tanks.enums.Direction;
import day9.Tanks.tanksobject.AbstractTank;
import day9.Tanks.tanksobject.BT7;
import day9.Tanks.tanksobject.T34;
import day9.Tanks.tanksobject.Tiger;

import java.awt.*;

public class BattleField implements Drawable {
	
	public static final int BF_WIDTH  = 576;
	public static final int BF_HEIGHT = 576;
	
	public static int step = 64;
	
	//public static TanksBattleField battleField;
	public static ActionField actionField;
	public static String [][] battleField;
	public static BattleFieldObjects[][] battleFieldObj;

	public static String Vertikal = "abcdefghi";
	public static String Horizontal = "123456789";

	private int [] headquarters;//quadrant staba [9,5]

	BattleField(ActionField actionField){
		
		this.actionField = actionField;
		
		inicialisationBF();

	}	
	
	public int getBfWidth(){
		return BF_WIDTH;
	}
	
	public int getBfHeigt(){
		return BF_HEIGHT;
	}
	
	/** Method: inicialisationBF()
	 * Parameters:
	 * 		null
	 * Return value:
	 * 		void
	 * Use:
	 * 		Inicializaciya polya boya, rasstanocka pregrad
	 */	
	public void inicialisationBF(){
				
//		battleField = new String [][]{
//				{" "," "," "," "," "," "," "," "," "},
//				{" "," "," "," "," "," "," "," "," "},
//				{"B","B","B"," "," ","B","B","B","B"},
//				{" "," "," "," "," "," "," "," "," "},
//				{" ","B","B","B","B","B","B"," "," "},
//				{" "," "," "," "," "," "," "," "," "},
//				{"B","B"," "," "," ","B","B","B","B"},
//				{" "," "," "," "," "," "," "," "," "},
//				{" "," "," "," "," "," "," "," "," "}
//				};

		String [][] battleFieldTwo = new String [][]{
				{" "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "},
				{"B","B","B","R","R","R","B","B","B"},
				{" "," "," "," "," "," "," "," "," "},
				{" ","W","W","W","W","W","W"," "," "},
				{" "," "," "," "," "," "," "," "," "},
				{"B","B"," "," "," ","B","B","B","B"},
				{" "," "," "," "," "," "," "," "," "},
				{" "," "," "," ","E"," "," "," "," "}
		};

		this.battleFieldObj = new BattleFieldObjects[9][9];

		for (int i = 0; i <= battleFieldTwo.length-1; i++) {

			String [] bfString  = battleFieldTwo[i];

			for (int j = 0; j <= bfString.length - 1; j++) {

				int x = j * 64;
				int y = i * 64;

				if (bfString[j].equals("B")){
					this.battleFieldObj[i][j] = new Brick(x,y);
				}else if (bfString[j].equals("R")){
					this.battleFieldObj[i][j] = new Rock(x,y);
				}else if (bfString[j].equals("W")){
					this.battleFieldObj[i][j] = new Water(x,y);
				}else if (bfString[j].equals("E")){
					this.battleFieldObj[i][j] = new Eagle(x,y);
					headquarters = new int[]{i,j};
				}else{
					this.battleFieldObj[i][j] = new Empty(x,y);
				}
			}

		}
		
	}

	@Override
	public void draw(Graphics g) {
		for (int j = 0; j < battleFieldObj.length; j++) {
			for (int k = 0; k < battleFieldObj.length; k++) {
				battleFieldObj[j][k].draw(g);
			}
		}
	}
	
	/* Method: getIntKoordinats(String Y,String X)
	 * Parameters:
	 * 		String Y - strokovoe predstavlenie quadrant po Y
	 * 		String X - strokovoe predstavlenie quadrant po X
	 * Return value:
	 * 		int [] - coordinates YX - value quadrant. int []{0,2}
	 * Use:
	 * 		vuchislyaet znachenie quadranta v Integer po peredannim znacheniyam String
	 * Example:
	 * 		Input ("a", "3") -> output int []{0,2}
	 */	
	public int [] getIntKoordinats(String Y,String X){
		
		int [] intKoordinats = new int [2];
		
		intKoordinats[0] = Vertikal.indexOf(Y);
		intKoordinats[1] = Horizontal.indexOf(X);		
		
		return intKoordinats;
	}
	
	/* Method: getValueOfXY(int X, int Y)
	 * Parameters:
	 * 		int X - znachenie pixelov po X
	 * 		int Y - znachenie pixelov po Y
	 * Return value:
	 * 		String - "B", ""
	 * Use:
	 * 		vuchislyaet ZNACHENIE V quadrente po vhodyaschim znacheniyap Pikselov X i Y
	 * Example:
	 * 		input (int X (65), int Y(504)) -> return "Â" or "" or...
	 */
	BattleFieldObjects getValueOfXY(int X, int Y){
		
		BattleFieldObjects result;
		
		//Poluchim value tipa "1_8"
		String YX = actionField.getQuadrant(X, Y);
		
		//Poluchim Integer value qudrant
		int symv_ = YX.indexOf("_");
		int y = Integer.valueOf(YX.substring(0, symv_));
		int x = Integer.valueOf(YX.substring(symv_ + 1));
		
		if ((y < 0 || y >= 9) || (x < 0 || x >= 9)) {
			return null;
		}
		
		//get value in quadrant
		result = battleFieldObj[y][x];
		
		return  result;
		
	}	
	
	/* Method: isItObstacle(int X, int Y)
	 * Parameters:
	 * 		int X - znachenie pixelov po X
	 * 		int Y - znachenie pixelov po Y
	 * Return value:
	 * 		boolean
	 * Use:
	 * 		proveryaet nalichie prepyatstviya po koordinates 
	 * Example:
	 * 		input (int X (65), int Y(504)) -> true or false
	 */	
	public boolean isItObstacle(int X, int Y){
		
		boolean result = false;
		
		BattleFieldObjects valQuadrant = getValueOfXY(X, Y);
		
		if (valQuadrant == null) {
			result = true;
		} else if (!(valQuadrant instanceof Empty)) {
			result = true;
		}
		
		return result;
	}
	
	/* Method: getQtyBrick(int Y)
	 * Parameters:
	 * 		int Y - value of horizontal(1...9)
	 * Return value:
	 * 		int - quantity of bricks on horizontal Y
	 * Use:
	 * 		pods4itivaet quantity of bricks on horizontal Y
	 * Example:
	 * 		getQtyBrick(9)
	 */
	public int getQtyBrick(int Y){
		int result = 0;
		
		for (String val : battleField[Y]) {
			if (val.equals("B")) {
				result += 1;
			}
		}
		
		return result;
	}
	
	/* Method: getQudrant(String v, String h) 
	 * Parameters:
	 * 		String v - value of Vertikal as string
	 * 		String h - value of Horizontal as string
	 * Return value:
	 * 		String - first koordinates of quadrant
	 * Use:
	 * 		get first koordinates of quadrant as String
	 * Example:
	 * 		getQudrant("à", "3") -> "0_128"
	 */
	String getQudrant(String v, String h) {
		// String Vertikal = "abcdefghi";
		// String Horizontal = "123456789";

		if (v == null || h == null) {
			return "0_0";
		} else if ((Vertikal.indexOf(v) == -1) || (Horizontal.indexOf(h) == -1)) {
			return "0_0";
		}

		int dlinaStoronaQuadrant = 64;

		int numQuadrantV = Vertikal.indexOf(v) * dlinaStoronaQuadrant;
		int numQuadrantH = Horizontal.indexOf(h) * dlinaStoronaQuadrant;

		return Integer.toString(numQuadrantV) + "_"
				+ Integer.toString(numQuadrantH);

	}
	
	/* Method: printCoordinates(String v, String h) 
	 * Parameters:
	 * 		String v - value of quadrant on Vertikal as string
	 * 		String h - value of quadrant on Horizontal as string
	 * Return value:
	 * 		void
	 * Use:
	 * 		Print in console na4ala coordinates of quadrant po polu4ennim values qudrant
	 * Example:
	 * 		printCoordinates("a", "2") -> a2:(0px; 64px)
	 */
	void printCoordinates(String v, String h) {
		// "0_64"
		String koordinataNachaloQuadrant = getQudrant(v, h);

		int razdelitel = koordinataNachaloQuadrant.indexOf("_");

		String koordinataV = koordinataNachaloQuadrant.substring(0, razdelitel);
		String koordinataH = koordinataNachaloQuadrant
				.substring(razdelitel + 1);

		System.out.println(v + h + ":(" + koordinataV + "px;" + koordinataH
				+ "px)");
		

	}
	
	
	/* Method: scanQuadrant(int Y, int X) 
	 * Parameters:
	 * 		int Y - koordibnata quadranta po Y
	 * 		int X - koordibnata quadranta po X
	 * Return value:
	 * 		String - value qudranta
	 * Use:
	 * 		Vozvras4aet to 4to nahoditsya v quadrante
	 * Example:
	 * 		scanQuadrant(8, 2)
	 */
	public BattleFieldObjects scanQuadrant(int Y, int X) {

		if (Y < 0 || Y > 8 || X < 0 || X > 8){
			return new Rock(-100,-100);
		}
		
		return battleFieldObj[Y][X];

	}
	
	/* Method: updateQuadrant(int Y, int X, String newValue) 
	 * Parameters:
	 * 		int Y - koordibnata quadranta po Y
	 * 		int X - koordibnata quadranta po X
	 * 		String newValue - new value quadranta
	 * Return value:
	 * 		not
	 * Use:
	 * 		set new value in quadrant
	 * Example:
	 * 		updateQuadrant(8, 2, " ")
	 */
	public void updateQuadrant(int Y, int X, BattleFieldObjects newValue) {

		battleFieldObj[Y][X] = newValue;

	}
	
	/* Method: getDimentionX() 
	 * Parameters:
	 * 		not
	 * Return value:
	 * 		int - qty quadrant po X
	 * Use:
	 * 		dlya polu4eniya qty quadrantov polya boya po osi X 
	 * Example:
	 * 		getDimentionX()
	 */
	public int getDimentionX() {
		int result = 0;
		if (battleField.length != 0){
			result = battleField[1].length;
		}
		return result;

	}
	
	/* Method: getDimentionY() 
	 * Parameters:
	 * 		not
	 * Return value:
	 * 		int - qty quadrant po Y
	 * Use:
	 * 		dlya polu4eniya qty quadrantov polya boya po osi Y
	 * Example:
	 * 		getDimentionY()
	 */
	public int getDimentionY() {
		
		return battleField.length;

	}

	public int[] getHeadquarters() {
		return headquarters;
	}

	//scaning line on X or Y. Return quadrant with Empty/Brick
	public int[] getAvailableQuadrant(int coordinate, String value, int [] currentYX){

		int [] result = new int[2];

		BattleFieldObjects bfObj  = new Rock(-100,-100);

		if (value.equals("Y")){
			int currentX    = currentYX[1];

			for (int i = 1; i <= 9; i++){

				bfObj = scanQuadrant(coordinate, currentX - i);

				if (bfObj instanceof Empty || bfObj instanceof Brick){
					result[0] = coordinate;
					result[1] = currentX - i;
					break;
				}

				bfObj = scanQuadrant(coordinate, currentX + i);
				if (bfObj instanceof Empty || bfObj instanceof Brick){
					result[0] = coordinate;
					result[1] = currentX + i;
					break;
				}

			}
		} else if (value.equals("X")) {
			int currentY    = currentYX[0];

			for (int i = 1; i <= 18; i++){

				bfObj = scanQuadrant(currentY - i, coordinate);
				if (bfObj instanceof Empty || bfObj instanceof Brick){
					result[0] = currentY - i;
					result[1] = coordinate;
					break;
				}

				bfObj = scanQuadrant(currentY + i, coordinate);
				if (bfObj instanceof Empty || bfObj instanceof Brick){
					result[0] = currentY + i;
					result[1] = coordinate;
					break;
				}

			}

		}

		return result;

	}

	/**
	Return quadrant YX defender
	 */
	public int[] getQuadrantDefender(){
		return actionField.getQuadrantDefender();
	}

	/**
	 *
	 * @int[] Return quadrant YX agressor
	 */
	public int[] getQuadrantAgressor(){
		return actionField.getQuadrantAgressor();
	}

	/*
    Return arrays with quadrant on direction
     */
	public int [][] getQuadrantsOnDirection(int [] quadrant, Direction direction){

		int [][] result = new int[9][2];

		int currentY = quadrant[0];
		int currentX = quadrant[1];

		int idx = 0;

		if (direction == Direction.DOWN) {

//			for (int i = currentY; i <= 8 ; i++) {
//				result[idx] = new int [] {i,currentX};
//				idx++;
//			}
			for (int i = 0; i <= 8 ; i++) {
				if (i >= currentY) {
					result[idx] = new int [] {i,currentX};
				} else {
					result[idx] = new int [] {-1,-1};
				}
				idx++;
			}

		} else if (direction == Direction.UP) {

//			for (int i = currentY; i >= 0 ; i--) {
//				result[idx] = new int [] {i,currentX};
//				idx++;
//			}

			for (int i = 8; i >= 0 ; i--) {
				if (i <= currentY) {
					result[idx] = new int [] {i,currentX};
				} else {
					result[idx] = new int [] {-1,-1};
				}
				idx++;
			}

		} else if (direction == Direction.RIGHT) {

			for (int i = currentX; i <= 8; i++) {
				result[idx] = new int [] {currentY,i};
				idx++;
			}

		} else if (direction == Direction.LEFT) {

			for (int i = currentX; i >= 0; i--) {
				result[idx] = new int [] {currentY,i};
				idx++;
			}

		}

		return result;
	}

	/*
	Return arreys wits targets for tanks
	 */
	public Object [] getTaregetsObject(AbstractTank tank){

		Object [] result = new  Object [9];
		int idx = 0;

		if (tank.getBehavior() == Behavior.AGRESSOR){

			result[idx] = battleFieldObj[getHeadquarters()[0]] [getHeadquarters()[1]];
			idx++;

			result[idx] = actionField.getDefender();

		} else if (tank.getBehavior() == Behavior.DEFENDER){
			result[idx] = actionField.getAgressor();
			idx++;
		}

		return result;
	}

	/*
	Return object in quadrant
	 */
	public BattleFieldObjects getObjectInQuadrant(int[] quadrant){
		if (quadrant[0] < 0) {
			return null;
		}
		return battleFieldObj[quadrant[0]][quadrant[1]];
	}

}
	
	

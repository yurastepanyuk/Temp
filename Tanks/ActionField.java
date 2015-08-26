package day9.Tanks;

/* METHODS
 * 
 * ActionField	
 * 			* paintComponent(Graphics g) - 	Otrisovka polya boya: pole, pregradi, tank, bulle
 * 			* processInterception() - Proverka po tekus4im koordinatam bullet nali4iya pregradi in her quadrant
 * 			* getQuadrantXY (int y, int x) - get value of quadrant as String
 * 			* getQuadrantXY (int y, int x) - Vozvrzs4aet koordinates na4ala quadranta po vhodyas4im values quadranta
 *			* processFire(TanksBullet bullet) - upravlyaet prorisovuvaniem bullet
 *			* processMove(TanksTank tank) - prorisovka dvizheniya tank na 1 quadrant
 *			* processTurn(TanksTank tank) - prorisovka razvorota tank
 */

import day9.Tanks.battlefieldobjects.BattleFieldObjects;
import day9.Tanks.battlefieldobjects.Brick;
import day9.Tanks.battlefieldobjects.Eagle;
import day9.Tanks.battlefieldobjects.Empty;
import day9.Tanks.battlefieldobjects.Rock;
import day9.Tanks.commoninterface.BattleFieldObject;
import day9.Tanks.commoninterface.Tank;
import day9.Tanks.enums.*;
import day9.Tanks.enums.Action;
import day9.Tanks.tanksobject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Random;

public class ActionField extends JPanel {
	
	private BattleField battleField;
	private Tank 		defender;
	private Bullet 		bullet;
	private Tank[] 		tanksAF;
	private Tank 		agressor;
	private long 		timeCreateNewAgressor;
	private long 		timeCreateDefender;
	private String		strSelectTank;

	private JFrame 		frame;
    private JPanel	 	panelGameOptions;
	private JPanel	 	panelGameResult;

	private GameMode 	gameMode;

	private JRadioButton rbutton;
	private ButtonGroup  buttonGroupSelectTank;

 	public ActionField() throws Exception {

		frame = new JFrame("BATTLE FIELD, DAY 9");
		frame.setLocation(500, 100);
		frame.setMinimumSize(new Dimension(BattleField.BF_WIDTH + 10, BattleField.BF_HEIGHT + 22 + 14));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);

		inicialisationAF();

		gameMode = GameMode.OPTIONS;
		//gameMode = GameMode.GAME;

		panelGameOptions = creatingOptionsPanel();
		panelGameResult	 = creatingTotalPanel();

		switchingPanels(GameMode.OPTIONS);

//		Thread.sleep(6000);
//		switchingPanels(GameMode.GAME);
//		runTheGame();

	}

	private void switchingPanels(GameMode newGameMode){
		gameMode = newGameMode;

		//frame.removeAll();

		for (Component component : frame.getContentPane().getComponents()){
			frame.getContentPane().remove(component);

		}

//		for (Component component : frame.getComponents()){
//			frame.getContentPane().remove (component);
//		}

		if (gameMode == GameMode.GAME){
			frame.getContentPane().add(this);
		} else if (gameMode == GameMode.OPTIONS){
			frame.getContentPane().add(panelGameOptions);
		} else if (gameMode == GameMode.RESULT){
			frame.getContentPane().add(panelGameResult);
		}

		repaint();

		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();

		frame.pack();
		frame.setVisible(true);
	}

	/** Inicialisation objects of Action Field
	 */
	private void inicialisationAF(){
		battleField = new BattleField(this);

		defender = new T34(battleField, Behavior.DEFENDER);

		timeCreateNewAgressor = System.currentTimeMillis() + 1000*3;//+3 sec
		agressor    = getNewAgressor();

		defender.setTargetForTank();
		agressor.setTargetForTank();

		bullet = new Bullet();

		//frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);

		tanksAF = new AbstractTank[3];
		tanksAF[0] = new T34(getBattleField(), Behavior.DEFENDER);
		tanksAF[1] = new BT7(getBattleField(), Behavior.AGRESSOR);
		tanksAF[2] = new Tiger(getBattleField(), Behavior.AGRESSOR);

		try {
			printTanksInfo();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private JPanel creatingOptionsPanel() throws Exception{

		//String imageNameGame = "src\\day9\\Tanks\\images\\battletank.png";

		String imageNameGame = "src\\" +  this.getClass().getName().replace(".","\\").replace(getClass().getSimpleName(),"") + "images\\battletank.png";
		String imageT34		 = "src\\" +  this.getClass().getName().replace(".","\\").replace(getClass().getSimpleName(),"") + "images\\t34up.jpg";
		String imageBT7		 = "src\\" +  this.getClass().getName().replace(".","\\").replace(getClass().getSimpleName(),"") + "images\\bt7Up.jpg";
		String imageTiger	 = "src\\" +  this.getClass().getName().replace(".","\\").replace(getClass().getSimpleName(),"") + "images\\tigerUp.jpg";

		JPanel panel = new JPanel();

		panel.setBackground(Color.BLACK);

		panel.setLayout(new GridBagLayout());

		Icon iconGame 	 = new ImageIcon(imageNameGame);
		JLabel labelGame = new JLabel("", iconGame, JLabel.CENTER);
		panel.add(labelGame, new GridBagConstraints(0,0,3,1,0,0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));


		JLabel labelSpace = new JLabel();
		panel.add(labelSpace, new GridBagConstraints(1,1,3,1,0,0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

		JLabel labelSelected = new JLabel("Select the tank of agressor");
		Font fontLabel		 = new Font("Courier New", Font.PLAIN, 14);
		labelSelected.setFont(fontLabel);
		labelSelected.setForeground(Color.white);
		panel.add(labelSelected, new GridBagConstraints(1,2,3,1,0,0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

		JPanel panelTanks = new JPanel();
		panelTanks.setBackground(Color.BLACK);
		panelTanks.setForeground(Color.BLACK);
		panelTanks.setBorder(null);
		panelTanks.setLayout(new GridLayout(0, 3));
		panelTanks.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		Icon iconT34	= new ImageIcon(imageT34);
		Icon iconBT7	= new ImageIcon(imageBT7);
		Icon iconTiger	= new ImageIcon(imageTiger);

		buttonGroupSelectTank = new ButtonGroup();
		ActionListener rbListener = new RBListener();

		rbutton = new JRadioButton("T34",iconT34);
		rbutton.addActionListener(rbListener);
		rbutton.setActionCommand("T34");
		rbutton.setSelected(true);
		//rbutton.setBackground(Color.BLACK);
		rbutton.setContentAreaFilled(false);
		buttonGroupSelectTank.add(rbutton);
		panelTanks.add(rbutton);

		rbutton 	= new JRadioButton("BT7",iconBT7);
		rbutton.addActionListener(rbListener);
		rbutton.setActionCommand("BT7");
		rbutton.setContentAreaFilled(false);
		buttonGroupSelectTank.add(rbutton);
		panelTanks.add(rbutton);

		rbutton 	= new JRadioButton("Tiger",iconTiger);
		rbutton.addActionListener(rbListener);
		rbutton.setActionCommand("Tiger");
		rbutton.setContentAreaFilled(false);
		buttonGroupSelectTank.add(rbutton);
		panelTanks.add(rbutton);

		panel.add(panelTanks, new GridBagConstraints(1,3,3,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0),0,0));

		JButton buttonStart = new JButton("Start");
		buttonStart.setActionCommand("start");
		ActionListener buttonListener = new ButtonListener(this);
		buttonStart.addActionListener(buttonListener);

		panel.add(buttonStart, new GridBagConstraints(2,4,1,1,0,0,GridBagConstraints.CENTER,0, new Insets(0,0,0,0),0,0));

		return panel;

	}

	private JPanel creatingTotalPanel() throws Exception{

		String imageTotal 	 = "src\\" +  this.getClass().getName().replace(".","\\").replace(getClass().getSimpleName(),"") + "images\\total.png";
		String imageT34		 = "src\\" +  this.getClass().getName().replace(".","\\").replace(getClass().getSimpleName(),"") + "images\\t34up.jpg";
		String imageBT7		 = "src\\" +  this.getClass().getName().replace(".","\\").replace(getClass().getSimpleName(),"") + "images\\bt7Up.jpg";
		String imageTiger	 = "src\\" +  this.getClass().getName().replace(".","\\").replace(getClass().getSimpleName(),"") + "images\\tigerUp.jpg";

		JPanel panel = new JPanel();

		panel.setBackground(Color.BLACK);

		panel.setLayout(new GridBagLayout());

		Icon iconTotal 	 = new ImageIcon(imageTotal);
		JLabel labelTotal = new JLabel("", iconTotal, JLabel.CENTER);
		panel.add(labelTotal, new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));


		JLabel labelSpace = new JLabel();
		panel.add(labelSpace, new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

		JPanel panelTanks = new JPanel();
		panelTanks.setBackground(Color.BLACK);
		panelTanks.setForeground(Color.BLACK);
		panelTanks.setBorder(null);
		panelTanks.setLayout(new GridLayout(3, 0));
		panelTanks.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		Icon iconT34	= new ImageIcon(imageT34);
		Icon iconBT7	= new ImageIcon(imageBT7);
		Icon iconTiger	= new ImageIcon(imageTiger);

		JLabel label = new JLabel("",iconT34, JLabel.CENTER);
		label.setBorder(null);
		panelTanks.add(label);

		label 	= new JLabel("",iconBT7, JLabel.CENTER);
		label.setBorder(null);
		panelTanks.add(label);

		label 	= new JLabel("",iconTiger, JLabel.CENTER);
		label.setBorder(null);
		panelTanks.add(label);

		panel.add(panelTanks, new GridBagConstraints(0,2,1,1,0,0,GridBagConstraints.CENTER,0, new Insets(0,0,0,0),0,0));

		labelSpace = new JLabel();
		panel.add(labelSpace, new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

		JButton buttonStart = new JButton("Get options");
		buttonStart.setActionCommand("options");
		ActionListener buttonListener = new ButtonListener(this);
		buttonStart.addActionListener(buttonListener);

		panel.add(buttonStart, new GridBagConstraints(0,4,1,1,0,0,GridBagConstraints.CENTER,0, new Insets(0,0,0,0),0,0));

		return panel;

	}

	private class ButtonListener implements ActionListener {

		ActionField actionField;

		public ButtonListener(ActionField actionField) {
			this.actionField = actionField;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("start")) {
				inicialisationAF();
				switchingPanels(GameMode.GAME);

				Thread thr = new Thread(){
					@Override
					public void run(){
						runTheGame();
					}
				};
				thr.start();
			} else if (e.getActionCommand().equals("options")){
				switchingPanels(GameMode.OPTIONS);
			}

		}

	}

	private class RBListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			strSelectTank = e.getActionCommand();
			agressor 	  = getNewAgressor();
		}
	}

	private AbstractTank getNewAgressor() {

 		Random r = new Random();

 		int X = 0;

 		int idx = 0;

 		int i = 0;
 		while (i < 3) {
 			idx = r.nextInt(3);
 			i++;
 			//System.out.println("Random idx: " + idx);
		}

 		if (idx == 0) {
			X = 0;
		} else if (idx == 1){
			X = 4;
		} else if (idx == 2){
			X = 8;
		}

		AbstractTank agres = null;

		if (strSelectTank == null){
			agres = new Tiger(getBattleField(), Behavior.AGRESSOR);
		} else if (strSelectTank.equals("T34")){
			agres = new T34(getBattleField(), Behavior.AGRESSOR);
		} else if (strSelectTank.equals("BT7")){
			agres = new BT7(getBattleField(), Behavior.AGRESSOR);
		} else if (strSelectTank.equals("Tiger")){
			agres = new Tiger(getBattleField(), Behavior.AGRESSOR);
		}

		agres.setX_(X*64);
		agres.setY(0);
		agres.setTankDirection(Direction.DOWN);
		agres.setColor(Colors.ORANGE);
		agres.bullet = new Bullet();
		agres.setTarget(battleField.getQuadrantDefender());

		timeCreateNewAgressor = 0;

		return agres;
	}

	public void printTanksInfo() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{

 		for (Tank tank : tanksAF) {

 			System.out.println("Color of tank: "  + tank.getColor());
 			System.out.println("Speed os tank: " + tank.getSpeed() );
 			if (tank.getClass().getSimpleName().equals("Tiger")) {
 				Class c = tank.getClass();
 				Field field = c.getField("armor");
 				int Value = (int) field.get(tank);
 				System.out.println("Armor of tank: " + Value);
			}

 			tank.printTankInfo();

		}

	};

	public void runTheGame() {

		BattleFieldObjects headquartersObj = battleField.scanQuadrant(battleField.getHeadquarters()[0], battleField.getHeadquarters()[1]);

		while (gameMode == GameMode.GAME) {

			if (true) {
				if (!agressor.isDestroyed() && !defender.isDestroyed()) {
					int qtyShotOnTarget = defender.isSeeingTargets();
					if (qtyShotOnTarget != 0){
						for (int idxShot = 0; idxShot <= qtyShotOnTarget; idxShot++){
							try {
								processAction(Action.FIRE, defender);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					try {
						processAction(defender.setUp(), defender);
					} catch (Exception e) {
						e.printStackTrace();
					}
					qtyShotOnTarget = defender.isSeeingTargets();
					if (qtyShotOnTarget != 0){
						for (int idxShot = 0; idxShot <= qtyShotOnTarget; idxShot++){
							try {
								processAction(Action.FIRE, defender);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				if (!agressor.isDestroyed() && (!defender.isDestroyed() || !headquartersObj.isDestroyed())) {
//					int qtyShotOnTarget = agressor.isSeeingTargets();
//					if (qtyShotOnTarget != 0){
//						for (int idxShot = 0; idxShot <= qtyShotOnTarget; idxShot++){
//							try {
//								processAction(Action.FIRE, agressor);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//					}
					try {
						processAction(agressor.setUp(), agressor);
					} catch (Exception e) {
						e.printStackTrace();
					}
					int qtyShotOnTarget = agressor.isSeeingTargets();
					if (qtyShotOnTarget != 0){
						for (int idxShot = 0; idxShot <= qtyShotOnTarget; idxShot++){
							try {
								processAction(Action.FIRE, agressor);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}

			}

			BattleFieldObject headquartersObject =  battleField.scanQuadrant(battleField.getHeadquarters()[0], battleField.getHeadquarters()[1]);
			if(headquartersObject.isDestroyed() || headquartersObject instanceof Empty){
				switchingPanels(GameMode.RESULT);
			}

		}

	}

	private void processAction(Action a, Tank t) throws Exception {
		if (a == Action.MOVE) {
			processMove(t);
		} else if (a == Action.FIRE) {
			processTurn(t);
			processFire(t. fire());
		}
	}

	public AbstractTank getTank(){
		return (AbstractTank)defender;
	}

	public BattleField getBattleField(){
		return battleField;
	}

	public Bullet getBullet(){
		return bullet;
	}

	/* Method: paintComponent(Graphics g)
	 * Parameters:
	 * 		Graphics g
	 * Return value:
	 * 		void
	 * Use:
	 * 		Otrisovka polya boya
	 * Example:
	 *
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		//setBackground(Color.BLACK);

		if (gameMode == GameMode.GAME){
			battleField.draw(g);

			if (timeCreateNewAgressor != 0 && System.currentTimeMillis() >= timeCreateNewAgressor) {
				agressor = getNewAgressor();
			} else if (timeCreateDefender != 0 && System.currentTimeMillis() >= timeCreateDefender){
				timeCreateDefender = 0;
				defender = new T34(battleField, Behavior.DEFENDER);
			}

			defender.draw(g);

			agressor.draw(g);

			bullet.draw(g);
		}

	}



	/* Method: processFire(TanksBullet bullet)
	 * Parameters:
	 * 		TanksBullet bullet - bullet dvizhenie kotoroy mu prorisovuvaem
	 * Return value:
	 * 		not
	 * Use:
	 * 		upravlyaet prorisovuvaniem bullet
	 * Example:
	 * 		processFire(bullet)
	 */
	public void processFire(Bullet bullet) throws Exception {

		this.bullet = bullet;

		int count = 0;

		//Cikl obespechivait plavnoe dvizhenie bullet
		while (count < battleField.getBfWidth()/bullet.getSpeed()) {

			if (bullet.getDirection() == Direction.UP) {
				bullet.updateY((-1)*bullet.getSpeed());
			}else if (bullet.getDirection() == Direction.DOWN) {
				bullet.updateY(bullet.getSpeed());
			}else if (bullet.getDirection() == Direction.LEFT) {
				bullet.updateX((-1)*bullet.getSpeed());
			}else if (bullet.getDirection() == Direction.RIGHT) {
				bullet.updateX(bullet.getSpeed());
			}

			count++;

			if (processInterception()){
				bullet.destroy();
			}

			repaint();

			Thread.sleep(bullet.getSpeed());
		}

	}

	/** Method: processInterception()
	 * Parameters:
	 * 		not
	 * Return value:
	 * 		boolean - true otrisovka proshla, false - pulya ne v predelah polya boya
	 * Use:
	 * 		Proverka po tekus4im koordinatam bullet nali4iya pregradi in her quadrant
	 * 		If pregrada est, to clean pregradu, bullet ubiraem s polya boya
	 * Example:
	 * 		processInterception() Bullet bullet
	 */
	private boolean processInterception(){

		boolean result = false;

		//proveryaem tol'ko esli pulya v predelah boya
		String YX = "0_0";
		if ((bullet.getX() >= 0 && bullet.getX() < battleField.getBfHeigt()) && (bullet.getY() >= 0 && bullet.getY() < battleField.getBfWidth())) {
			YX = getQuadrant(bullet.getX(), bullet.getY());//"0_8"
		} else {
			return false;
		}

		int symv_ = YX.indexOf("_");
		int y = Integer.valueOf(YX.substring(0, symv_));
		int x = Integer.valueOf(YX.substring(symv_ + 1));

		//Po koordinatam quadrant bullet proveryaem 4to hranitsya in array battleField
		//String valQuadrant = actionField.battleField.getValueOfXY(X, Y);
		BattleFieldObjects valQuadrant = battleField.scanQuadrant(y, x);

		if (valQuadrant instanceof Empty) {
			//return false;
		}

		if (valQuadrant instanceof Brick || valQuadrant instanceof Eagle) {

			battleField.updateQuadrant(y, x, new Empty(x,y));

			return true;
		} else if (valQuadrant instanceof Rock){
			return true;
		}

		//Otrabotka popadaniya v tank
		if (bulletCrossesTank(bullet) == true){
			return true;
		}
		 
		return result;
		
	}
	
	/** Method: destroy(Bullet bullet, Tank agressor)
	 * Parameters:
	 * 		Bullet bullet   - bullet kotoraya popala v tank
	 * 		Tank tank		- tank v kotoruj popala bullet
	 * Return value:
	 * 		void
	 * Use:
	 * 		posle popadaniya v tank ih nuzhno ubrat s polya boya
	 * Example:
	 * 		destroy(tank.bullet, agressor)
	 */
	private void destroy(Bullet bullet, AbstractTank tank) {
		bullet.destroy();
		tank.destroy();
		if (tank instanceof Tiger){
			timeCreateNewAgressor = System.currentTimeMillis() + 1000*3;
		} else {
			timeCreateDefender = System.currentTimeMillis() + 1000*3;
		}

	}

	/* Method: bulletCrossesTank(Bullet bullet_) 
	 * Parameters:
	 * 		Bullet bullet_ - bullet kotoruyu proveryaem na perese4enie s tankami
	 * Return value:
	 * 		boolean
	 * Use:
	 * 		chek bullet na perese4enie s tankami
	 * Example:
	 * 		bulletCrossesTank(bullet)
	 */
	private boolean bulletCrossesTank(Bullet bullet_) {
		
		boolean result = false;
		
		if(bullet_ == null){
			return false;
		}
		
		int xBullet = bullet_.getX();
		int yBullet = bullet_.getY();

		//bullet_.tankParent.getTarget();
		AbstractTank currentAT = bullet_.getTankParent();
		Object [] targets = battleField.getTaregetsObject(currentAT);

		int yTankTarget = -100;
		int xTankTarget = -100;

		//int xTankDefender = defender.getX();
		//int yTankDefender = defender.getY();

		for (Object target : targets){

			if (!(target instanceof AbstractTank)) continue;

			yTankTarget = ((AbstractTank) target).getY();
			xTankTarget = ((AbstractTank) target).getX();

			byte hitX = 0;
			byte hitY = 0;

			if (((xBullet >= xTankTarget) && (xBullet <= xTankTarget+64)) || ((xBullet+14 >= xTankTarget) && (xBullet+14 <= xTankTarget+64))){
				hitX = 1;
			}

			if (((yBullet >= yTankTarget) && (yBullet <= yTankTarget+64)) || ((yBullet+14 >= yTankTarget) && (yBullet+14 <= yTankTarget+64))){
				hitY = 1;
			}


			if(hitX + hitY >= 2){
				if ((Tank)bullet_.getTankParent() == (Tank)target){
					result = false;
				} else {
					result = true;
				}

			}

			if (result == true){
				int armor = 0;
				if (target instanceof Tiger) {
					armor = ((Tiger) target).getArmor();
					((Tiger) target).setArmor(armor - 1);
				}

				if (armor == 0) {
					destroy(bullet_, (AbstractTank) target);
					if (currentAT.getBehavior() == Behavior.AGRESSOR) {
						currentAT.setTarget(battleField.getHeadquarters());
					} else{
						currentAT.setTarget(battleField.getQuadrantAgressor());
					}

				}
			}

			return result;

//
//			if (target instanceof AbstractTank || target instanceof day7.Tanks.BattleFieldObjects.Eagle){
//
//				int [] targetQuadrant = new int[2];
//
//				if (target instanceof AbstractTank){
//					targetQuadrant = ((AbstractTank) target).getYXnow();
//				} else if (target instanceof day7.Tanks.BattleFieldObjects.Eagle) {
//					targetQuadrant = ((BattleFieldObjects) target).getYXnow();
//				}
//
//				if (targetQuadrant[0] == quadrant[0] && targetQuadrant[1] == quadrant[1]) {
//					targetFind = 1;
//					qtyShots++;
//				}
//			}
//
//			if (bfObjectInQuadrant instanceof Brick){
//				qtyShots++;
//			}
//
//			if (targetFind == 1) {
//				return qtyShots;
//			}
//
		}

		return result;
	}

	/* Method: getQuadrant(int x, int y) 
	 * Parameters:
	 * 		int x - value of koordinates of Vertikal as integer
	 * 		int y - value of koordinates of Horizontal as integer
	 * Return value:
	 * 		String - first koordinates of quadrant
	 * Use:
	 * 		get value of quadrant as String
	 * 		Override metod for getQudrant(String v, String h)
	 * Example:
	 * 		getQudrant(60, 140) -> "3_0"
	 */
	public String getQuadrant(int x, int y){
				
		return "" + y/defender.step +  "_" + x/defender.step;
		
	}
	
	/* Method: getQuadrantXY (int y, int x) 
	 * Parameters:
	 * 		int y - value of quadrant Y
	 * 		int x - value of quadrant X
	 * Return value:
	 * 		int [] - koordinates na4ala quadranta
	 * Use:
	 * 		Vozvrzs4aet koordinates na4ala quadranta po vhodyas4im values quadranta
	 * Example:
	 * 		getQuadrantXY (1, 3) -> int [] {64, 192}
	 */
	public int [] getQuadrantXY (int y, int x) {
				
		int [] XY = new int [2];
		
		XY[0] = y*64;
		XY[1] = x*64;
		
		
		return XY;
	}

	/* Method: processTurn(TanksTank tank) 
	 * Parameters:
	 * 		TanksTank tank - tank kotoruj razvora4ivaem
	 * Return value:
	 * 		not
	 * Use:
	 * 		prorisovka razvorota tank
	 * Example:
	 * 		processTurn(tank)
	 */
	public void processTurn(AbstractTank tank) {
		
		this.defender = tank;

		repaint();
		
	}	
	
	/* Method: processMove(Tank tank)
	 * Parameters:
	 * 		TanksTank tank - tank kotoruj dolzhen peremestitsya na 1 quadrant
	 * Return value:
	 * 		not
	 * Use:
	 * 		prorisovka dvizheniya tank na 1 quadrant
	 * Example:
	 * 		processMove(tank)
	 */
	public void processMove(Tank tank) throws Exception {
		
		//this.defender = tank;

		processTurn(tank);
		
		int covered = 0;

		for (int i = 0; i < tank.getMovePath(); i++) {

			if (!tank.moznoSdelatShag(tank.getDirection())) {

				processFire(tank.fire());

				if (!tank.moznoSdelatShag(tank.getDirection())) {
					return;
				}

			}

			//Cikl obespechivait plavnoe dvizhenie tank
			while (covered < tank.step) {

				if (tank.getDirection() == Direction.getDirectoinOfId(4)) {
					// 4 right
					//tank.tankX += tank.shortStep;
					tank.updateX(tank.shortStep);

				} else if (tank.getDirection() == Direction.getDirectoinOfId(3)) {
					// 3 left
					//tank.tankX -= tank.shortStep;
					tank.updateX((-1) * tank.shortStep);

				} else if (tank.getDirection() == Direction.getDirectoinOfId(1)) {
					// 1 up
					//tank.tankY -= tank.shortStep;
					tank.updateY((-1) * tank.shortStep);

				} else if (tank.getDirection() == Direction.getDirectoinOfId(2)) {
					// 2 down
					//tank.tankY += tank.shortStep;
					tank.updateY(tank.shortStep);
				}

				covered++;

				repaint();

				Thread.sleep((int) (15 * (1 / ((double) tank.getSpeed() / 15))));
			}
		}			
		
	}

	private void processTurn(Tank tank) throws Exception {
		repaint();
	}

	/* Method: moveToQuadrant(int v, int h)
	 * Parameters:
	 * 		int v - value new quadrant on Vertikal(Y)
	 * 		int h - value new quadrant on Horizontal(X)
	 * Return value:
	 * 		void
	 * Use:
	 * 		Ras4et i upravlenie dvizheniem to new quadrant
	 * Example:
	 * 		moveToQuadrant(8, 1)
	 */
	public void moveToQuadrant(int y, int x, Tank tank) throws Exception {

		tank.setProcessMoveToQuadrant(y, x, true, null, false);

	}

	public int[] getQuadrantDefender(){
		if (defender.isDestroyed() != true){
			return defender.getYXnow();
		} else {
			return battleField.getHeadquarters();
		}

	}

	public int[] getQuadrantAgressor(){
		if (agressor != null && agressor.isDestroyed() != true){
			return agressor.getYXnow();
		} else {
			return new int []{8,2};
		}

	}


	public Tank getDefender(){
		return  defender.isDestroyed()?null:defender;
	}

	public Tank getAgressor(){
		return agressor;
	}
}

package day9.Tanks.tanksobject;

import day9.Tanks.ActionField;
import day9.Tanks.BattleField;
import day9.Tanks.enums.Action;
import day9.Tanks.enums.Behavior;
import day9.Tanks.enums.Colors;
import day9.Tanks.enums.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

public class Tiger extends AbstractTank {
	
	public int armor;
	public int step = 0;

	//private final static String IMAGE_NAME = "src\\day9\\Tanks\\Images\\tigerUp.jpg";
	private final static String IMAGE_NAME = "src\\" + ActionField.class.getName().replace(".","\\").replace(ActionField.class.getSimpleName(),"") + "images\\tigerUp.jpg";

	public Tiger(BattleField battleField, Behavior behavior) {
		super(battleField);
		setBehavior(behavior);
		super.setColor(Colors.RED);
		super.setSpeed(15);
		this.armor = 1;
		super.setColor_(new Color(255, 165, 0));

		super.actoins = new ArrayList<>();

		try{
			image 	  = ImageIO.read(new File(IMAGE_NAME));
			imageDraw = image;
		}catch (IOException e){
			System.err.println("Can't find image: " + IMAGE_NAME);
		}

		rotateImage(getDirection());
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	@Override
	public void printTankInfo() {
		System.out.println("Pereopredelenniy metod printTankInfo()");
		super.printTankInfo();
		System.out.println("ARMOR of tank: " + armor);
	}

	@Override
	public Action setUp() {
		if (actoins == null) {
			return Action.FIRE;
		}

		if (step >= actoins.size() || step >= 2) {
			step = 0;
			//setTarget(getBF().getQuadrantDefender());
			setTargetForTank();
			if (getTarget()!= null)	{
				setProcessMoveToQuadrant(getTarget()[0], getTarget()[1], true, null, false);
			}
		}
		if (!(actoins.get(step) instanceof Action)) {
			System.out.println("Tiger " + (Direction) actoins.get(step));
			turn((Direction) actoins.get(step++));
			rotateImage(getDirection());
		}
		if (step >= actoins.size()) {
			step = 0;
			if (getTarget()!= null)	{
				setProcessMoveToQuadrant(getTarget()[0], getTarget()[1], true, null,false);
			}
		}

		while (step < actoins.size() && !(actoins.get(step) instanceof Action)) {
			System.out.println("Tiger " + (Direction) actoins.get(step));
			turn((Direction) actoins.get(step++));
			rotateImage(getDirection());
		}

		if(step < actoins.size()){
			System.out.println("Tiger " + (actoins.get(step) instanceof Action ?(Action) actoins.get(step): Action.FIRE ) );

			return actoins.get(step) instanceof Action ?(Action) actoins.get(step++): Action.FIRE;
		}else{
			return Action.NONE;
		}

		//return (Action) actoins.get(step++);
	}

//	@Override
//	public void draw(Graphics g) {
//		if (!isDestroyed()) {
//			g.fillRect(getX(), getY(), 64, 64);
//			g.drawImage(iTigerdraw, getX(), getY(), new ImageObserver() {
//				@Override
//				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
//					return false;
//				}
//			});
//		}
//	}
//
//	public void rotateImage(Direction direction){
//
//		double degress = 0;
//
//		ImageIcon icon = new ImageIcon(this.iTiger);
//		BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
//		Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
//		if (direction == Direction.UP) {
//			degress = 0;
//		}else if (direction == Direction.RIGHT){
//			degress = 90;
//		}else if (direction == Direction.LEFT){
//			degress = -90;
//		}else if (direction == Direction.DOWN){
//			degress = 180;
//		}else {
//			return;
//		}
//		graphics2D.rotate(Math.toRadians(degress), icon.getIconWidth()/2, icon.getIconHeight()/2);
//		graphics2D.drawImage(this.iTiger,0,0,new ImageObserver() {
//			@Override
//			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
//				return false;
//			}
//		});
//		this.iTigerdraw = bufferedImage;
//	}

}

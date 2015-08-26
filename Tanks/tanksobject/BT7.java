package day9.Tanks.tanksobject;

import day9.Tanks.ActionField;
import day9.Tanks.BattleField;
import day9.Tanks.enums.Action;
import day9.Tanks.enums.Behavior;
import day9.Tanks.enums.Colors;
import day9.Tanks.enums.Direction;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.util.ArrayList;

public class BT7 extends AbstractTank {

	//private final static String IMAGE_NAME = "src\\day8\\Tanks\\Images\\bt7Up.jpg";
	private final static String IMAGE_NAME = "src\\" + ActionField.class.getName().replace(".","\\").replace(ActionField.class.getSimpleName(),"") + "images\\bt7Up.jpg";


	private int step = 0;
//	private Image iBt7;
//	private Image iBt7draw;
		
	public BT7(BattleField battleField, Behavior behavior){
		
		super(battleField);
		setBehavior(behavior);
		super.setColor(Colors.BLUE);
		speed *= 2;
		super.setColor_(new Color(255, 0, 0));
		movePath = 2;

		super.actoins = new ArrayList<>();

		setTargetForTank();

		try{
//			iBt7 	 = ImageIO.read(new File(IMAGE_NAME));
//			iBt7draw = iBt7;
			image 	  = ImageIO.read(new File(IMAGE_NAME));
			imageDraw = image;
		}catch (IOException e){
			System.err.println("Can't find image: " + IMAGE_NAME);
		}

		rotateImage(getDirection());

	}
	
	@Override
	public void printTankInfo() {
		System.out.println("Pereopredelenniy metod printTankInfo()");
		super.printTankInfo();
	}

	@Override
	public Action setUp() {

//		rotateImage(getDirection());
//		return Action.MOVE;
		if (actoins == null) {
			return Action.FIRE;
		}

		if (step >= actoins.size() || step >= 2) {
			step = 0;
			setTargetForTank();
		}
		if (!(actoins.get(step) instanceof Action)) {
			System.out.println("BT7 " + (Direction) actoins.get(step));
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
			System.out.println("BT7 " + (Direction) actoins.get(step));
			turn((Direction) actoins.get(step++));
			rotateImage(getDirection());
		}

		if(step < actoins.size()){
			System.out.println("BT7 " + (actoins.get(step) instanceof Action ?(Action) actoins.get(step): Action.FIRE ) );

			return actoins.get(step) instanceof Action ?(Action) actoins.get(step++): Action.FIRE;
		}else{
			return Action.NONE;
		}
	}

//	@Override
//	public void draw(Graphics g) {
//		if (!isDestroyed()) {
//			g.fillRect(getX(), getY(), 64, 64);
//			g.drawImage(iBt7, getX(), getY(), new ImageObserver() {
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
//		ImageIcon icon = new ImageIcon(this.iBt7);
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
//		graphics2D.drawImage(this.iBt7draw,0,0,new ImageObserver() {
//			@Override
//			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
//				return false;
//			}
//		});
//		this.iBt7draw = bufferedImage;
//	}

}

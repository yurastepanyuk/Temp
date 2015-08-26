package day9.Tanks.tanksobject;

import day9.Tanks.ActionField;
import day9.Tanks.BattleField;
import day9.Tanks.enums.Action;
import day9.Tanks.enums.Behavior;
import day9.Tanks.enums.Colors;
import day9.Tanks.enums.Direction;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class T34 extends AbstractTank {

	private int step = 0;

	//private final static String IMAGE_NAME = "src\\day9\\Tanks\\Images\\t34up.jpg";
	private final static String IMAGE_NAME = "src\\" + ActionField.class.getName().replace(".","\\").replace(ActionField.class.getSimpleName(),"") + "images\\t34up.jpg";


	public T34(BattleField battleField, Behavior behavior) {
		super(battleField);
		super.setBehavior(behavior);
		super.setColor(Colors.YELLOW);
		//super.setSpeed(50);
        super.setColor_(new Color(90, 0, 90));

		super.actoins = new ArrayList<>();

		setTargetForTank();

		try{
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
//		if (actoins == null) {
//			return Action.NONE;
//		}
//		if (step >= actoins.size()) {
//			step = 0;
//			if (getTarget()!= null)	{
//				setProcessMoveToQuadrant(getTarget()[0], getTarget()[1], true, null, false);
//			}
//		}
//		if (!(actoins.get(step) instanceof Action)) {
//			System.out.println("T34 " + (Direction) actoins.get(step));
//			turn((Direction) actoins.get(step++));
//			rotateImage(getDirection());
//		}
//		if (step >= actoins.size()) {
//			step = 0;
//			if (getTarget()!= null)	{
//				setProcessMoveToQuadrant(getTarget()[0], getTarget()[1], true, null, false);
//			}
//		}
//
//		while (step < actoins.size() && !(actoins.get(step) instanceof Action)) {
//			System.out.println("T34 " + (Direction) actoins.get(step));
//			turn((Direction) actoins.get(step++));
//			rotateImage(getDirection());
//		}
//
//		if(step < actoins.size()){
//			System.out.println("T34 " + (actoins.get(step) instanceof Action ?(Action) actoins.get(step): Action.FIRE ) );
//
//			return actoins.get(step) instanceof Action ?(Action) actoins.get(step++): Action.FIRE;
//		}else{
//			return Action.NONE;
//		}
		//return (Action) actoins.get(step++);

		if (actoins == null) {
			return Action.FIRE;
		}

		if (step >= actoins.size() || step >= 2) {
			step = 0;
			setTargetForTank();
		}
		if (!(actoins.get(step) instanceof Action)) {
			System.out.println("T34 " + (Direction) actoins.get(step));
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
			System.out.println("T34 " + (Direction) actoins.get(step));
			turn((Direction) actoins.get(step++));
			rotateImage(getDirection());
		}

		if(step < actoins.size()){
			System.out.println("T34 " + (actoins.get(step) instanceof Action ?(Action) actoins.get(step): Action.FIRE ) );

			return actoins.get(step) instanceof Action ?(Action) actoins.get(step++): Action.FIRE;
		}else{
			return Action.NONE;
		}
	}

}

package day9.Tanks.commoninterface;

import day9.Tanks.enums.Action;
import day9.Tanks.enums.Colors;
import day9.Tanks.enums.Direction;
import day9.Tanks.tanksobject.Bullet;

import java.util.List;

public interface Tank extends Drawable, Destroyable {

	public static final int step = 64;
	public static int shortStep = 1;
	
	public Action setUp();

	//public void move() throws Exception;

	public Bullet fire() throws Exception;

	public int getX();

	public int getY();
	
	public Direction getDirection();
	
	public void updateX(int x);

	public void updateY(int y);
	
	public int getSpeed();
	
	public int getMovePath();

	public boolean moznoSdelatShag(Direction direction);

	public void turn(Direction direction);

	public Colors getColor();

	public void printTankInfo();

	public int [] getYXnow();

	public Direction getNapravlenieY(int newY, int yOld);

	public Direction getNapravlenieX(int newX, int xOld);

	public int getCountQuadrantY(int newY, int yOld);

	public int getCountQuadrantX(int newX, int xOld);

	public List<Object> setProcessMoveToQuadrant(int x, int y, boolean set, int[] otherStartQuadrant, boolean firstX);

	public void setTarget(int[] target);

	public int isSeeingTargets();

	public void setTargetForTank();
}

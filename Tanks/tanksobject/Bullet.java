package day9.Tanks.tanksobject;

/*METHODS
 * 
 * Bullet
 * 			* destroy()				- udalyaet bullet s polya boya
 * 			* updateY()				- peremes4aet pulyu po Y na veli4inu addY
 * 			* updateX()				- peremes4aet pulyu po X na veli4inu addX
 */

import day9.Tanks.commoninterface.Destroyable;
import day9.Tanks.commoninterface.Drawable;
import day9.Tanks.enums.Direction;

import java.awt.*;

public class Bullet implements Drawable, Destroyable {
	
	private int X = -100;
	private int Y = -100;
	private int speed = 5;
	private Direction direction;
	private AbstractTank tankParent;

	private boolean destroyed;

	public Bullet() {

		this(-100, -100, 1, Direction.UP);

	}

	public Bullet(int x, int y, int speed, Direction direction) {
		this.X = x;
		this.Y = y;
		this.speed = speed;
		this.direction = direction;
	}

	/* Method: TanksBullet(int X, int Y, TanksActionField actionField)
	 * Parameters:
	 * 		not
	 * Return value:
	 * 		not
	 * Use:
	 * 		Konstruktor klassa. Inicialisiruet pulyu vozle tanka
	 * Example:
	 * 		new TanksBullet(tankX + 25, tankY + 25,  actionField);
	 */
	public Bullet(int X, int Y, Direction direction) {

		this.X = X;
		this.Y = Y;
		this.direction = direction;

	}

	public Direction getDirection() {
		return direction;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getSpeed() {
		return speed;
	}

	/* Method: updateX(int addX)
	 * Parameters:
	 * 		not
	 * Return value:
	 * 		not
	 * Use:
	 * 		peremes4aet pulyu po X na veli4inu addX
	 * Example:
	 * 		updateX(bullet.getSpeed())
	 */
	public void updateX(int addX){
		X += addX;
	}

	/* Method: updateY(int addY)
	 * Parameters:
	 * 		not
	 * Return value:
	 * 		not
	 * Use:
	 * 		peremes4aet pulyu po Y na veli4inu addY
	 * Example:
	 * 		updateY(bullet.getSpeed())
	 */
	public void updateY(int addY){
		Y += addY;
	}

	/* Method: destroy()
	 * Parameters:
	 * 		not
	 * Return value:
	 * 		not
	 * Use:
	 * 		udalyaet bullet s polya boya
	 * Example:
	 * 		destroy()
	 */
	public void destroy(){
		X = -100;
		Y = -100;
		destroyed = true;
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public void draw(Graphics g) {
		//Prorisivka puli
		g.setColor(new Color(255, 255, 0));
		g.fillRect(getX(), getY(), 14, 14);
	}

	public void setTankParent(AbstractTank tankParent) {
		this.tankParent = tankParent;
	}

	public AbstractTank getTankParent() {
		return tankParent;
	}
}

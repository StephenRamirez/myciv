import java.awt.Point;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Unit
{
	protected Point cords;
	protected int health;

	public Unit(int x, int y, int health)
	{
		cords = new Point(x,y);
		this.health = health;
	}

	public abstract void step();

	public abstract void draw(Graphics2D g);

	public abstract boolean inBounds(int x, int y);

	public int getHealth()
	{
		return health;
	}

	public void changeHealth(int hx)
	{
		health += hx;
	}
}
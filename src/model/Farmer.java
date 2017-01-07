import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.Random;

public class Farmer extends Unit
{
	private int radius;

	public Farmer(int x, int y, int health)
	{
		super(x,y,health);

		radius = 10;
	}

	public boolean inBounds(int x, int y)
	{
		double distance = Math.sqrt(Math.pow((cords.x - x),2) + Math.pow((cords.y - y),2));

		if(distance >= (double)radius)
			return true;
		return false;
	}

	public void draw(Graphics2D g)
	{
		g.setPaint(Color.YELLOW);
		g.fillOval(cords.x,cords.y,radius,radius);
	}

	public void move(int dx, int dy)
	{
		cords.x += dx;
		cords.y += dy;
	}

	public void step()
	{
		Random rand = new Random();

		if(cords.x == 0)
			move(1,0);
		else if(cords.y == 0)
			move(0,1);
		else if(cords.x == World.BOUNDS)
			move(-1,0);
		else if(cords.y == World.BOUNDS)
			move(0,-1);
		else
			move(rand.nextInt(2),rand.nextInt(2));
	}
}
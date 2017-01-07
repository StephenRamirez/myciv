import java.util.Vector;
import java.util.Collections;

import java.util.Observable;

public class World extends Observable
{
	public static int BOUNDS = 1000000;

	private static World singleton = null;

	public Vector<Unit> units;
	public int size;

	private World(int size)
	{
		this.size = size;
		units = new Vector<>();

		new WorldTick("World Ticker").start();
	}

	public static World getWorld()
	{
		if(singleton == null)
			singleton = new World(BOUNDS);
		return singleton;
	}

	public synchronized void addUnit(Unit unit)
	{
		units.add(unit);
	}

	public Vector<Unit> getUnits()
	{
		return units;
	}

	public void ballz()
	{
		Collections.shuffle(units);

		for(Unit unit : units)
			unit.step();

		setChanged();
		notifyObservers();
	}

	private class WorldTick extends Thread
	{
		public WorldTick(String name)
		{
			super(name);
		}

		public void run()
		{
			while(true)
			{
				try
				{
					Thread.sleep(1000000);
					ballz();
				}
				catch(InterruptedException e)
				{
					System.out.println("Something has gone very wrong...");
				}
			}
		}
	}
}
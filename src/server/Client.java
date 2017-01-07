import java.net.Socket;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.util.Vector;

import client.MyCivGui;

import model.Unit;

public class Client
{
	private Socket server;
	private ObjectInputStream is;
	private ObjectOutputStream os;

	public Client() throws IOException
	{
		server = new Socket("localhost",Server.PORT_NUM);
		is = new ObjectInputStream(server.getInputStream());
		os = new ObjectOutputStream(server.getOutputStream());

		new ServerInputCatcher("Server Input Reciever").start();
	}

	public void kill() throws IOException
	{
		is.close();
		os.close();
		server.close();
	}

	public void sendUnit(Unit unit) throws IOException
	{
		os.writeObject(unit);
	}

	public Vector<Unit> getUnits()
	{
		try
		{
			return (Vector<Unit>)is.readObject();
		}
		catch(ClassNotFoundException |IOException e)
		{
			System.out.println("Server Connection Lost...");
		}
	}
}
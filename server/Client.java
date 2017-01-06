import java.net.Socket;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

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

	private class ServerInputCatcher extends Thread
	{
		public ServerInputCatcher(String name)
		{
			super(name);
		}

		public void run()
		{
			while(true)
			{
				try
				{
					String test = (String)is.readObject();
					System.out.println(test);
				}
				catch(ClassNotFoundException |IOException e)
				{
					System.out.println("Server Connection Lost...");
					break;
				}
			}
		}
	}
}
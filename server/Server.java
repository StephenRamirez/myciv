import java.util.ArrayList;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.util.Scanner;

public class Server
{
	private ServerSocket server;

	private ArrayList<Socket> clients;
	private ArrayList<ObjectOutputStream> outputConnections;
	private ArrayList<ObjectInputStream> inputConnections;

	public static final int PORT_NUM = 8000;

	public Server() throws UnknownHostException, IOException
	{
		server = new ServerSocket(PORT_NUM);
		clients = new ArrayList<>();
		outputConnections = new ArrayList<>();
		inputConnections = new ArrayList<>();
		new ClientAcceptor("Client Acceptor").start();
	}

	private synchronized void killClient(Socket client) throws IOException
	{
		for(int i = 0; i < clients.size(); i++)
		{
			if(client == clients.get(i))
			{
				outputConnections.remove(i).close();
				inputConnections.remove(i).close();
				clients.remove(i).close();
				break;
			}
		}
	}

	public void kill() throws IOException
	{
		for(ObjectOutputStream os : outputConnections)
			os.close();

		for(ObjectInputStream is : inputConnections)
			is.close();

		for(Socket client : clients)
			client.close();

		server.close();
	}

	private class ClientInputCatcher extends Thread
	{
		private Socket player;
		private ObjectInputStream is;

		public ClientInputCatcher(String name, Socket player, ObjectInputStream is)
		{
			super(name);
			this.player = player;
			this.is = is;
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
					try{killClient(player);}
					catch(IOException f){System.out.println("Error Player Socket Unable To Close...");}
					finally{break;}
				}
			}
		}
	}

	private class ClientAcceptor extends Thread
	{
		public ClientAcceptor(String name)
		{
			super(name);
		}

		public void run()
		{
			while(true)
			{
				try
				{
					Socket player = server.accept();
					clients.add(player);
					outputConnections.add(new ObjectOutputStream(player.getOutputStream()));
					ObjectInputStream is = new ObjectInputStream(player.getInputStream());
					inputConnections.add(is);

					new ClientInputCatcher("Client Input Catcher",player,is);
					System.out.println("Player Connected...");
				}
				catch(IOException e)
				{
					System.out.println("Server Connection Closed...");
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		Server host = new Server();
		System.out.println("Server Online...");

		Scanner scan = new Scanner(System.in);
		String line = "startup";

		while(!line.equals("end"))
		{
			System.out.print(":>");
			line = scan.nextLine();
		}

		host.kill();
	}
}
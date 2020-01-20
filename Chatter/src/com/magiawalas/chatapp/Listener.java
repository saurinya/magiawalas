package com.magiawalas.chatapp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * This class will open an server socket and listen for incomings connections
 * This will be the main class also.
 * @author HZFBS7
 *
 */
		
public class Listener 
{

	public static void main (String [] args)
	{
		if (args.length <= 0)
		{
			System.err.println("You must provide the port on which you want to listen!");
			System.exit(-1);
		}
		
		String portValue = args[0];
		int intPortValue = -1;
		
		// Lets convert to integer..
		try
		{
			intPortValue = Integer.parseInt(portValue);
			// thats great I got the port..
		}
		catch (Throwable e)
		{
			System.err.println("Stupid , I got an exception .. what kind of a value did you provide.. IT SHOULD BE NUMBER:- " + portValue + " this is the exception i got : " + e.getMessage()); 
		}
		
		try 
		{
			byte [] byteAddress = {0,0,0,0};
			InetAddress address = InetAddress.getByAddress(byteAddress);
			ServerSocket ss = new ServerSocket(intPortValue,100,address);
			
			while (true)
			{
				System.out.println("Going into the Acceptance Mode");
				Socket s = ss.accept();		// This is listening..
				System.out.println("Just came out of the Mode...");
				// Okay create a new Communicator;
				Communicator c = new Communicator(s);
				
				// Now create a new Thread
				Thread myThread = new Thread(c);
				myThread.start();
			}
		} 
		catch (Throwable e) 
		{
			System.err.println("The listening program is shutting down... got an exception.." + e.getMessage());
		}
		
	}
}

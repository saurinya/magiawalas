package com.magiawalas.chatapp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Connector 
{

	public static void main (String [] args)
	{
		if (args.length < 2)
		{
			System.err.println("You must provide the address and port on which you want to listen!");
			System.exit(-1);
		}
		
		String portValue = args[1];
		String address = args[0];
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
		
		System.out.println("Trying to connect to.." + address + " on port: " + intPortValue);
		
		try 
		{
			Socket s = new Socket(InetAddress.getByName(address), intPortValue);
			Communicator c = new Communicator(s);
			c.run();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

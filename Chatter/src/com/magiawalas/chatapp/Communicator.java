package com.magiawalas.chatapp;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Communicator implements Runnable 
{
	Socket s = null;
	
	public Communicator (Socket s)
	{
		this.s = s;
	}
	
	@Override
	public void run() 
	{
		try 
		{
			// Console cs = System.console();
			// Get the Input Stream from the socket..
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			
			InputStreamReader keyboard   = new InputStreamReader(System.in);
			InputStreamReader sockerBoad = new InputStreamReader(is);
			
			OutputStreamWriter socketWriter = new OutputStreamWriter(os);
			
			while (true)
			{
			
				// check if a character is available on keyboard..
				if (keyboard.ready())
				{
					// while (keyboard.ready())
					{
						// System.out.print("K");
						char ch = (char) keyboard.read();
						// System.out.print(ch);
						socketWriter.write((int) ch);
						socketWriter.flush();
					}
				}
			  
			    if (sockerBoad.ready())
			    {
			    	// while (sockerBoad.ready())
			    	{
			    		// System.out.print("S");
						char ch = (char) sockerBoad.read();
						System.out.print(ch);
			    	}
			    }
			}
		} 
		catch (Throwable e) 
		{
			System.out.println("Something happened, I am stopping work.. connection must be closed..");
		}
		finally 
		{
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

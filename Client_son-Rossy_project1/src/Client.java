
import java.io.*;
import java.util.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Client{
	
	public static void main(String[] args)throws Exception 
	{
		
		myClient();
	}
		
	
	   
	public static void myClient()throws Exception 
	{
		//InetAddress addr = InetAddress.getByName("localhost");
		
		
		Socket socket1 = new Socket("localhost",9999);
		String MessageOut;
		
		//to read from the keyboard
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		//to get input from server
		BufferedReader in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
		
		//Out to server and enable flush
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream())),true);
		
        
		System.out.println(in.readLine());
		while(true)
		{
			MessageOut=reader.readLine();
			if(MessageOut!=null)
			{
			
				//System.out.println(reader.readLine());
				out.println(MessageOut);
				System.out.println(in.readLine());
				if(MessageOut.equals("end"))
				{
					break;
				}
			}
		}
		
		
		socket1.close();
			

		
	  
	}
}

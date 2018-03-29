import java.io.*;
import java.util.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server extends Thread{
	static Socket socket;
	
public Server(Socket socket)
{
	this.socket=socket;
}
	   

public static void myServer(Socket socket)throws Exception
{
	System.out.println("server has started");
	
	
	String MessageOut, MessageIn="";
	
	//a command
	String command="";
	String name="";
	String number="";
	String store="store";
	String get="get";
	String remove="remove";
	int i=0;
	
	//to read and to forward computation
	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	
	//this an array list to store numbers and names
	List<String>myList=new ArrayList<String>();
	

	
	out.println("hello Client: Type STORE name follow by Number "
			+ "or GET name "
			+ "or REMOVE name "
			+ "otherwise type END to end communication "); 
	
	while(true)
	{
		MessageIn=in.readLine();//to read the line the user typed
		int count=wordCount(MessageIn);// to count the word
		//any time the user types end then program communication is over
		if(MessageIn.equals("end"))
		{
			out.println("bye communication is over");
			break;
		}
		
		if(MessageIn.length()!=0)
		{
			//if there is a message then split the message to read the command, the number and the name
			//and assign the message to each appropriate variable
			
			if(count==3)//if message is 3 words then it must be a store a name and a number so we split them
			{
			 String[] Split=MessageIn.split(" ",3);
			command=Split[0];
			name=Split[1];
			number=Split[2];
			//check to see if user's first word was STORE
			if(Objects.equals(command, store))
			{
				//if it's store then we store
				myList.add(name);
				myList.add(number);
				out.println("stored");
                System.out.println("name has been stored");
			}
			
			}
			else if(count==2)//if message is 2 words then it must be a a get or a remove with a name so we split them in two message
			{
				
				String[] Split=MessageIn.split(" ",2);
				command=Split[0];
				name=Split[1];
				
				
				if(Objects.equals(command, get))//if command is get then we go in the loop
				{
					i=0;
					if(!myList.isEmpty())//check to see if we have a list
					{
					
						while(!Objects.equals(myList.get(i),name))
						{
							
							//if we get at the last item and didn't find it then we let them know
							if(Objects.equals( myList.get(i),myList.get(myList.size() - 1)))
							{
								out.println("no nome in file");
								break;
							}
							i++;
						}
						if(Objects.equals(myList.get(i),(name)))
						{
							out.println(myList.get(i+1));//when break out of the loop we check again if it's really the name so print out the number
							command="";
						}
					}
					else
					{
						out.println("no nome in file");
					}
				}
				else if(Objects.equals(command, remove))//if message is remove then we look for the name
				{
					i=0;
					if(!myList.isEmpty())
					{
					
					while(!Objects.equals(myList.get(i),name))
					{
						//if we get at the last item and didn't find it then we let them know
						if(Objects.equals( myList.get(i),myList.get(myList.size() - 1)))
						{
							out.println("no nome in file");
							break;
						}
						i++;
					}
					if(Objects.equals(myList.get(i),(name)))//after we break out the loop we check if it was the name then
					{
						String Name=myList.get(i);
						String Number=myList.get(i+1);
						//we remove name and the number
						myList.remove(Name);
						myList.remove(Number);
						out.println("name remove");
						command="";
					}
					}
					else 
					{
						out.println("no nome in file");
					}
				}
				else
					//if the command did not match either get or remove then we let the user know that was wrong input
				{
					out.println("wrong input please tye GET name or REMOVE name");
				}
				
				
			}
			else//if user put any thing else it's a wrong input
			{
				out.println("wrong command try again");
			}
					
			
			
		}
	}
	
	//shut down server and socket
	//socket.close();
	 

}
//a method to count how many word that the user typed
public static int wordCount(String message)
{
	message=message.trim();
	if(message.isEmpty())
	{
	return 0;
	}
	return message.split("\\s+").length;
}
public void run()
{
	try {
		myServer(socket);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}


import java.net.ServerSocket;
import java.net.Socket;

public class BigServer {
	// The port number on which the server will be listening
	  private static int port = 9999;
	  // The server socket.
	  private static ServerSocket listener = null;
	  // The client socket.
	   private static Socket clientSocket = null;
	   
	public static void main(String[] args)throws Exception {
		listener=new ServerSocket(port);
		System.out.println("waiting for connetion");
		int i=1;
		
		while(true)
		{
		clientSocket=listener.accept();
		new Server(clientSocket).start();
		String hostName = clientSocket.getInetAddress().getHostName();
		System.out.println("server connected to "+hostName+i);
		i++;
		if(clientSocket.isClosed())
		{
			System.out.println("close "+i);
			//i--;
		}
		if(i<1)
		{
			break;
		}
		}
		

	   
	}

}

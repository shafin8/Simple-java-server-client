import java.net.*;
import java.io.*;
public class Server {

	//initialize socket and input stream
			private Socket socket = null;
			private ServerSocket server = null;
			private DataInputStream in = null;
			
			private DataInputStream input = null;
			private DataOutputStream out = null;
	
	// constructor with port
	public Server(int port)
			{
			// starts server and waits for a connection
			try
					{
					server = new ServerSocket(port);
					System.out.println("Server started");
					System.out.println("Waiting for a client ...");
					socket = server.accept();
					System.out.println("Client accepted");
					
					// takes input from terminal
					input = new DataInputStream(System.in);
					// sends output to the socket
					out = new DataOutputStream(socket.getOutputStream());
					
			
					
					// takes input from the client socket
					in = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
					String line = "";
					String toClientMessage = "",q = null,p= null;
					// reads message from client until "Over" is sent
					
					while(true) {
						
						
						try
							{
							line = in.readUTF();
							System.out.println("Client: "+line);
							if(line.equalsIgnoreCase("exit")) {
								System.out.println("disconnecting from client");
								break;
							}
							toClientMessage=input.readLine();
							out.writeUTF(toClientMessage);}
							catch(IOException i){System.out.println(i);
							}
						
						 if(toClientMessage.equalsIgnoreCase("exit")) {
							System.out.println("disconnecting from server");
							break;
						}
						
					}
					
					
				
					System.out.println("Closing connection");
					input.close();
					out.close();
					socket.close();
					in.close();
					}
			catch(IOException i)
			{
			System.out.println(i);
			}
			}
	public static void main(String args[])
	{
		Server server = new Server(5000);
	}
	}
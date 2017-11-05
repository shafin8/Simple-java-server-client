import java.net.*;
import java.io.*;
public class Client {

	// initialize socket and input output streams
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream out = null;
	private DataInputStream in = null;

	
	// constructor to put ip address and port
	public Client(String address, int port)
					{
					// establish a connection
					try
							{
							socket = new Socket(address, port);
							System.out.println("Connected");
							// takes input from terminal
							input = new DataInputStream(System.in);
							// sends output to the socket
							out = new DataOutputStream(socket.getOutputStream());
							
							in = new DataInputStream(
									new BufferedInputStream(socket.getInputStream()));
							}
					catch(UnknownHostException u)
							{
									System.out.println(u);
							}
					catch(IOException i)
							{
									System.out.println(i);
							}
					// string to read message from input
					String toServerMessage = "";
					String line = "";
					
					
		while(true) {
					try
					{
					toServerMessage=input.readLine();
					out.writeUTF(toServerMessage);
					if(toServerMessage.equalsIgnoreCase("exit")) {
						System.out.println("disconnecting from client");
						break;
					}
					line = in.readUTF();
					System.out.println("Server: "+line);
					if(line.equalsIgnoreCase("exit")) {
						System.out.println("disconnecting from server");
						break;
					}
					}catch(IOException i){System.out.println(i);}
					
					}
					
				
		// close the connection
		try
				{
			System.out.println("Closing connection");
				input.close();
				out.close();
				socket.close();
				}
		catch(IOException i)
		{
				System.out.println(i);
		}
		
					}
	public static void main(String args[])
	{
			Client client = new Client("127.0.0.1", 5000);
	}
	}
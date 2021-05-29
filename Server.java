// Java program to illustrate Server side
// Implementation using DatagramSocket
import java.io.IOException; //TODO: delete this 
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.*;

import jdk.jshell.execution.Util;

import java.util.Scanner;



public class Server
{
	public static void main(String[] args) throws IOException
	{
		//TODO: Add code to parse server.conf
		//TODO: add code to create ledger

		

		int server_port  = 	1234;

		// Step 1 : Create a socket to listen at port 1234
		DatagramSocket ds = new DatagramSocket(server_port);
		byte[] receive = new byte[65535];

		DatagramPacket DpReceive = null;
		while (true)
		{

			// Step 2 : create a DatagramPacket to receive the data.
			DpReceive = new DatagramPacket(receive, receive.length);

			// Step 3 : revieve the data in byte buffer.
			ds.receive(DpReceive);

			InetAddress remote_IP = DpReceive.getAddress();
			SocketAddress remote_socket_address = DpReceive.getSocketAddress();
			int remote_port = DpReceive.getPort(); 
			String[] client_msg = Utility.data(receive).toString().split(" ");  

			System.out.println("Client says: " + client_msg);
			System.out.println("Client IP: " + remote_IP.toString().replace("/",""));
			System.out.println("Client Socket Address: " + remote_socket_address.toString().replace("/", ""));
			System.out.println("Client Port: " + remote_port); 

			//String res = Utility.getCommand(Utility.data(receive).toString());
			Utility.sendResponse(remote_IP, remote_port, 200, "OK"); //For testing 

			//TODO: Parse commands and identify them that way. Take string from client and seperate them by space into list
			
			System.out.println("The size of the message array is: " + client_msg.length);
			System.out.println("The array is: " + Arrays.toString(client_msg));


			//TODO: Check array for spaces. If it contains even one, send a 501 - Syntax error in paarameters msg
			//TODO: Check for valid command (elem 0 of client_msg) if no valid command send 500-command unrecognized 

			//TODO: IF else statements to call the appropriate commaands function 






			// Clear the buffer after every message.
			receive = new byte[65535];
	
			// Exit the server if the client sends "bye"
			if (Utility.data(receive).toString().equals("BYE"))
			{
				System.out.println("Client sent bye.....EXITING");
				// Clear the buffer after every message.
				receive = new byte[65535];
				break;
			}
		}
	}


}
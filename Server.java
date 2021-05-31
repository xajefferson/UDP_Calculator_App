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
		Ledger l = new Ledger();
		


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

			System.out.println("Request Starting...");
			System.out.println("Client IP: " + remote_IP.toString().replace("/",""));
			System.out.println("Client Port: " + remote_port); 
			System.out.println("Client Socket Address: " + remote_socket_address.toString().replace("/", ""));
						
			System.out.println("The size of the message array is: " + client_msg.length);
			System.out.println("The client says: " + Arrays.toString(client_msg));



			//To check if anything was sent
			if(client_msg.length == 0){
				System.out.println("Empty Request");
				Utility.sendResponse(remote_IP, remote_port, 500, "Syntax Error, empty string");
				receive = new byte[65535];
				System.out.println("Client request complete 0\n\n-----------------------------------------------------------------------------\n\n");
				continue;
			}


			//To check client message spacing
			if(Utility.check_array_for_spaces(client_msg)){
				System.out.println("Invalid Spacing");
				Utility.sendResponse(remote_IP, remote_port, 501, "Syntax error, invalid spacing.");
				// Clear the buffer after every message.
				receive = new byte[65535];
				System.out.println("Client request complete 1\n\n-----------------------------------------------------------------------------\n\n");
				continue;
			} 

			//To check if too many arguments were sent
			if(client_msg.length > 3){
				System.out.println("Too many arguments");
				Utility.sendResponse(remote_IP, remote_port, 501, "Syntax error, too many arguments");
				receive = new byte[65535];
				System.out.println("Client request complete 2\n\n-----------------------------------------------------------------------------\n\n");
				continue;
			}

			//To check if client sent valid command

			String validatedString = Utility.getCommand(client_msg);
			System.out.println("Validated string was: " + validatedString);

			if (validatedString.equals("command unrecognized")){
				System.out.println("Command unrecognized");
				Utility.sendResponse(remote_IP, remote_port, 500, "Syntax Error, command unrecognized");
				receive = new byte[65535];
				System.out.println("Client request complete 3\n\n-----------------------------------------------------------------------------\n\n");
				continue;
			}

			//If a client that has not sent helo tries to issue a command that is not helo
			if(!(l.socketAddrExists(remote_socket_address)) && !(validatedString.equals("HELO"))){
				//(~p/\~q)
				System.out.println("Client did not send HELO first");
				Utility.sendResponse(remote_IP, remote_port, 503, "Bad sequence of commands, send HELO first");
				receive = new byte[65535];
				System.out.println("Client request complete 4\n\n-----------------------------------------------------------------------------\n\n");
				continue;
			}



			if(validatedString.equals("HELO")){
				System.out.println("Entering HELO function...\n");
				Commands.helo(remote_IP, remote_port, remote_socket_address, l);
			}else if (validatedString.equals("HELP")){
				System.out.println("Entering HELP function...\n");
				Commands.help(l.getClientFromLedger(remote_socket_address), l);
			}else if (validatedString.equals("CIRCLE")){
				System.out.println("Entering CIRCLE function...\n");
				Commands.circle(client_msg, l.getClientFromLedger(remote_socket_address), l);
			}else if (validatedString.equals("SPHERE")){
				System.out.println("Entering SPHERE function...\n");
				Commands.sphere(client_msg, l.getClientFromLedger(remote_socket_address),l);
			}else if (validatedString.equals("CYLINDER")){
				System.out.println("Entering CYLINDER function...\n");
				Commands.cylinder(client_msg, l.getClientFromLedger(remote_socket_address),l);
			}else if (validatedString.equals("CIRCLE AREA")){
				System.out.println("Entering CIRCLE AREA function...\n");
				Commands.circle_area(l.getClientFromLedger(remote_socket_address), client_msg, l);
			}else if (validatedString.equals("CYLINDER AREA")){
				System.out.println("Entering CYLINDER AREA function...\n");
				Commands.cylinder_area(l.getClientFromLedger(remote_socket_address), client_msg, l);
			}else if (validatedString.equals("HGT")){
				System.out.println("Entering HGT function...\n");
				Commands.cylinder_height(l.getClientFromLedger(remote_socket_address), client_msg, l);
			}else if (validatedString.equals("CIRC")){
				System.out.println("Entering CIRC function...\n");
				Commands.circle_circumference(l.getClientFromLedger(remote_socket_address), client_msg, l);
			}else if (validatedString.equals("VOL")){
				System.out.println("Entering VOL function...\n");
				Commands.sphere_volume(l.getClientFromLedger(remote_socket_address), client_msg, l);
			}else if (validatedString.equals("RAD")){
				System.out.println("Entering RAD function...\n");
				Commands.sphere_radius(l.getClientFromLedger(remote_socket_address), client_msg, l);
			}else if (validatedString.equals("BYE")){
				System.out.println("Entering BYE function...\n");
				Commands.bye(client_msg, l.getClientFromLedger(remote_socket_address), l);
			}else if (validatedString.equals("XAVIER")){
				System.out.println("Entering XAVIER function...\n");
				//For development purposes
				System.out.println("Server shutting down...\n");
				Utility.sendResponse(remote_IP, remote_port, 200, "Server shutting down...");
				// Clear the buffer after every message.
				receive = new byte[65535];
				break;
			}

			// Clear the buffer after every message.
			receive = new byte[65535];

			System.out.println("\n\nUpdated ledger...\n");
			l.printLedger();

			System.out.println("Client request complete 5\n-----------------------------------------------------------------------------\n\n");

		}
	}


}
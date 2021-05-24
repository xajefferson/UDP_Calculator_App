// Java program to illustrate Client side
// Implementation using DatagramSocket
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client
{
	public static void main(String args[]) throws IOException
	{
		int server_port  = 	1234;

		Scanner sc = new Scanner(System.in);
		//String server_IP = "192.168.0.11";

		// Step 1:Create the socket object for
		// carrying the data.
		DatagramSocket ds = new DatagramSocket();

		InetAddress server_ip = InetAddress.getLocalHost();
		//InetAddress ip = InetAddress.getByName(server_IP);
		byte buf[] = null;
		byte[] receive = new byte[65535];
		DatagramPacket server_response = null;

		// loop while user not enters "bye"
		while (true)
		{
			server_response = new DatagramPacket(receive, receive.length);

			System.out.print("Enter command: ");
			String inp = sc.nextLine();
			inp = inp.toUpperCase();

			// convert the String input into the byte array.
			buf = inp.getBytes();

			// Step 2 : Create the datagramPacket for sending
			// the data.
			DatagramPacket DpSend =
				new DatagramPacket(buf, buf.length, server_ip, server_port);

			// Step 3 : invoke the send call to actually send
			// the data.
			ds.send(DpSend);

			ds.receive(server_response);
			System.out.println("Server: " + Utility.data(receive));


			// break the loop if user enters "bye"
			if (inp.equals("BYE"))
				break;

			// Clear the buffer after every message.
			receive = new byte[65535];
		}
	}
}

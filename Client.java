// Java program to illustrate Client side
// Implementation using DatagramSocket
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client
{
	public static void main(String args[]) throws IOException
	{
		System.out.println("Client process starting...\n");
		
		Scanner sc = new Scanner(System.in);
		DatagramSocket ds = new DatagramSocket();

		InetAddress server_ip;
		int server_port ;

		byte buf[] = null;
		byte[] receive = new byte[65535];
		DatagramPacket server_response = null;


		File f = new File("client.conf");
		Scanner f_scan = new Scanner(f); 

		String l1 = f_scan.nextLine();
		String l2 = f_scan.nextLine();

		l1 = l1.split("=")[1];
		l2 = l2.split("=")[1];

		server_ip = InetAddress.getByName(l1);
		server_port = Integer.valueOf(l2);

		System.out.println("Server IP read in: " + l1);
		System.out.println("Server port read in: " + l2);


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
			System.out.println(Utility.data(receive));


			// break the loop if user enters "bye"
			String[] tmp = inp.split(" ");
			if (tmp[0].equals("BYE"))
				break;

			// Clear the buffer after every message.
			receive = new byte[65535];
			System.out.println("\n-----------------------------------------------------------");
		}
	}

	
}

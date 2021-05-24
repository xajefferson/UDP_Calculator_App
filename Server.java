// Java program to illustrate Server side
// Implementation using DatagramSocket
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;


public class Server
{
	public static void main(String[] args) throws IOException
	{

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

			System.out.println("Client: " + Utility.data(receive));
			System.out.println("Client IP: " + remote_IP.toString().replace("/",""));
			System.out.println("Client Socket Address: " + remote_socket_address.toString().replace("/", ""));
			System.out.println("Client Port: " + remote_port); 

			Utility.sendResponse(remote_IP, remote_port, 200, "OK");

			// Exit the server if the client sends "bye"
			if (Utility.data(receive).toString().equals("BYE"))
			{
				System.out.println("Client sent bye.....EXITING");
				break;
			}

			// Clear the buffer after every message.
			receive = new byte[65535];
		}
	}


}

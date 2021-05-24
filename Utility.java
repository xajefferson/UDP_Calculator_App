import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Utility {
    
    public static boolean testFunction(){
        return true;
    }

    public static void sendResponse(InetAddress client_ip, int client_port, int reply_code, String message){

        DatagramSocket ds = null;

		try {
			ds = new DatagramSocket();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        byte buf[] = null;
        String to_send = String.valueOf(reply_code) + " " + message;  

        buf = to_send.getBytes();

        DatagramPacket DpSend = new DatagramPacket(buf, buf.length, client_ip, client_port);
        try {
			ds.send(DpSend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // A utility method to convert the byte array
	// data into a string representation.
	public static StringBuilder data(byte[] a)
	{
		if (a == null)
			return null;
		StringBuilder ret = new StringBuilder();
		int i = 0;
		while (a[i] != 0)
		{
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}
}

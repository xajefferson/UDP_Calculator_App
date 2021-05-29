import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Utility {
    

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


	public static StringBuilder data(byte[] a){
		// A utility method to convert the byte array
		// data into a string representation.
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

	public static String getCommand(String command) {

        System.out.println("The commands command is: " + command.substring(0, 4));

        if (command.substring(0, 4).equals("HELO")) {
            System.out.println("Command was: " + command.substring(0, 4));
            return "HELO";

        } else if (command.substring(0, 4).equals("HELP")) {
            System.out.println("Command was: " + command.substring(0, 4));
            return "HELP";
        } else if (command.substring(0, 6).equals("CIRCLE")) {
            System.out.println("Command was: " + command.substring(0, 6));
            return "CIRCLE";

        } else if (command.substring(0, 6).equals("SPHERE")) {
            System.out.println("Command was: " + command.substring(0, 6));
            return "SPHERE";

        } else if (command.substring(0, 8).equals("CYLINDER")) {
            System.out.println("Command was: " + command.substring(0, 8));
            return "CYLINDER";

        } else if (command.substring(0, 4).equals("AREA")) {
            System.out.println("Command was: " + command.substring(0, 4));
            return "AREA";

        } else if (command.substring(0, 8).equals("CYLINDER")) { //TODO: add sub commands
			//To seperate commands: https://stackoverflow.com/questions/3214002/splitting-a-space-separated-list
            System.out.println("Command was: " + command.substring(0, 8));
            return "CYLINDER";

        } else if (command.substring(0, 3).equals("BYE")) {
            System.out.println("Command was: " + command.substring(0, 3));
            return "BYE";
        } else
            return "command unrecognized";

        
    }

    public static Boolean check_array_for_spaces(String[] to_check){


        for (int i = 0; i< to_check.length; i++){
            if (to_check[i].contains(" ")) {
                return true; 
            }

        }

        return false;
    }

    
}

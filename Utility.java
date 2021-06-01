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
			e1.printStackTrace();
		}

        byte buf[] = null;
        String to_send = String.valueOf(reply_code) + " " + message;  

        buf = to_send.getBytes();

        DatagramPacket DpSend = new DatagramPacket(buf, buf.length, client_ip, client_port);
        try {
			ds.send(DpSend);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


	public static StringBuilder data(byte[] a){

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

	public static String getCommand(String[] command) {


        if (command[0].equals("HELO") && (command.length == 2)) {
            System.out.println("Command was: " + command[0]);
            return "HELO";

        } else if (command[0].equals("HELP")&& (command.length == 1)) {
            System.out.println("Command was: " + command[0]);
            return "HELP";
        } else if (command[0].equals("CIRCLE")&& (command.length == 1)) {
            System.out.println("Command was: " + command[0]);
            return "CIRCLE";

        } else if (command[0].equals("SPHERE")&& (command.length == 1)) {
            System.out.println("Command was: " + command[0]);
            return "SPHERE";

        } else if (command[0].equals("CYLINDER")&& (command.length == 1)) {
            System.out.println("Command was: " + command[0]);
            return "CYLINDER";

        } else if (command[0].equals("AREA") && (command.length == 2)) {
            System.out.println("Command was: CIRCLE AREA");
            return "CIRCLE AREA";

        } else if (command[0].equals("AREA") && (command.length == 3)) {
            System.out.println("Command was: CYLINDER AREA");
            return "CYLINDER AREA";

        } else if (command[0].equals("HGT")&& (command.length == 3)) {
            System.out.println("Command was: " + command[0]);
            return "HGT";
        } else if (command[0].equals("CIRC")&& (command.length == 2)) {
            System.out.println("Command was: " + command[0]);
            return "CIRC";
        }  else if (command[0].equals("VOL")&& (command.length == 2)) {
            System.out.println("Command was: " + command[0]);
            return "VOL";
        }  else if (command[0].equals("RAD")&& (command.length == 2)) {
            System.out.println("Command was: " + command[0]);
            return "RAD";
        }  else if (command[0].equals("BYE")&& (command.length == 2)) {
            System.out.println("Command was: " + command[0]);
            return "BYE";
        } 
        else
            return "command unrecognized";

        
    }

    

    public static double convert_string_to_double(String s){
        System.out.println("Convert string to double called");
        
        try{
            double num = Double.valueOf(s);
            return num;
        } catch(Exception e){
            return -1.0; 
        }
    }

    public static Boolean check_array_for_spaces(String[] to_check){

        //System.out.println("Check array for spaces function called...");
        for (int i = 0; i< to_check.length; i++){
            //System.out.println(to_check[i]);
            if (to_check[i].contains("\\s") || to_check[i] == "") {
                //System.out.println("Space found");
                return true; 
            }
        }
        //System.out.println("Space not found");
        return false;
    }

    public static int parse_udp_port(String to_parse){
        String[] udp_port = to_parse.split("=");
        return Integer.valueOf(udp_port[1]); 
    }

}

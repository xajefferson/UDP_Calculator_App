import java.net.InetAddress;


public class Commands {
    

    public static void helo(InetAddress client_ip, int client_port){
        
    }

    public static void help(InetAddress client_ip, int client_port){
        
    }

    public static void circle(InetAddress client_ip, int client_port){
        
    }
    
    public static void sphere(InetAddress client_ip, int client_port){
        
    }

    public static void cylinder(InetAddress client_ip, int client_port){
        
    }

    public static void bye(InetAddress client_ip, int client_port){
        
    }

    public static String parseCommand(String command){

    System.out.println("The commands command is: " + command.substring(0,4));

    if (command.substring(0,4).equals("HELO")){
            System.out.println("Command was : " + command.substring(0,4));
            return "HELO";

    } else if (command.substring(0,4).equals("HELP")){
        System.out.println("Command was : " + command.substring(0,4));
        return "HELP";
    }
    //TODO add rest of commands
    return "No";
}
        

    
}

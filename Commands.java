import java.net.InetAddress;
import java.net.SocketAddress;

//import org.graalvm.compiler.lir.LIRInstruction.Use;

public class Commands {

    public static void helo(InetAddress client_ip, int client_port, SocketAddress client_socket_address) {
        //This is where a client will be added to the ledger

        //TODO: check if client already exist in ledger if they do, send 503 error, if not, create client object and add them 
        //TODO: Add them to ledger
        //Send 200 OK

    }

    public static void help(User client) {

    }

    public static void circle(String[] client_command_array, User client) {

        //TODO: set circleTriggered in User obj to true and the other 2 to false

    }

    public static void sphere(String[] client_command_array, User client) {
        //Update 3 bools to false
        //set circleTriggered to true


    }

    public static void cylinder(String[] client_command_array, User client) {

    }

    public static void bye(String[] client_command_array, User client) {
        //Check if client is in the ledger if not send error msg if so
        //TODO: Delete client from ledger
        //Send 200
    }

    public static void circle_area(InetAddress client_ip, int client_port, String[] array){

    }

    public static void circle_circumference(InetAddress client_ip, int client_port, String[] array){
        
    }

    public static void sphere_volume(InetAddress client_ip, int client_port, String[] array){
        
    }

    public static void sphere_radius(InetAddress client_ip, int client_port, String[] array){
        
    }

    public static void cylinder_area(InetAddress client_ip, int client_port, String[] array){
        
    }

    public static void cylinder_height(InetAddress client_ip, int client_port, String[] array){
        
    }







}

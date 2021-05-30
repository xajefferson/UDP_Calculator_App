import java.net.InetAddress;
import java.net.SocketAddress;

import jdk.jshell.execution.Util;

//import org.graalvm.compiler.lir.LIRInstruction.Use;

public class Commands {

    public static void helo(InetAddress client_ip, int client_port, SocketAddress client_socket_address, Ledger l) {
        // This is where a client will be added to the ledger

        // TODO: check if client already exist in ledger if they do, send 503 error, if
        // not, create client object and add them
        // TODO: Add them to ledger
        // Send 200 OK

        if (l.socketAddrExists(client_socket_address)) {
            Utility.sendResponse(client_ip, client_port, 501, "Client already exists");
            return;
        }

        User newUser = new User(client_ip, client_port, client_socket_address);

        l.addClientToLedger(client_socket_address, newUser);
        String msg = "HELO " + client_ip.toString().replace("/", "") + "(UDP)";
        Utility.sendResponse(client_ip, client_port, 200, msg);
        return;
    }

    public static void help(User client, Ledger l) {
        // Always check ledger for client existance
        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                    "Bad sequence of commands, send HELO firstt");
            return;
        }

        String help_string = "Enter commands and their appropriate arguments.";
        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 200, help_string);
        
        return;

    }

    public static void circle(String[] client_command_array, User client, Ledger l) {

        // TODO: set circleTriggered in User obj to true and the other 2 to false
        // Always check ledger for client existance
        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                    "Bad sequence of commands, send HELO before CIRCLE");
            return;
        }

        client.setCircleTriggered(true);
        client.setCylinderTriggered(false);
        client.setSphereTriggered(false);

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 200, "CIRCLE ready!");

        l.replaceClientObject(client.getUserSocketAddress(), client);//FIXME: This may or may not work
        //TODO: Test ledger update with print statements tomorrow
        return;
    }

    public static void sphere(String[] client_command_array, User client, Ledger l) {
        // Update 3 bools to false
        // set circleTriggered to true
        // Send ready response

        
        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                    "Bad sequence of commands, send HELO before SPHERE");
            return;
        }


        client.setCircleTriggered(false);
        client.setCylinderTriggered(false);
        client.setSphereTriggered(true);

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 200, "SPHERE ready!");
        l.replaceClientObject(client.getUserSocketAddress(), client);//FIXME: This may or may not work
        return;

    }

    public static void cylinder(String[] client_command_array, User client, Ledger l) {

        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                    "Bad sequence of commands, send HELO before CYLINDER");
            return;
        }


        client.setCircleTriggered(false);
        client.setCylinderTriggered(true);
        client.setSphereTriggered(false);

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 200, "CYLINDER ready!");
        l.replaceClientObject(client.getUserSocketAddress(), client);//FIXME: This may or may not work
        return;

    }

    public static void bye(String[] client_command_array, User client, Ledger l) {
        // Check if client is in the ledger if not send error msg if so
        // TODO: Delete client from ledger
        // Send 200

        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                   "Bad sequence of commands, send HELO before BYE");
            return;
        }

        InetAddress temp_ip = client.getUserIP();
        int temp_port = client.getUserPort();

        l.removeClientFromLedger(client.getUserSocketAddress());

        String message  = "BYE " + temp_ip.toString().replace("/", "") + "(UDP)";        

        Utility.sendResponse(temp_ip, temp_port, 200, message);
        return;

    }

    public static void circle_area(User client, String[] array, Ledger l) {

    }

    public static void circle_circumference(User client, String[] array, Ledger l) {

    }

    public static void sphere_volume(User client, String[] array, Ledger l) {

    }

    public static void sphere_radius(User client, String[] array, Ledger l) {

    }

    public static void cylinder_area(User client, String[] array, Ledger l) {

    }

    public static void cylinder_height(User client, String[] array, Ledger l) {

    }

}

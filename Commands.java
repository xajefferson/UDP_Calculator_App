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
            Utility.sendResponse(client_ip, client_port, 503, "Bad sequence of commands, client already exists");
            return;
        }

        User newUser = new User(client_ip, client_port, client_socket_address);

        l.addClientToLedger(client_socket_address, newUser);
        String msg = "HELO " + client_ip.toString().replace("/", "") + "(UDP)";
        Utility.sendResponse(client_ip, client_port, 200, msg);


        System.out.println("Client added to ledger: \n");
        l.printUserFromLedger(client_socket_address);

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

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 210, "CIRCLE ready!");

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

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 220, "SPHERE ready!");
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

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 230, "CYLINDER ready!");
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

        //Check if circle was called first

        //store arrat[1] into var and sund that var to Utility.convertStrToDouble 
        //If it returns a -1 then invalid input was sent
        //Check that array[1] is is a non negative number, if it is let client know then return 

        //Check if client exists in ledger
        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                   "Bad sequence of commands, send HELO before CIRCLE and AREA");
            return;
        }

        //Check if circle was called first
        if(!(client.isCircleTriggered())){
            //if the client didnt call CIRCLE first
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503, "Bad sequence of commands, send CIRCLE first.");
            return;
        }
        
        String calc = array[1];
        Double converted_result = Utility.convert_string_to_double(calc);

        //Check if valid number was recieved
        if(converted_result <= 0){
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 501, "Syntax error in parameters or arguments");
            return;
        }

        //Valud number was recieved now send response to client
        String to_send = String.valueOf(Calculations.calcCircArea(converted_result));
        //System.out.println("to send value: " + to_send);

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 250, to_send);
        return;

    }

    public static void circle_circumference(User client, String[] array, Ledger l) {

        //Cleck if client is in ledger
        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                   "Bad sequence of commands, send HELO before CIRCLE and CIRC");
            return;
        }

        //Check if circle was called first
        if(!(client.isCircleTriggered())){
            //if the client didnt call CIRCLE first
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503, "Bad sequence of commands, send CIRCLE first.");
            return;
        }
        
        String calc = array[1];
        Double converted_result = Utility.convert_string_to_double(calc);

        //Check if valid number was recieved
        if(converted_result <= 0){
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 501, "Syntax error in parameters or arguments");
            return;
        }

        //Valid number was recieved now send response to client
        String to_send = String.valueOf(Calculations.calcCircCircumference(converted_result));

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 250, to_send);
        return;



    }

    public static void sphere_volume(User client, String[] array, Ledger l) {


        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                   "Bad sequence of commands, send HELO before SPHERE and VOL");
            return;
        }

        //Check if sphere was called first
        if(!(client.isSphereTriggered())){
            //if the client didnt call CIRCLE first
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503, "Bad sequence of commands, send SPHERE first.");
            return;
        }
        
        String calc = array[1];
        Double converted_result = Utility.convert_string_to_double(calc);

        //Check if valid number was recieved
        if(converted_result <= 0){
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 501, "Syntax error in parameters or arguments");
            return;
        }

        //Valid number was recieved now send response to client
        String to_send = String.valueOf(Calculations.calcSphereVolume(converted_result));

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 250, to_send);
        return;


    }

    public static void sphere_radius(User client, String[] array, Ledger l) {
        
        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                   "Bad sequence of commands, send HELO before SPHERE and RAD");
            return;
        }

        //Check if sphere was called first
        if(!(client.isSphereTriggered())){
            //if the client didnt call CIRCLE first
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503, "Bad sequence of commands, send SPHERE first.");
            return;
        }
        
        String calc = array[1];
        Double converted_result = Utility.convert_string_to_double(calc);

        //Check if valid number was recieved
        if(converted_result <= 0){
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 501, "Syntax error in parameters or arguments");
            return;
        }

        //Valid number was recieved now send response to client
        String to_send = String.valueOf(Calculations.calcSphereRadius(converted_result));

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 250, to_send);
        return;

    }

    public static void cylinder_area(User client, String[] array, Ledger l) {

        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                   "Bad sequence of commands, send HELO before CYLINDER and AREA");
            return;
        }

        //Check if cylinder was called first
        if(!(client.isCylinderTriggered())){
            //if the client didnt call CYLINDER first
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503, "Bad sequence of commands, send CYLINDER first.");
            return;
        }
        
        String r = array[1];
        String h = array[2];
        Double r_converted = Utility.convert_string_to_double(r);
        Double h_converted = Utility.convert_string_to_double(h);

        
        //Check if valid number was recieved
        if(r_converted <= 0 || h_converted <=0){
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 501, "Syntax error in parameters or arguments");
            return;
        }

        //Valid number was recieved now send response to client
        String to_Send = String.valueOf(Calculations.calcCylinderArea(r_converted, h_converted));

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 250, to_Send);
        return;
    }

    public static void cylinder_height(User client, String[] array, Ledger l) {

        if (!(l.socketAddrExists(client.getUserSocketAddress()))) {
            // If the client is not in the ledger
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503,
                   "Bad sequence of commands, send HELO before CYLINDER and AREA");
            return;
        }

        //Check if cylinder was called first
        if(!(client.isCylinderTriggered())){
            //if the client didnt call CYLINDER first
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 503, "Bad sequence of commands, send CYLINDER first.");
            return;
        }
        
        String V = array[1];
        String r = array[2];
        Double V_converted = Utility.convert_string_to_double(V);
        Double r_converted = Utility.convert_string_to_double(r);

        
        //Check if valid number was recieved
        if(V_converted <= 0 || r_converted <=0){
            Utility.sendResponse(client.getUserIP(), client.getUserPort(), 501, "Syntax error in parameters or arguments");
            return;
        }

        //Valid number was recieved now send response to client
        String to_Send = String.valueOf(Calculations.calcCylinderHeight(V_converted, r_converted));

        Utility.sendResponse(client.getUserIP(), client.getUserPort(), 250, to_Send);
        return;
    }

}

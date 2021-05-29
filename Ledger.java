import java.net.Socket;
import java.net.SocketAddress;
import java.util.Hashtable;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Ledger {


    Hashtable<SocketAddress, User> ht;

    public Ledger(){
        this.ht= new Hashtable<SocketAddress, User>();
    }

    public Ledger(User client){
        this.ht  = new Hashtable<SocketAddress, User>();
        ht.put(client.getUserSocketAddress(), client);
    }
    

    public User getClientObject(SocketAddress addr){
        return ht.get(addr);
    }

    public void replaceClientObject(SocketAddress addr, User client){
        ht.replace(addr, client);
    }

    public void printLedger(){
        //TODO: Test print ledger
        System.out.println(ht.toString());
    }

    public Boolean socketAddrExists(SocketAddress addr){
       return ht.containsKey(addr);
    }

    public void addClientToLedger(SocketAddress addr, User client){
        ht.put(addr, client);
    }

    public void removeClientFromLedger(SocketAddress addr){
        String removed = ht.remove(addr).toString();
        System.out.print("Client Removed From Ledger\n----------------------------" + removed);
    }
}


import java.net.SocketAddress;
import java.util.Hashtable;
import java.util.Map;

public class Ledger {


    Hashtable<SocketAddress, User> ht;

    public Ledger(){
        this.ht= new Hashtable<SocketAddress, User>();
    }

    public Ledger(User client){
        this.ht  = new Hashtable<SocketAddress, User>();
        ht.put(client.getUserSocketAddress(), client);
    }
    

    public User getClientFromLedger(SocketAddress addr){
        return ht.get(addr);
    }

    public void replaceClientObject(SocketAddress addr, User client){
        ht.replace(addr, client);
    }

    public void printUserFromLedger(SocketAddress addr){
        System.out.println(ht.get(addr).toString());
    }




    public Boolean socketAddrExists(SocketAddress addr){
       return ht.containsKey(addr);
    }

 

    public void addClientToLedger(SocketAddress addr, User client){
        ht.put(addr, client);
    }

    public void removeClientFromLedger(SocketAddress addr){
        String removed = ht.remove(addr).toString();
        System.out.println("Client Removed From Ledger\n----------------------------\n" + removed);
    }

    public void printLedger(){
        for (Map.Entry<SocketAddress, User> m:ht.entrySet()){
            System.out.println("User key: " + m.getKey().toString() + "\nValues: " + m.getValue().toString() + "_________________________\n");
        }
    }
}

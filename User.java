import java.net.InetAddress;
import java.net.SocketAddress;

public class User {


    private InetAddress user_IP;
    private int user_port;
    private SocketAddress user_socket_address;

    private Boolean circle_triggered;
    private Boolean sphere_triggered;
    private Boolean cylinder_triggered;

    public User(InetAddress user_IP, int user_port, SocketAddress user_socket_address){
        this.user_IP = user_IP;
        this.user_port = user_port;
        this.user_socket_address = user_socket_address;

        circle_triggered = false;
        sphere_triggered = false;
        cylinder_triggered = false;
    }

    public InetAddress getUserIP(){
        return user_IP;
    }

    public int getUserPort(){
        return user_port;
    }

    public SocketAddress getUserSocketAddress(){
        return user_socket_address;
    }

    public Boolean isCircleTriggered(){
        return circle_triggered;
    }

    public Boolean isSphereTriggered(){
        return sphere_triggered;
    }

    public Boolean isCylinderTriggered(){
        return cylinder_triggered;
    }

    public void setCircleTriggered(Boolean value){
        circle_triggered = value;
    }
    
    public void setSphereTriggered(Boolean value){
        sphere_triggered = value;
    }

    public void setCylinderTriggered(Boolean value){
        cylinder_triggered = value;
    }

    @Override
    public String toString(){
        return String.format("User IP: %s\n User Port: %d\n User Socket Address: %s\n" +
        "circle_triggered: %B\nsphere_triggered: %B\n cylinder_triggered: %B", 
        user_IP.toString(), user_port, user_socket_address.toString(), circle_triggered,
        sphere_triggered, cylinder_triggered);
    }
}

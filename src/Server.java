import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {
	
	private DatagramSocket sendReceiveSocket;
	private DatagramPacket packet;
	private int portNum;
	private InetAddress address;
	
	public Server() {
		try {
			sendReceiveSocket = new DatagramSocket();
			//sendReceiveSocket.connect(address, portNum);
			portNum = sendReceiveSocket.getLocalPort();
			address = InetAddress.getLocalHost();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	
	public void serializeInetAddress() {
		// Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream("address.txt"); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(address); 
              
            out.close(); 
            file.close();  
  
        }catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
  
	}

	public InetAddress getAddress() {
		return address;
	}

	public int getPortNum() {
		return portNum;
	}
	
	public static void main(String[] args) {
		Server me = new Server();
		System.out.println("Server\nAddress: " + me.getAddress() + ". Port Num: " + me.getPortNum());
		me.serializeInetAddress();

	}

}

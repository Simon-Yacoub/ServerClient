import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;

public class ServerSetup {

	public static void main(String[] args) {
		serializeInetAddress();
		Thread serverThread = new Thread(new Server());
		
		serverThread.start();
	}
	
	public static void serializeInetAddress() {
        try
        {    
            FileOutputStream file = new FileOutputStream("address.txt"); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(InetAddress.getLocalHost()); 
              
            out.close(); 
            file.close();  
  
        }catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
  
	}
}

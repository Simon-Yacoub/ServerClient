import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	private DatagramSocket sendReceiveSocket;
	private DatagramPacket packet;
	private int portNum;
	private InetAddress address;

	public Client() {
		try {
			sendReceiveSocket = new DatagramSocket();
			portNum = sendReceiveSocket.getLocalPort();
			address = InetAddress.getLocalHost();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Client me = new Client();
		System.out.println("Client\nAddress: " + me.getAddress() + ". Port Num: " + me.getPortNum());
		InetAddress serverAddress = deserializeServerInetAddress();
		System.out.println("Server Address: " + serverAddress);
		

	}

	public static InetAddress deserializeServerInetAddress() {
		// Deserialization
		InetAddress serverAddress = null;
		try {
			// Reading the object from a file
			FileInputStream file = new FileInputStream("address.txt");
			ObjectInputStream in = new ObjectInputStream(file);

			// Method for deserialization of object
			serverAddress = (InetAddress) in.readObject();

			in.close();
			file.close();
		}catch (IOException ex) {
			System.out.println("IOException is caught");
		}catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
		
		return serverAddress;

	}

	public InetAddress getAddress() {
		return address;
	}

	public int getPortNum() {
		return portNum;
	}

}
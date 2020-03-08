import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private static DatagramSocket sendReceiveSocket;	
	private int portNum;
	private static InetAddress serverAddress;
	private final static int SERVER_PORT = 5000;

	public Client() {
		try {
			sendReceiveSocket = new DatagramSocket();
			portNum = sendReceiveSocket.getLocalPort();
			serverAddress = deserializeServerInetAddress();
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Enter message to send to server:");
			String message = sc.nextLine();
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, SERVER_PORT);
			try {
				sendReceiveSocket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		

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

	public int getPortNum() {
		return portNum;
	}

}
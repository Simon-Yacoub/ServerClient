import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
	private static DatagramSocket sendReceiveSocket;
	private static InetAddress serverAddress;
	private final static int SERVER_PORT = 5000;

	public Client() {
		try {
			sendReceiveSocket = new DatagramSocket();
			serverAddress = deserializeServerInetAddress();
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			sendReceiveSocket = new DatagramSocket();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		serverAddress = deserializeServerInetAddress();
		while (true) {

			// Send a message to server
			System.out.print("Enter message to send to server: ");
			Scanner sc = new Scanner(System.in);
			String message = sc.nextLine();
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, SERVER_PORT);
			try {
				sendReceiveSocket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Receive a response from server
			try {
				data = new byte[100];
				packet = new DatagramPacket(data, data.length); // packet for receiving.
				//System.out.print("Client Waiting.\n");
				sendReceiveSocket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.print("Server: ");
			System.out.print(new String(packet.getData(), 0, packet.getLength()) + "\n");

		}

	}

	public static InetAddress deserializeServerInetAddress() {
		InetAddress serverAddress = null;
		try {
			FileInputStream file = new FileInputStream("address.txt");
			ObjectInputStream in = new ObjectInputStream(file);

			serverAddress = (InetAddress) in.readObject();

			in.close();
			file.close();
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}

		return serverAddress;

	}

}
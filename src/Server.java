import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

public class Server implements Runnable{
	
	private DatagramSocket sendReceiveSocket;
	private DatagramPacket packet;
	private final int PORT = 5000;
	
	public Server() {
		try {
			sendReceiveSocket = new DatagramSocket(PORT);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		System.out.print("Server Online and Waiting.\n");
		while(true) {
			
			//Receive a message from the client
			try {
				byte[] data = new byte[100];
				packet = new DatagramPacket(data, data.length); //packet for receiving.
				sendReceiveSocket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.print("Client: ");
			System.out.print(new String(packet.getData(), 0, packet.getLength()) + "\n");
			
			//Create a reply packet with a message in it
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Response: ");
			String message = sc.nextLine();
			
			byte[] msg = message.getBytes();
			packet = new DatagramPacket(msg, msg.length, packet.getAddress(), packet.getPort());
			try {
				sendReceiveSocket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}

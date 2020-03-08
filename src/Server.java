import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
		while(true) {
			try {
				byte[] data = new byte[100];
				packet = new DatagramPacket(data, data.length); //packet for receiving.
				System.out.print("Server Idly Waiting.");
				sendReceiveSocket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("Packet received from: " + packet.getAddress() + "\n");
			System.out.println("Destination port: " + packet.getPort() + ", ");
			int len = packet.getLength();
			System.out.println("Length: " + len + ". ");
			System.out.println("Containing String: ");
			System.out.println(new String(packet.getData(), 0, len) + "\n");
			System.out.println("Containing Bytes: ");
			for(byte b : packet.getData()) {
				System.out.print(b + ", ");
			}
			
		}
	}

}

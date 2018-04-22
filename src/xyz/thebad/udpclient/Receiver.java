package xyz.thebad.udpclient;

import java.io.IOException;
import java.net.DatagramPacket;

public class Receiver implements Runnable {

	@Override
	public void run() {
		
		while(!Socket.closed) {
			
			byte[] buffer = new byte[8192];
			DatagramPacket packet = new DatagramPacket(buffer, 8192);
			try {
				Socket.socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			
			UDPClient.addLine(new String(packet.getData()), false);
		}
	}

}

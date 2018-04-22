package xyz.thebad.udpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Test implements Runnable {
	
	DatagramSocket socket = null;
	
	@Override
	public void run() {

		try {
			socket = new DatagramSocket(5000);
		} catch (SocketException e) {
			e.printStackTrace();
			return;
		}

		while(!Socket.closed) {

			byte[] buffer = new byte[8192];
			DatagramPacket packet = new DatagramPacket(buffer, 8192);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			try {
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

	}

	public void stop() {
		socket.close();
	}

}

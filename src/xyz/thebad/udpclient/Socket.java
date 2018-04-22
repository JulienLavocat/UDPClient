package xyz.thebad.udpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Socket {

	public static DatagramSocket socket;
	public static boolean closed;
	
	public static boolean init() {
		closed = false;
		
		try {
			socket = new DatagramSocket(Config.APP_PORT);
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		}
		
		new Thread(new Receiver()).start();
		
		return true;
	}
	
	public static void send(String msg) {
		byte[] data = (Config.HEADER + Config.SEPARATOR + msg).getBytes();
		try {
			socket.send(new DatagramPacket(data, data.length, Config.HOST, Config.PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String receive() {
		byte[] buffer = new byte[8192];
		DatagramPacket packet = new DatagramPacket(buffer, 8192);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new String(packet.getData()).trim();
	}
	
	public static void close() {
		closed = true;
		socket.close();
	}
	
}

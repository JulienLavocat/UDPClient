package xyz.thebad.udpclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class Config {
	
	public static int 			PORT;
	public static int 			APP_PORT;
	public static InetAddress 	HOST;
	public static String			HEADER;
	public static String 		SEPARATOR;
	
	public static boolean loadConfig() {

		Properties props = new Properties();
		try {
			props.load(new FileInputStream("./config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		PORT = Integer.parseInt(props.getProperty("port"));
		try {
			HOST = InetAddress.getByName(props.getProperty("host"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
		HEADER = props.getProperty("header");
		SEPARATOR = props.getProperty("separator");
		APP_PORT = Integer.parseInt(props.getProperty("app_port"));
		
		return true;
	}

}

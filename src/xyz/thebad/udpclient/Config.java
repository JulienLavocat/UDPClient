package xyz.thebad.udpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
	
	public static boolean 		HEADER_REQUIRED;
	public static int 			PORT;
	public static int 			APP_PORT;
	public static InetAddress 	HOST;
	public static String			HEADER;
	public static String 		SEPARATOR;
	
	private static String default_config = "host=localhost\n" + 
			"port=3000\n" + 
			"header=1234\n" + 
			"separator=§\n" + 
			"app_port=25565" +
			"header_required=false";
	
	public static boolean loadConfig() {

		Properties props = new Properties();
		
		File f = new File("./config.properties");
		if(!f.exists()) {
			try {
				Files.write(Paths.get("./config.properties"), default_config.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
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
		HEADER_REQUIRED = Boolean.parseBoolean(props.getProperty("header_required"));
		
		return true;
	}

}

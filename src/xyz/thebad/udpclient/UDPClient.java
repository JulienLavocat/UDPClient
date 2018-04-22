package xyz.thebad.udpclient;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class UDPClient extends Application {

	public Stage stage;
	public static UDPClientController controller;
	
	
	public static void main(String[] args) {
		
		//new Thread(new Test()).start();
		
		if(!Config.loadConfig()) {
			return;
		}
		
		if(!Socket.init()) {
			return;
		}
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Paths.get("./udpclientgui.fxml").toUri().toURL());
		Parent root = loader.load();
		
		controller = loader.getController();
		//controller.setApp(this);
		
		Scene scene = new Scene(root, 640, 400);
		
		this.stage = stage;
		this.stage.setTitle("UDPClient - Version 1.0");
		this.stage.setScene(scene);
		this.stage.show();
		this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        Socket.close();
		
		        try {
					stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		});
		addLine("UDPClient ready", true);
		addLine("Currently sending packet to " + Config.HOST.getHostAddress() + ":" + Config.PORT, true);
	}
	
	public static synchronized void addLine(String line, boolean client) {
		String current = controller.getConsole().getText();
		if(client)
			current += ("[CLIENT] " + line + "\n");
		else
			current += ("[SERVER] " + line + "\n");
	
		controller.getConsole().setText(current);
	}

}

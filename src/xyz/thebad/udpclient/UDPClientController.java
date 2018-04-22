package xyz.thebad.udpclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UDPClientController {

	@FXML
	private TextArea console;
	@FXML
	private Button send;
	@FXML
	private TextField packet;
	@FXML
	private VBox vbox;
	@FXML
	private AnchorPane anchor;
	
	public UDPClientController() {
		
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleAction() {
		UDPClient.addLine("Sending \"" + packet.getText() + "\"", true);
		Socket.send(packet.getText());
		packet.setText("");
	}

	public TextArea getConsole() {
		return console;
	}
	
	public TextField getPacket() {
		return packet;
	}
	
	public Button getSend() {
		return send;
	}
	
}

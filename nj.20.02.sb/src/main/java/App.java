import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
	
	private double dragOffsetX, dragOffsetY;
	
	@Override
	public void start(Stage stage) throws IOException {
		
		URL fxmlUrl = getClass().getClassLoader().getResource("fxml/persons_ui.fxml");
		AnchorPane root = FXMLLoader.<AnchorPane>load(fxmlUrl);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("style.css");
		
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.show();
		
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				dragOffsetX = event.getScreenX() - stage.getX();
				dragOffsetY = event.getScreenY() - stage.getY();
			}
		});
		
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - dragOffsetX);
				stage.setY(event.getScreenY() - dragOffsetY);
			}
		});
	}
	
	public static void main(String[] args) {
		launch();
	}
}
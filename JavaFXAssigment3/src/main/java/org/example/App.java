package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        URL fxmlUrl = getClass().getClassLoader().getResource("fxml/persons_ui.fxml");
        AnchorPane root = FXMLLoader.<AnchorPane>load(fxmlUrl);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }




    public static void main(String[] args) {
        launch();
    }

}
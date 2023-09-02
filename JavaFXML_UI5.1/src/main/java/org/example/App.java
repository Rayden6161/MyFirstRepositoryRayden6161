package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {
    private double dragOffsetX,dragOffsetY;
    @Override
    public void start(Stage stage) throws IOException {
URL fxmlURL=getClass().getClassLoader().getResource("add_person.fxml"); //putanja do naseg FXML fajla add_person.fxml u resources...
  /*Takodje mozemo izbrisati ove viskove koje name je okruzenje izgenerisalo a odnosi se
  * na klase u org.example PrimaryController i SecondaryController.
  * Takodje i u resources.org.example izbrisemo primary.fxml i secondary.fxml */

    GridPane root =FXMLLoader.load(fxmlURL);
    //znaci unutar instance root imacemo ceo UI koriscenjem fxml-a
    //load vraca instancu korenog elementa fxml fajla GridPane je nas koreni element

Scene scene = new Scene(root);
scene.getStylesheets().add("style.css");//putanja do eksternog css fajla
stage.initStyle(StageStyle.UNDECORATED);
/*znaci ovim smo prozoru naseg stagea(prikaza glavnog) uklonili njegove dekoracije tako da
* ne mozemo izaci iz prozora jer je nestao iks i onda dalje radimo u add_person.fxml
* Znaci u add_peson.fxml dodacemo button kontrolu u HBoxu kako bi smo je poravnali po desnoj strani
*
* Takodje treba nam logika za pomeranje prozora jer smo pored buttona (iks) uklonili i ivice pozornice
* znaci napravicemo 2 promenljive na pocetku tipa double
* Takodje primenicemo 2 dogadjaja
**/

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragOffsetX=event.getScreenX()-stage.getX();
                dragOffsetY=event.getScreenY()-stage.getY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX()-dragOffsetX);
                stage.setY(event.getScreenY()-dragOffsetY);
            }
        });
        //za stilizovanje koristimo CSS,dodacemo jedan fajl u resources
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
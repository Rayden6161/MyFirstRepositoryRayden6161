package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
//ZNACI OVDE NE IDU GETTERI I SETTERI JER TO IDE U MODELU LOGICNO A OVO JE KONTROLER.
public class PersonController implements Initializable {
    /*@FXML //uz pomoc ove anotacije mozemo baratati kontrolama iz UI-a(add_person.fxml)...
    private TextField firstName, lastName, email, address, country;*/

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField address;
    @FXML
    private TextField country;

    /*sto se tice naziva TextField polja da bi im pristupili i njima baratali u fxml(UI) moramo unutar
     * fxml(UI) iliti add_person.fxml-a dodati u TextField   fx:id="pa onda ide identican ispis
     * vrednosti sa ovim nazivima u kontroleru.Na taj nacin kad to realizujemo mozemo pristupiti tj baratati
     * TextField poljima..."*/
    Person person;

    public PersonController() {

    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ovo je metoda koja biva automatski pozvana od strane loadera java fx-a u nakon kreiranja  UI-a
        //
        person = new Person();
        //znaci ovo su propertiji ove person instance.

        firstName.textProperty().bindBidirectional(person.firstNameProperty());
        lastName.textProperty().bindBidirectional(person.lastNameProperty());
        email.textProperty().bindBidirectional(person.emailProperty());
        address.textProperty().bindBidirectional(person.addressProperty());
        country.textProperty().bindBidirectional(person.countryProperty());
        //ovi textProperty() nisu radili dog nisam pasteovao ove importe skroz gore u projekat za vezbanje :)
        //ove propertije smo povezali sa textFieldovima forme.
        //verovatno da ima neke povezanosti sa propertijima modela(Person)...:)
    }

    @FXML
    private void savePerson() {
        if (person.isValid()) {
            person.save();
        } else {
            StringBuilder errMsg = new StringBuilder();
            ArrayList<String> errList = person.errorsProperty().get();
            //citanje gresaka koje smestamo unutar instance errList a to sve uz pomoc
            for (String errList1 : errList) {
                errMsg.append(errList1);
                //na kraju kad procitamo greske dodajemo iz StringBuilder insatnci
                //greske se prikazuju konzoli

            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("person can be saved");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();
        }

    }

    @FXML
    private void closeForm() {
        //ova metoda se aktivira kada se pokrene close button kontrola.
        Platform.exit();
    }

    @FXML
    private void clearPerson() {
        //kada se pokrene clear button kontrola.
        person.firstNameProperty().set("");
        person.lastNameProperty().set("");
        person.emailProperty().set("");
        person.addressProperty().set("");
        person.countryProperty().set("");

    }
    /*da i ove nase 3 metode koristimo tj da se aktiviraju na pritisak odredjenog tastera
     * mi moramo u UI da unutar button elementa znaci unutar Button elementa utisnemo
     * onAction ="#savePerson"
     * OVO # NAM KAZE DA JE METODA IZ KONTROLERA.
     * ovim se vezuje na pritisak odredjenog tastera */
}



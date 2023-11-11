package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Gender;
import model.Person;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PersonController implements Initializable {
	@FXML
	private TextField firstName;  //ovo su nam id vrednosti u UI-u   <TextField fx:id="firstName" promptText="First name"/>
	@FXML
	private TextField lastName;
	@FXML
	private TextField email;
	@FXML
	private ToggleGroup gender;
//	znaci ova polja se odnose na fx:id u UI...
	
	@FXML
	private Button saveBtn;
	//ovo nam je fx:id atribut u button kontroli u UI-u
	@FXML
	private Button clearBtn;
	//ovo nam je fx:id atribut u button kontroli u UI-u
	@FXML
	private Button closeBtn;
	//ovo nam je fx:id atribut u button kontroli u UI-u
	@FXML
	private Button deleteBtn;
	//ovo nam je fx:id atribut u button kontroli u UI-u
	
	@FXML
	private TableView<Person> personTableView; //ovo se odnosi na desnu stranu naseg prozora dje se prikazuju korisnici koje unosimo
	//fx:id="personTableView"  znaci ovo nam je atribut u nasem UI-u TableView elementu
	
	ObservableList<Person> persons;
	Person person;


	//znaci ovo su nam sve kontrole iznad u nasem prozoru znaci plus tabela i sveeeee a sve to imamo i u fxml...
	
	public PersonController() {
	}
	
	@FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
			persons = FXCollections.<Person>observableArrayList();
		 //lista  kojoj prosledjujemo Person
		person = new Person();
		//instanca klase modela Person
		
		firstName.textProperty().bindBidirectional(person.firstNameProperty());
		lastName.textProperty().bindBidirectional(person.lastNameProperty());
		email.textProperty().bindBidirectional(person.emailProperty());
		//recimo person.firstNameProperty() odnosi se na properti koji se kreira u klasi person znaci imamo get set i ovaj properti(to su to instanciranja propertija)...

		//povezemo polja iz fxml-a
		// (verovatno polja u ovom kontroleru koja su povezana sa poljima u UI-u tj sa njihovim atributom fx:id)
		// sa person propertijima sa bindBidirectional

		
		gender.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				//znaci imamo change event pri odabiru stavke gender...
				
				if(newValue != null) {

					
					ToggleButton selected = (ToggleButton) newValue;//nova vrednost je selektovana
					
					switch(selected.getId()) { //ukoliko nesto odaberemo vrsimo selekciju na osnovu id-ja odabrane stavke
						case "male": //ukoliko odaberemo male   <ToggleButton fx:id="male"...>
						person.genderProperty().set(Gender.MALE);//setujemo vrednost propertija na Gender.MALE(enumeratorska konstanta)
						break;
					case "female"://ukoliko odaberemo female   <ToggleButton fx:id="female"...>
						person.genderProperty().set(Gender.FEMALE); //setujemo vrednost propertija na Gender.FEMALE(enumeratorska konstanta)
						break;
					}
				}
			}
		});
		
		personTableView.prefWidthProperty().bind(personTableView.getColumns().get(2).widthProperty().multiply(2.6));
		
	}
	
	@FXML
	private void savePerson() {
		if(person.isValid()) { //ukoliko je unos validan
			
			persons = personTableView.getItems(); //dodjemo do unosa
			
			ToggleButton selected = (ToggleButton) gender.getSelectedToggle();
			//pri selektovanju gender-a se vrsi selekcija a to ispod
			switch(selected.getId()) {
			case "male": //ukoliko odaberemo male onda smestamo unos u tabeli levo
				persons.add(new Person(firstName.getText(), lastName.getText(), email.getText(), Gender.MALE));
				break;
			case "female"://ukoliko odaberemo female onda smestamo unos u tabeli levo
				persons.add(new Person(firstName.getText(), lastName.getText(), email.getText(), Gender.FEMALE));
				break;
			}
			
			personTableView.setItems(persons);
			//znaci u nasoj tabeli(desna strana) u ovoj liniji koda smestamo nase vrednosti koje smo uneli u formi
		} else {
			//u suprotnom
			
			StringBuilder errMsg = new StringBuilder();
			
			ArrayList<String> errList = person.errorsProperty().get(); //imamo listu stringova
			
			for(String errList1 : errList) { //[prodjemo kroz listu
				errMsg.append(errList1); //u nasoj listi addujemo greske
			}
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Person can be saved!"); //znaci ukoliko dodje do greske imamo ovaj ispis
			alert.setHeaderText(null);
			alert.setContentText(errMsg.toString());
			alert.showAndWait();
			errList.clear();
		}
	}
	
	@FXML
	private void closeForm() {
		Platform.exit();
	} //ovim zatvaramo nasu formu.
	
	@FXML
	private void clearPerson() {
		
		person.firstNameProperty().set(""); //resetujemo nasu formu,znaci ako nesto unesemo u formi na pritisak tastera vrsimo clear na unos u nasoj formi
		person.lastNameProperty().set("");
		person.emailProperty().set("");
		person.genderProperty().set(Gender.FEMALE);
		
		if(gender.getSelectedToggle() != null) {
			gender.getSelectedToggle().setSelected(false);
		}
	}
	
	@FXML
	private void deletePerson() {
		persons = personTableView.getItems(); //dodjemo do tih unosa
		
		int index = personTableView.selectionModelProperty().getValue().getSelectedIndex();
		persons.remove(index); //izbrisemo nas unos do kojeg smo dosli(index)
		
		personTableView.setItems(persons); //i onda se vrsi update prikaza nakon brisanja
	}
}

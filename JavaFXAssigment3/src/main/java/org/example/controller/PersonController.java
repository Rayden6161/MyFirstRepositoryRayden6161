package org.example.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import org.example.model.Bandwitch;
import org.example.model.Duration;
import org.example.model.Person;
import org.example.model.Speed;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PersonController implements Initializable {
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField address;
	@FXML
	private ToggleGroup bandwitch;

@FXML
private ToggleGroup duration;
	@FXML
	private ToggleGroup speed;
	
	@FXML
	private Button saveBtn;

	@FXML
	private Button clearBtn;

	@FXML
	private Button closeBtn;

	@FXML
	private Button deleteBtn;

	
	@FXML
	private TableView<Person> personTableView;
	
	ObservableList<Person> persons;
	Person person;


	
	public PersonController() {
	}
	
	@FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		persons = FXCollections.<Person>observableArrayList();

		person = new Person();

		
		firstName.textProperty().bindBidirectional(person.firstNameProperty());
		lastName.textProperty().bindBidirectional(person.lastNameProperty());
		address.textProperty().bindBidirectional(person.addressProperty());




		
		bandwitch.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

				
				if(newValue != null) {
					
					ToggleButton selected = (ToggleButton) newValue;//nova vrednost je selektovana
					
					switch(selected.getId()) {
						case "1":
						person.bandwitchProperty().set(Bandwitch.ONE);//setujemo vrednost propertija na Gender.MALE(enumeratorska konstanta)
						break;
						case "five":
							person.bandwitchProperty().set(Bandwitch.FIVE);//setujemo vrednost propertija na Gender.MALE(enumeratorska konstanta)
							break;
						case "ten":
							person.bandwitchProperty().set(Bandwitch.TEN);//setujemo vrednost propertija na Gender.MALE(enumeratorska konstanta)
							break;
						case "hundred":
							person.bandwitchProperty().set(Bandwitch.HUNDRED);
							break;
						case "Flat":
							person.bandwitchProperty().set(Bandwitch.FLAT);
							break;
					}
				}
			}
		});
duration.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	@Override
	public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
if(newValue != null){
ToggleButton selected =(ToggleButton) newValue;
	switch(selected.getId()) { //ukoliko nesto odaberemo vrsimo selekciju na osnovu id-ja odabrane stavke
		case "1 year":
			person.durationProperty().set(Duration.ONEYEAR);//setujemo vrednost propertija na Gender.MALE(enumeratorska konstanta)
			break;
		case "2 years":
			person.durationProperty().set(Duration.TWOYEARS);//setujemo vrednost propertija na Gender.MALE(enumeratorska konstanta)
			break;

	}
}

	}
});
speed.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	@Override
	public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
		if(newValue !=null){
			ToggleButton selected =(ToggleButton) newValue;
			if(selected !=null){
				switch (selected.getId()){
					case "2":
						person.speedProperty().set(Speed.TWO);
						break;
					case "5":
						person.speedProperty().set(Speed.FIVE);
						break;
					case "10":
						person.speedProperty().set(Speed.TEN);
						break;
					case "50":
						person.speedProperty().set(Speed.FIFTY);
						break;
					case "100":
						person.speedProperty().set(Speed.HUNDRED);
						break;
				}
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
			
			ToggleButton selected = (ToggleButton) speed.getSelectedToggle();
			Speed speed1 = (Speed) selected.getUserData();

			ToggleButton selected1 = (ToggleButton) bandwitch.getSelectedToggle();
			Bandwitch bandwitch1 =(Bandwitch) selected1.getUserData();

			ToggleButton selected2= (ToggleButton) duration.getSelectedToggle();
			Duration duration1 =(Duration) selected2.getUserData();


			switch(selected.getId()) {
			case "1":
				persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(), speed1,bandwitch1,duration1));
				break;
			case "5":
				persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(), speed1,bandwitch1,duration1));
				break;
				case "10":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(),speed1,bandwitch1,duration1));
					break;
				case "100":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(), speed1,bandwitch1,duration1));
					break;
				case "Flat":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(),speed1,bandwitch1,duration1));
					break;
			}
			switch (selected1.getId()){
				case "1 year":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(),speed1,bandwitch1,duration1));
					break;
				case "2 years":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(),speed1,bandwitch1,duration1));
					break;

			}
			switch (selected2.getId()){
				case "2":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(), speed1,bandwitch1,duration1));
					break;
				case "5":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(),speed1,bandwitch1,duration1));
					break;
				case "10":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(),speed1,bandwitch1,duration1));
					break;
				case "20":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(),speed1,bandwitch1,duration1));
					break;
				case "50":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(),speed1,bandwitch1,duration1));
					break;
				case "100":
					persons.add(new Person(firstName.getText(), lastName.getText(), address.getText(),speed1,bandwitch1,duration1));
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
			alert.setTitle("Person can be saved!");
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
		person.addressProperty().set("");
		person.bandwitchProperty().set(Bandwitch.ONE);
		person.durationProperty().set(Duration.ONEYEAR);
		person.speedProperty().set(Speed.TWO);
		
		if(bandwitch.getSelectedToggle() != null) {
			bandwitch.getSelectedToggle().setSelected(false);
		} else if (duration.getSelectedToggle() !=null) {
			duration.getSelectedToggle().setSelected(false);
		} else if (speed.getSelectedToggle() !=null){
				speed.getSelectedToggle().setSelected(false); {


		}
	}}
	
	@FXML
	private void deletePerson() {
		persons = personTableView.getItems();
		
		int index = personTableView.selectionModelProperty().getValue().getSelectedIndex();
		persons.remove(index);
		
		personTableView.setItems(persons);
	}
}

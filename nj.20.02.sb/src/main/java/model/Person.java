package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Person {
	
	private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
	//<children> <TextField fx:id="firstName" promptText="First name"/> znaci argument "firstName"naseg propertyja se odnosi na liniju koda iz UI-a ovu sto smo ovde napisali.
	private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
	private final StringProperty email = new SimpleStringProperty(this, "email", "");
	private final ObjectProperty<Gender> gender = new SimpleObjectProperty(this, "gender", Gender.FEMALE);
	//ovaj gender nam je enumerator a argument gender odnosi se na id u UI-a    fx:id="gender".... :)
	
	public Person() {
	}
	
	public Person(String firstName, String lastName, String email, Gender gender) {
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.email.set(email);
		this.gender.set(gender);
	}
	
	public Person(String firstName) {
		this.firstName.set(firstName);
	}
	
	public Person(String firstName, String lastName) {
		this.firstName.set(firstName);
		this.lastName.set(lastName);
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public String getEmail() {
		return email.get();
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	public Gender getGender() {
		return gender.get();
	}
	
	public ObjectProperty<Gender> genderProperty() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender.set(gender);
	}
	
	private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());
	
	public ObjectProperty<ArrayList<String>> errorsProperty() {
		return errorList;
	}
	//ukoliko nije validno onda se u ovoj listi u ObjectProperty ispisuju tj ubacuju tekstovi ispod u zavisnosti od kombinacije.
	
	public boolean isValid() {
		
		boolean isValid = true;
		
		if(firstName.get() != null && firstName.get().equals("")) {
			errorList.getValue().add("First name can't be empty!");
			isValid = false;
		}
		if(lastName.get().equals("")) {
			errorList.getValue().add("Last name can't be empty!"); //ovo su te validnosti...
			isValid = false;
		}
		if(email.get().equals("")) {
			errorList.getValue().add("Email can't be empty!");
			isValid = false;
		}
		return isValid;
	}
	
	@Override
	public String toString() {
		return "First name: " + firstName.get() + "\n" + "Last name: " + lastName.get() + "\n" +
				"Email: " + email.get() + "\n" + "model.Gender: " + gender.get().toString();
	}
}

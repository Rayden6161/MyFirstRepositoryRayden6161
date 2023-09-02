package org.example.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Person {
	
	private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
	//<children> <TextField fx:id="firstName" promptText="First name"/> znaci argument "firstName"naseg propertyja se odnosi na liniju koda iz UI-a ovu sto smo ovde napisali.
	private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
	private final StringProperty address = new SimpleStringProperty(this, "address", "");
	private final ObjectProperty<Speed> speed = new SimpleObjectProperty(this, "speed", Speed.TWO);
	//ovaj gender nam je enumerator a argument gender odnosi se na id u UI-a    fx:id="gender".... :)
	private final ObjectProperty<Bandwitch> bandwitch = new SimpleObjectProperty(this, "bandwitch",Bandwitch.ONE);
	private final ObjectProperty<Duration> duration = new SimpleObjectProperty<>(this,"duration",Duration.ONEYEAR);
	public Person() {
	}
	
	public Person(String firstName, String lastName, String address, Speed speed,Bandwitch bandwitch,Duration duration) {
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.address.set(address);
		this.speed.set(speed);
		this.bandwitch.set(bandwitch);
		this.duration.set(duration);
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

	public String getAddress() {
		return address.get();
	}

	public StringProperty addressProperty() {
		return address;
	}

	public void setAddress(String address) {
		this.address.set(address);
	}

	public Speed getSpeed() {
		return speed.get();
	}

	public ObjectProperty<Speed> speedProperty() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed.set(speed);
	}

	public Bandwitch getBandwitch() {
		return bandwitch.get();
	}

	public ObjectProperty<Bandwitch> bandwitchProperty() {
		return bandwitch;
	}

	public void setBandwitch(Bandwitch bandwitch) {
		this.bandwitch.set(bandwitch);
	}

	public Duration getDuration() {
		return duration.get();
	}

	public ObjectProperty<Duration> durationProperty() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration.set(duration);
	}
	private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());

	public ObjectProperty<ArrayList<String>> errorsProperty() {
		return errorList;
	}

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
		if(address.get().equals("")) {
			errorList.getValue().add("Email can't be empty!");
			isValid = false;
		}
		return isValid;
	}
	
	@Override
	public String toString() {
		return "First name: " + firstName.get() + "\n" + "Last name: " + lastName.get() + "\n" +
				"Adress: " + address.get() + "\n" + "model.Bandwitch: " + bandwitch.get().toString()
		+ "\n"+"model.Duration" + duration.get().toString()+ "\n" + "model.Speed :" + speed.get().toString();

	}
}

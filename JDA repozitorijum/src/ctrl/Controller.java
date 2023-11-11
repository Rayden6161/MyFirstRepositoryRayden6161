package ctrl;

import db.Course;
import db.Db;
import ui.Ui;

import java.sql.SQLException;
import java.util.List;

public class Controller { //znaci ovom klasom prakticno spajamo klase Ui i Db i onda imamo klasu koja ce se baviti tim osnovnim aktivnostima
	//a takodje i klasu koja ce da instancira te aktivnosti i ona ce imati metode koje spajaju klase Db i Ui.
	//Tu klasu cemo nazvati Controler,znaci ona je mozak nase operacije.
	public Db db; //znaci instancirali smo nase klase znaci spajamo kockice
	public Ui ui; //preko ova dva polja Application klasa moze da dodje do ovih polja u metodi lunch();
	
	public void showCourses() throws SQLException { //I sada iz nase Application treba da pozovemo ovu metodu.
		List allCourses = db.getAllCourses(); //u nasu listu smestamo sve kurseve iz nase baze
		//stavka 1 u switch unutar Appli	cation
		System.out.println("id\ttitle\tdescription"); //prikazemo onda polja
		System.out.println("-----------------------------------------------------");
		for(Object o : allCourses) { /* //prodjemo kroz te objekte(u ovom slucaju kurseve)
		Posto ne koristimo genericke liste vec obicne ovde imamo listu objekata a ne kurseva.*/
			Course course = (Course) o; //i onda taj    o    izjednacavamo sa nasom Course klasom i to isprintamo
			System.out.println(course);
			//znaci nasa Course klasa ima samo tri svojstva koja su u bazi kolone...id,title,description
			// i metodu toString koja prikazuje nasa polja...tj ta tri polja iliti kolone sta li su....
		}
	}
	
	public void addCourse() throws SQLException {//stavka 2 u switch unutar Application
		Course toAdd = ui.getCourse(); //Prvo preuzimamo kurs od korisnika
		db.insertCourse(toAdd);//i onda taj dobijeni kurs da damo bazi podataka
	}
	
	public void deleteCourse() throws SQLException {//stavka 4 u switch od Application
		int id = ui.getCourseId(); //uzmemo id ok korisnika
		db.deleteCourse(id);//i onda taj id predamo metodi nase baze deleteCourse
	}
	
	public void updateCourse() throws SQLException { //Stavka 3 u switch u Application
		int id = ui.getCourseId(); //uzmemo id od korisnika i prosledimo ga nasoj bazi i njenoj metodi getCourse
		Course courseToUpdate = db.getCourse(id); //prosledimo nas id (gornji korak)
		if(courseToUpdate == null) { //ukoliko je kurs null  ne mozemo nista da uradimo.
			System.out.println("course does not exist in database");
			return;
		} else {
		ui.updateCourse(courseToUpdate); //ukoliko kurs postoji vrsimo update za njega.
		db.updateCourse(courseToUpdate);
		}
	}
}

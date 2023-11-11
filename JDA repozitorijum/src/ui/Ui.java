package ui;

import db.Course;

import java.util.Scanner;

public class Ui {
	
	public final static int invalidCommand = 0;
	public final static int ShowCourses = 1; //znaci ove stavke razradjujemo kao metode u nasem Controller-u
	public final static int AddCourse = 2; //takodje instanciramo u njemu Ui i Db klase,ova klasa nam sluzi za komunikaciju sa korisnikom
	public final static int UpdateCourse = 3; //u Controleru Ui.UpdateCourse,znaci zato je static plus u Kontroleru razradjujemo ove metode ctrl.UpdateCourse
	public final static int DeleteCourse = 4;
	public final static int ExitProgram = 5;
	
	public int menu() {
		System.out.println("" + ShowCourses + " – Show courses, " + AddCourse + " – Add course, " +
				                   UpdateCourse + " - Update course, " + DeleteCourse +
				                   " – Delete course, " + ExitProgram + " - Exit program");
		
		Scanner scanner = new Scanner(System.in); //vodimo racuna da ne zatvorimo skener u metodi
		int selectedOption = 0;
		while(true) {//beskonacna petlja koja se izvrsava dok korisnik ne unese ispravan broj
			try {
				selectedOption = Integer.parseInt(scanner.nextLine()); //probaj da uneses preko skenera
				
				if(selectedOption < 1 || selectedOption > 5) { //uslov
					System.out.println("Menu item does not exist, try again");
					continue; //poruka ce nastavljati da se prikazuje dok ne unesemo pravi broj
				}
				
				break;
			}
			catch(Exception e) {
				System.out.println("please, enter valid menu item number");
			}
		}
		
		//return 0;
		return selectedOption; //i na kraju vraca nam program nas odabir
	}
	
	public int getCourseId() {
		System.out.println("insert course id");
		
		Scanner scanner = new Scanner(System.in);
		int selectedId = 0;
		while(true) {
			try {
				selectedId = Integer.parseInt(scanner.nextLine()); //u nasu promenljivu smestamo nas unos u nas skener
				
				break;
			}
			catch(Exception e) {
				System.out.println("please, enter valid id"); //u suprotnom ako nije ispravan id ide poruka
			}
		}

		return selectedId;
	}
	
	public Course getCourse() {
		Course res = new Course(); //imamo 3 polja u Course klasi ,zato je instanciramo jer moramo uneti title koje je polje u toje klasi
		Scanner scanner = new Scanner(System.in);
		
		while(res.title == null) { //ovde smo uzeli title i ukoliko je prazan unosimo preko skenera ispod
			System.out.println("enter course title");
			String title = scanner.nextLine();
			if(title.isEmpty()) { //i ukoliko bi taj title bio prazan znaci da nesto nije kako trebba
				System.out.println("title cannot be empty");
				continue; //i to znaci da mozemo u novi krug petlje sto postizemo sa ovim continiue
			}
			res.title = title; //u suprotnom kazemo da je res.title=title; tj smestamo nas title u nasu lokalnu String promenljivu
			System.out.println("enter course description");
			res.description = scanner.nextLine(); //i sad u sustini mi imamo popunjen objekat klase Course koji ce nam vratiti.
			
		}
		return res; //znaci vrati nam nas res koji je instanca Course klase.
	}
	
	public void updateCourse(Course course) { //znaci ako se ne unese nista za title ili description ne menja se nista ta polja ostaju kakva su i bila
		Scanner scanner = new Scanner(System.in);
		
		//while(true) {
		System.out.println("enter course title");
		String title = scanner.nextLine();
		if(!title.isEmpty()) { //ako je korisnik uneo neku stavku iliti ako title nije prazan
			course.title = title; //menjamo tu stavku  i to smestamo u nasu lokalnu promenljivu
			//course.title znaci iz klase Course polje title
		}
		
		System.out.println("enter course description");
		String description = scanner.nextLine();
		if(!description.isEmpty()) { //ista prica
			course.description = description;//ista prica
		}
		//}
	}}
//znaci ova klasa nam je za komunikaciju sa korisnikom (znaci na mene se misli)...
//znaci kad pokrenem run imam da izaberem stavku
//znaci ove metode nemaju neku posebnu logiku sem da ako unos nije validan ispise e odgovarajuca greska
//dok u suprotnom u nasim promenljivima smestamo unos koji smo uneli preko skenera.


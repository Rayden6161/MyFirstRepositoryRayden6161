package ctrl;

import db.Db;
import ui.Ui;

import java.sql.SQLException;

public class Application { //ovaj application je samo discpecer koji govori kontroleru sta da radi
	//a sam kontroler sadrzi sve potrebne metode koje se obracaju nasim elementima da bi dali neki rezultat.
	//sto znaci da mi u kontroleru moramo da napravimo metodu show Curses.
	public static void launch() throws SQLException { //ova metoda ce da startuje nas kontroler tj njegove metode tako sto
		//ce da instancira taj kontroler.
		//ovo ce da startuje nase kontrole
		System.out.println("courses editor V 001 is running!");
		
		Controller ctrl = new Controller();
		
		ctrl.db = new Db(); //ista prica kao sa ovim ispod
		ctrl.ui = new Ui();  //zato smo u switchu naveli ctrl.ui.menu jer smo ovo izjednacili ovde sa Ui klasom
		
		/*int selectMenuItem = ctrl.ui.menu();
		System.out.println(selectMenuItem);*/
		
		mainLoop: //znaci kreiramo ime while petlje da bi case-ovi mogli da napuste i switch i while petlju verovatno pozivom imena while petlje.
		while(true) { //znaci kad odaberemo stavku i dalje ce nam nuditi prikaz,beskonacna petlja
			//System.out.println(ctrl.ui.menu());
			
			switch(ctrl.ui.menu()) { //znaci switchuj nas meni sa odabirom
			
			case Ui.ShowCourses: //ukoliko je odabir u nasoj Ui klasi ShowCourses onda
				ctrl.showCourses();//to poistovecujemo sa metodom koju smo razradili sa istim nazivom samo u Controller klasi
			break;
			
			case Ui.AddCourse: //ukoliko je unos korisnika AddCourse
				ctrl.addCourse(); //onda kontroler(a u njemu
			break;
			
			case Ui.UpdateCourse:
				ctrl.updateCourse();
			break;
			
			case Ui.DeleteCourse:
				ctrl.deleteCourse();
			break; //znaci da bi napustili i switch i while petlju tj prekinuli moramo da imenujemo
			//while petlju odmah iznad while...Sa ovim break prekidamo samo trenutnu petlju.
			
			//case 5:
			case Ui.ExitProgram:
				//System.exit(0);
				System.out.println("bye bye");
				break mainLoop; //znaci 5 je za izlazak i prosledimo mu ime while petlje i onda kompletno izlazi iz while jer je switch petlja unutar while petlje.
			//break;
			}
		}
		
	}
}
/*znaci ova klasa sluzi da pokrene neke metode unutar Controller klase koja je mozak nase
* aplikacije jer ona spaja reprezentacioni(view) i drugi deo (model)...*/
import ctrl.Application;
import db.Course;
import db.Db;
import ui.Ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application { //prvo napravimo bazu podataka i tabelu
	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root",
		 "");
		
		//Statement st = conn.createStatement();
		//Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		//st.execute("insert into courses values (null, 'core java','java fundamentals')");
		
		//ResultSet rs = st.executeQuery("select * from courses"); //znaci executeQuery nam vraca ResultSet
		
		//boolean hasNext = rs.next(); //rs.next  znaci imamo taj marker koji pici po tabeli od gore prema dole
		/*System.out.println(hasNext);
		
		hasNext = rs.next();
		System.out.println(hasNext);*/
		
		//rs.next();
		
		//int rowId = rs.getInt("id");
		/*int rowId = rs.getInt(1);
		System.out.println(rowId);*/
		 
		/*	String courseTitle = rs.getString("title");
		System.out.println(courseTitle);*/
		 
		/*rs.updateString("title", "core java");
		rs.updateRow();*/
	
		/*Scanner scanner = new Scanner(System.in);
		System.out.println(scanner.nextLine());*/
		
		//Ui ui = new Ui();
		//ui.menu();
	/*	int selectedOption = ui.menu();
		System.out.println(selectedOption);*/
		
	/*	int selectedId = ui.getCourseId();
		System.out.println(selectedId);*/
		
		/*Course course = ui.getCourse();
		System.out.println(course);*/
		
		/*Course course = new Course();
		course.id = 5;
		course.title = "My Java Course ";
		course.description = "Something about Java";
		
		System.out.println(course);
		ui.updateCourse(course); //znaci prosledili smo nasoj metodi updateCourse u ui klasi nasu instancu course u koju smo
		//umetnuli vresnosti u njena postojeca svojstva.
		System.out.println(course);*/ //i onda samo to ispisemo.
		//takodje posle ispisa dobijamo ponovo opciju da menjamo vrednosti nasih polja...
		
		//Course course= ui.getCourse(); //znaci imamo instancu
		//Db db = new Db(); //i ovde
		//db.insertCourse(course); //i db instanci i njenoj metodi prosledimo instancu course sa nasim poljima u bazi...
		//znaci moramo to proslediti jer se u njoj nalaze nasa polja...
		
		
	/*	int deleteid = ui.getCourseId(); //znaci ovo nam je za brisanje kursa
	//znaci spakujemo broj naseg id-a iz tabele u int promenljivu i prosledimo je metodi klase instanci klase Db...
		db.deleteCourse(deleteid);*/
		
		/*Course c = new Course();
		c.id = 1;
		c.title = "Hello";
		c.description = "world";
		
		db.updateCourse(c);*/  //posaljemo vrednosti polja nase instance Course klase updateCourse metodi klase  Db...
		
		//System.out.println(db.getAllCourses());
		
		//Course course = db.getCourse(3); //vrednost id-a posaljemo metodi getCourse klase Db i to sve spakujemo
		//u nasu instancu klase Course course i to isprintamo ispod.
		/*Course course = db.getCourse(5);
		System.out.println(course);*/
		
		//Application.launch();
		launch();
	}
	
}
/*Imamo tri dela sto se tice svega ovoga tj celog projekta
* 1. Deo za reprezentaciju podataka(osnovna komunikacija korisnika sa aplikacijom.
* Onime sto korisnik vidi taj deo se zove VIEW
* 2. Drugi deo se bavi samim podacima i zove se MODEL ukljucuje strukturu podataka i
* eventualno ukljucuje rad sa bazom
* 3. Treci deo sluzi da spoji prethodna dva dela i zove se kontroler.*/

package db;  //u ovoj klasi komuniciramo sa bazom.

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Db { //znaci u ovoj klasi mi komuniciramo sa nasom bazom,znaci modifikacija nasih podataka u nasoj bazi
	// Ui nam je view Course i DB je model,Db sei bavi manipulacijom iz baze podataka
	public Connection connect() throws SQLException { //znaci treba nam konekcija ka nasoj bazi
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
		//znaci ovo nismo stavili u try catch blok jer saljemo ako bude Exceptiona u glavnu aplikaciju virtuelnoj masini da ona moze da prekine aplikaciju ako nesto nije u redu sa bazom.Sve to radimo sa  throws SQLException . znaci bacamo exception glavnoj aplikaciji.
		return conn; //recimo da nam vise nije potrebna nasa konekcija onda pozivamo metodu close.
	}
	
	public void insertCourse(Course course) throws SQLException {
		Connection conn = connect();
		Statement st = conn.createStatement();
		String query =
				"insert into courses values (null, '" + course.title + "','" + course.description +
						"')";
		//kontam u ovom upitu nismo kao u preparesStatementu prvo stavljali naziv polja pa onda  "values",
		//ovde je direktno samo values mada nemamo kao u ps-u int statuc=0; ovde nam metoda ne vraca
		//nista zato je void umesto int(zato nema int status=0;),
		//Takodje moguce da direktno ide values zbog koriscenja Statement umesto PreparedStatement-a.
		st.execute(query);
		conn.close(); //zatvaranje statementa i konekcije
	}
	
	public void deleteCourse(int id) throws SQLException {
		Connection conn = connect(); //izjednacavamo promenljivu i nasu metodu iznad za konekciju
		Statement st = conn.createStatement();
		String query = "delete from courses where id = " + id; //brisi redove iliti stake po id-u nase tabele
		st.execute(query); //startuj
		st.close();//i onda zatvori nas upit
		conn.close();// i nasu konekciju zatvori takodje...
	}
	
	public void updateCourse(Course course) throws SQLException { //prihvatamo kao parametart ceo Course  jer hocemo da ga azuriramo
		Connection conn = connect();
		PreparedStatement st = conn.prepareStatement("update courses set title = ?, description = ? where id = ?");
		st.setString(1, course.title); //promena na prvoj poziciji PARAMETRA PREPAREDSTATEMENTA za naziv
		st.setString(2, course.description);//promena na drugoj poziciji PARAMETRA PREPAREDSTATEMENTA za description
		st.setInt(3, course.id);//promena na trecoj poziciji PARAMETRA PREPAREDSTATEMENTA za id
		st.execute();
		st.close();
		conn.close();
	}
	
	public List<Course> getAllCourses() throws SQLException { //ovo nece biti void jer mora DA VRATI listu objekata nasih course-eva.
		List res = new ArrayList(); //znaci kad pokrenemo imamo praznu listu
		//e sad,da bi smo preuzeli courseve iz baze podataka i poslali nazad ovoj glavnoj aplikaciji treba nam opet jdbc:...
		Connection conn = connect();
		Statement st = conn.createStatement();
		String query = "select * from courses";
		ResultSet rs = st.executeQuery(query); //ResultSet je takodje deo jdbc-a,takodje koristimo st.executeQuery umesto execute...
		while(rs.next()) { //ovom while petljom prolazimo kroz tu listu i treba da je popunimo objektima
			//sve dok ima podataka pravimo objekat klase course,znc ispod
			Course course = new Course();
			course.id = rs.getInt("id"); //objekte popunjavamo podacimo koje uzimamo iz nase baze
			course.title = rs.getString("title");
			course.description = rs.getString("description");
			
			res.add(course); //konacno kad smo napravili objekat dodajemo ga u listu kurseva...
		}
		
		st.close();
		conn.close();
		
		return res; //prikazujemo nasu listu u aplikaciji koju smo popunili objektima iz nase baze.
	}
	
	public Course getCourse(int id) throws SQLException { //kurs koji uzmemo od korisnika u Ui posaljemo ga ovoj metodi u Controleru
		Course course = null; //postavljamo na null po onda na kraj metode vracamo novu vrednost course
		
		Connection conn = connect(); //opet nam treba konekcija
		
		PreparedStatement st = conn.prepareStatement("select * from courses where id = ?"); //evo ovde je formiran upit
		st.setInt(1, id); //prva i jedina pozicija naseg parametra(u zagradi)
		
		ResultSet rs = st.executeQuery();  //znaci ovde smo iz zagrade izbacili nas upit query jer je upit vec formiran iznad
		if(rs.next()){
			course = new Course(); //znaci kreiramo novi objekat
			course.id = rs.getInt("id"); //ovo je id koji je korisnik uneo prilikom pozivanja ove metode getCourse.
			//znaci recimo db.getCourse(i onda prosledimo id ) znaci ovo sve u mainu...
			course.title = rs.getString("title");
			course.description = rs.getString("description");
		}
		
		rs.close();
		st.close();
		conn.close();
		
		return course; //ako bi smo hteli da update-jemo ili bilo sta u bazi mozda ne bi mogli iz baze to da uradimo
		//nema smisla da ovde stavimo da vrace null...
	}
}
//znaci ova klasa nam je za komunikacijom sa nasom bazom podataka, verovatno cemo posle u kontroleru
//sastaviti sve kocikce
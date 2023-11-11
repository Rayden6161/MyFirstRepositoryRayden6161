package db;

public class Course { /*znaci napravili smo ovu nasu klasu jer u nasoj bazi podataka imamo ova tri polja
znaci bice tu i instanciranja i raznoraznih kombinacija.*/
	public int id;
	public String title;
	public String description;

	@Override
	public String toString() {
		return id + "\t" + title + "\t" + description;
	}
}/*znaci prosledimo celu klasu Ui metodi getCourse znaci instanciramo unutar te metode
da bi nam vratilo nas kurs zato se i zove metoda getCourse.*/

import java.time.LocalDate;
import java.util.HashMap;
public class Main {
    public static void main(String[] args) {
HashMap<String,Object>person = new  HashMap();
person.put("name","John Smith");
person.put("age",33);
person.put("niz",new String[]{"swimming","paintinng"}); //znaci ovde je kljuc "niz" instance of ovog String niza
        //I onda taj niz saljemo u novi hobbies..
//person.put("birthday", LocalDate.parse("08-04-1987"));
//nt newAge = (int)person.get("age")+7;

for(String key: person.keySet()){ //znaci da bi izvukli objekat za kljucevi key moramo pozvati metodu nad nasom HashMapom ciji su kljucevi String tipa.
    //Tip String od naziva HashMape person i pozovemo keySet metodu
    if(person.get(key)instanceof String[]){//ukoliko je pozvan key deo String niza
        String[] hobbies =(String[]) person.get(key); //onda ga iz postojeceg niza saljemoo  u novi hobbies
for (String H: hobbies){
    System.out.println(H);
}
    }
   else{ System.out.println(key+ "=>" + person.get(key));} //onda istampamo kljuceve  -.> vrednost.
}





    }
}
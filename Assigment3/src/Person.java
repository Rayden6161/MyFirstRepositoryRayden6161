public class Person {
    String ime;
    String prezime;
    String grad;

    public Person(String ime, String prezime,String grad) {
        this.ime = ime;
        this.prezime = prezime;
        this.grad = grad;
    }
    String getInfo(){
        return "Ime :" + ime  + "\n Prezine :" + prezime + "\n grad "
                + grad + "\n";
    }
}

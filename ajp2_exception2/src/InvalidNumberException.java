public class InvalidNumberException  extends Exception
//Ideja da mi pisemo svoje izuzetke,napravimo klasu koja nasledjuje Exception
    //NAJMANJE sto nam je trebalo je da nasledimo klasu
    //ipak mozemo i napraviti svoj konstruktor sto cemo i uraditi
{
    private int  number;
    public InvalidNumberException(int number){
this.number = number;

    }
    public int getNumber(){return number;}
}

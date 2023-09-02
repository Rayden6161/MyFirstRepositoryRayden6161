package db;

public class Worker {

    private String name;
    private int ageNumber;

    private String adress;
    private int sallary;
    private int id;

    public Worker(String name,int ageNumber, String adress, int sallary,int id) {

        this.name= name;
        this.ageNumber = ageNumber;
        this.adress = adress;
        this.sallary = sallary;
        this.id =id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeNumber() {
        return ageNumber;
    }

    public void setAgeNumber(int ageNumber) {
        this.ageNumber = ageNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getSallary() {
        return sallary;
    }

    public void setSallary(int sallary) {
        this.sallary = sallary;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Worker(String marko, int i, String s, int i1) {

    }

    @Override
    public String toString() {
        return  name + "\t" + ageNumber +"\t" + adress + "\t" +sallary+ "\t" + id;
}

}

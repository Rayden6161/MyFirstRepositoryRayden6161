public class Proizvod {
    private String naziv;
    private double cena;
    private String kategorija;

    public Proizvod(String naziv , double cena, String kategorija) {
        this.naziv = naziv;
        this.cena = cena;
        this.kategorija=kategorija;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCena() {
        return cena;
    }

    public String getKategorija() {
        return kategorija;
    }

    @Override
    public String toString() {
        return "Proizvod{" +
                "naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", kategorija='" + kategorija + '\'' +
                '}';
    }
}

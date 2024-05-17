import java.time.LocalDate;

public class Tilitapahtuma {
    LocalDate paivamaara;
    public LocalDate getPaivamaara() {
        return paivamaara;
    }

    public void setPaivamaara(LocalDate paivamaara) {
        this.paivamaara = paivamaara;
    }

    double summa;
    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    String toimittajanNimi;

    public String getToimittajanNimi() {
        return toimittajanNimi;
    }

    public void setToimittajanNimi(String toimittajanNimi) {
        this.toimittajanNimi = toimittajanNimi;
    }

    public Tilitapahtuma() {

    }

    public Tilitapahtuma(LocalDate paivamaara, double summa, String toimittajanNimi) {
        this.paivamaara = paivamaara;
        this.summa = summa;
        this.toimittajanNimi = toimittajanNimi;
    }


    @Override
    public String toString() {
        return paivamaara + ": " + summa + ", " + toimittajanNimi;
    }
}

import java.util.ArrayList;
import java.util.List;

public class TilioteKuukausi {
    String kuukaudenNimi;
    public String getKuukaudenNimi() {
        return kuukaudenNimi;
    }

    public void setKuukaudenNimi(String kuukaudenNimi) {
        this.kuukaudenNimi = kuukaudenNimi;
    }

    int kuukaudenVuosi;

    public int getKuukaudenVuosi() {
        return kuukaudenVuosi;
    }

    public void setKuukaudenVuosi(int kuukaudenVuosi) {
        this.kuukaudenVuosi = kuukaudenVuosi;
    }

    List<Tilitapahtuma> tilitapahtumat = new ArrayList<Tilitapahtuma>();

    public TilioteKuukausi() {

    }

    public TilioteKuukausi(String kuukaudenNimi, int kuukaudenVuosi) {
        this.kuukaudenNimi = kuukaudenNimi;
        this.kuukaudenVuosi = kuukaudenVuosi;
    }

    @Override
    public String toString() {
        return kuukaudenNimi + ", " + kuukaudenVuosi + ": " + tilitapahtumat;
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TilioteOhjelmisto {

    public List<Tilitapahtuma> etsiVuokratulot(List<Tilitapahtuma> tilitapahutmat) {
        return null;
    }

    public HashMap<Integer, Double> etsiTulotKuukausittain(List<Tilitapahtuma> tilitapahtumat) {
        return null;
    }

    public static void main(String[] args) {
        Scanner tiedostonlukija;
        List<Tilitapahtuma> tilitapahtumat = new ArrayList<>();
        try {
            tiedostonlukija = new Scanner(new File("tilitapahtumat.txt"));

            String rivi = tiedostonlukija.nextLine();

            while (tiedostonlukija.hasNextLine()) {
                rivi = tiedostonlukija.nextLine();
                String[] osat = rivi.split(";");
                String paivamaara = osat[0];
                String[] paivanOsat = paivamaara.split("/");
                int vuosi = Integer.parseInt(paivanOsat[0]);
                int kuukausi = Integer.parseInt(paivanOsat[1]);
                int paiva = Integer.parseInt(paivanOsat[2]);

                LocalDate paivamaaraLocal = LocalDate.of(vuosi, kuukausi, paiva);
                double summa = Double.parseDouble(osat[1].replace(',', '.'));
                String nimi = osat[5];

                Tilitapahtuma tilitapahtuma = new Tilitapahtuma(paivamaaraLocal, summa, nimi);
                tilitapahtumat.add(tilitapahtuma);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(tilitapahtumat);
    }
}

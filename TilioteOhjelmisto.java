import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TilioteOhjelmisto {

    public static List<Tilitapahtuma> etsiVuokratulot(List<Tilitapahtuma> tilitapahtumat) {
        return tilitapahtumat.stream()
                .filter((tilitapahtuma -> tilitapahtuma.getToimittajanNimi().equals("Jarvinen MATIAS PETTERI")))
                .toList();
    }

    public static HashMap<Integer, Double> etsiTulotKuukausittain(List<Tilitapahtuma> tilitapahtumat) {
        HashMap<Integer, Double> tulot = new HashMap<>();
        for (Tilitapahtuma tilitapahtuma : tilitapahtumat) {
            if (tilitapahtuma.getSumma() < 0) {
                continue;
            }
            Integer kuukausi = tilitapahtuma.getPaivamaara().getMonthValue();
            double nykyinenSumma = tulot.getOrDefault(kuukausi, 0.0);
            tulot.put(kuukausi, nykyinenSumma + tilitapahtuma.getSumma());
        }
        return tulot;
    }

    public static List<Tilitapahtuma> etsiRuokalaKulut(List<Tilitapahtuma> tilitapahtumat) {
        return tilitapahtumat.stream()
                .filter((tilitapahtuma -> tilitapahtuma.getToimittajanNimi().contains("Compass Group Finland")))
                .toList();
    }

    public static List<Tilitapahtuma> etsiPolttoainekulut(List<Tilitapahtuma> tilitapahtumat) {
        return tilitapahtumat.stream()
                .filter((tilitapahtuma -> tilitapahtuma.getToimittajanNimi().toLowerCase().contains("neste")
                        || tilitapahtuma.getToimittajanNimi().toLowerCase().contains("teboil")))
                .toList();
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

        List<String> kuukaudetListassa = List.of("Tammikuu", "Helmikuu", "Maaliskuu", "Huhtikuu", "Toukokuu", "Kesäkuu",
                "Heinäkuu", "Elokuu", "Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu");

        System.out.println("Tulostetaan koulun Paaraide ruokalan ruokailukulut tiedoston aikajanteelta:");
        System.out
                .println("AMICA PAARAIDE RUOKAMENOT TALLA JAKSOLLA: " + (int) etsiRuokalaKulut(tilitapahtumat).stream()
                        .mapToDouble((tilitapahtuma -> tilitapahtuma.getSumma())).sum());
        System.out.println("Tulostetaan autoilun polttoainekuluja tiedoston aikajanteella: ");
        System.out.println("POLTTOAINEKULUT TALLA JAKSOLLA: " + (int) etsiPolttoainekulut(tilitapahtumat).stream()
                .mapToDouble((tilitapahtuma -> tilitapahtuma.getSumma())).sum() + " euroa");
        System.out.println("Marraskuun tulot");
        System.out.println("11: " + etsiTulotKuukausittain(tilitapahtumat).get(11).intValue());
    }
}

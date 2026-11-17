import java.util.Random;
import java.util.Scanner;
//Täringumäng kus genereeritakse juhuslik arv 0-99 ja enne seda kasutaja peab arvama, kas number on väiksem kui 50 või suurem/võrdne kui 50.
public class Täringumäng extends Mang {
    private Random rnd = new Random();
    private Scanner scan = new Scanner(System.in);

    //Käivitab täringumängu
    //Mängija saab valida kas arv tuleb alla või üle/võrdne 50-ne. Võimalikud arvud on 0-99
    @Override
    public double mangi(Kasutaja mangija) {
        //Menüü, et mängija saaks mitu korda järjest mängida, aga annab ka valiku vaadata oma krediite + mängust lahkuda.
        while (true) {
            System.out.println("Täringumäng:");
            System.out.println("1 - mängi, 2 - tagasi, 3 - krediidid");
            String valik = scan.nextLine();

            if (valik.equals("1")) {

                System.out.println("Alla 50 või Üle/Võrdne 50? (A/Ü)");
                String valik2 = scan.nextLine();

                System.out.println("Sisesta panus:");
                System.out.println("Sinu krediidid: " + mangija.getKrediidid());
                double panus = scan.nextDouble();
                scan.nextLine();
                //Kontroll, et panus oleks suurem kui 0.
                if (panus <= 0) {
                    System.out.println("Panus peab olema suurem kui 0!");
                    continue;
                }
                //Kiire panuse kontroll, et ei saaks panustada rohkem kui võimalik.
                if (panus > mangija.getKrediidid()) {
                    System.out.println("Pole piisavalt krediiti!");
                    continue;
                }
                //Võtame kasutaja kontolt panuse maha.
                mangija.setKrediidid(mangija.getKrediidid() - panus);

                int arv = rnd.nextInt(100);
                System.out.println("Tulemus: " + arv);

                boolean voit = false;
                //Võidu kontroll.
                if (valik2.equalsIgnoreCase("ü") && arv >= 50) {
                    voit = true;
                } else if (valik2.equalsIgnoreCase("a") && arv < 50) {
                    voit = true;
                }
                //Kui on võit, siis kasutaja saab 2* oma panuse tagasi ja kui kaotas, siis jäi panusest ilma.
                if (voit) {
                    double voitSumma = panus * 2;
                    System.out.println("Võitsid! +" + voitSumma);
                    mangija.setKrediidid(mangija.getKrediidid() + voitSumma);
                } else {
                    System.out.println("Kaotasid!");
                }
                //Näitab aktiivselt kasutaja panust.
                System.out.println("Sinu krediidid: " + mangija.getKrediidid());

            } else if (valik.equals("2")) {
                break;

            } else if (valik.equals("3")) {
                System.out.println("Sinu krediidid: " + mangija.getKrediidid());

            } else {
                System.out.println("Vale sisend!");
            }
        }

        return 0;
    }
}
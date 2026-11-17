//MEIE EESMÄRK EI OLE PROPAGEERIDA HASARTMÄNGUDE MÄNGIMIST. HASARTMÄNGUD EI OLE SOBIV VIIS
//RAHALISTE PROBLEEMIDE LAHENDAMISEKS. 😁👍

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // kasutaja loomine
        Scanner scan = new Scanner(System.in);

        System.out.println("Sisesta oma nimi/pseudonüüm: ");
        String nimi = scan.nextLine();

        Kasutaja mangur = new Kasutaja(nimi, 0, 0);

        boolean lopeta = false;
        while (!lopeta) {
            System.out.println("Vali mida soovid teha:  ");
            System.out.println("Sinu krediidid ----- " + mangur.getKrediidid());
            System.out.println("1 - juurdemakse, 2 - väljamakse, 3 - Täringumäng, 4 - slot, 5 - rulett, 6 - BlackJack, 7 - MineSweeper  8 - välju");
            String valik = scan.nextLine();
            if (valik.equals("1")){
                krediidi_juurdemakse(mangur, scan);
            } else if (valik.equals("2")){
                valjamakse(mangur, scan);
            } else if (valik.equals("3")) {
                taringMang(mangur);
            }else if (valik.equals("4")){
                slot(mangur);
            } else if (valik.equals("5")) {
                rulett(mangur);
            } else if (valik.equals("6")) {
                BlackJack(mangur);
            }else if (valik.equals("7")){
                minesweeper(mangur);
            } else if (valik.equals("8")) {
                break;
            } else {
                System.out.println("Sisesta õige sisend!");
                continue;
            }

        }



    }
    // juurdemakse värk
    public static void krediidi_juurdemakse(Kasutaja mangija, Scanner scan){
        boolean labitud = true;
        while (labitud) {
                System.out.println("Sisesta palju soovid juurde lisada (limiit 10000). Kui sa ei soovi lisada vajuta 0.");
                double summa = scan.nextDouble();
                scan.nextLine();
                if (summa > 10000) {
                    System.out.println("Vali uuesti. Juurde lisamise limiit on 10000");
                } else {
                    mangija.juurdemakse(summa);
                    System.out.println(mangija.getKrediidid() + " lisatud su kontole ");
                    labitud = false;
                }
        }
    }
    public static void valjamakse(Kasutaja mangija, Scanner scan){
        System.out.println("Sisesta summa mida soovid välja võtta:");
        double summa = scan.nextDouble();
        scan.nextLine();

        if (summa > mangija.getKrediidid()) {
            System.out.println("Pole piisavalt raha!");
        } else {
            mangija.valjamakse(summa);
            System.out.println("Väljamakse tehtud. Alles: " + mangija.getKrediidid());
        }
    }
    public static void taringMang(Kasutaja mangur) {
        Mang mang = new Täringumäng();
        double tulemus = mang.mangi(mangur);

        mangur.muudaKrediiti(tulemus);

    }
    public static void rulett(Kasutaja mangur){
        Mang mang = new Rulett();
        double mangimine = mang.mangi(mangur);

    }
    public static void slot(Kasutaja mangur){
        Mang mang = new Slot();
        double mangimine = mang.mangi(mangur);
    }
    public static void BlackJack(Kasutaja mangur){
        Mang mang = new MustJaak();
        double mangimine = mang.mangi(mangur);
    }
    public static void minesweeper(Kasutaja mangur){
        Mang mang = new minesweeper();
        double mangimine = mang.mangi(mangur);
    }

}
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Rulett extends Mang {
    private Kasutaja mangija;

    @Override
    public double mangi(Kasutaja mangija) {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Kui soovid mängida ruletti. 1 - mängi, 2 - välju, 3 - kuva krediite "); // Mängija valib, mida soovib teha
            String mangimine = scan.nextLine(); // muutuja mangimine võtab valiku vastu
            if (mangimine.equals("1")) {
                System.out.println(mangija.getKrediidid() + " , sinu krediidid.");
                System.out.println("Sisesta oma panus");
                double panus = scan.nextDouble();
                if (panus <= mangija.getKrediidid()) { //Panus peab alati olema väiksem võrdne krediidi kogusega
                    mangija.setKrediidid(mangija.getKrediidid() - panus);
                    System.out.println("Vali oma värv (Punane = 2x / Must = 2x / Roheline = 14x)");
                    System.out.println("Sisesta, milline värv (P = punane, M = must, R = roheline)"); // Mängija saab valida kolme värvi vahel, mille kõrval on panuse suurendamise korrutis. Punast ja Must esineb sama palju, kuid rohelist mitte nii palju
                    scan.nextLine();
                    String valik = scan.nextLine().toLowerCase();
                    if (valik.length() < 2) {
                        List<Integer> punased = List.of(1, 3, 5, 7, 9, 13, 15, 17, 19, 21, 23);
                        List<Integer> mustad = List.of(2, 4, 6, 8, 10, 14, 16, 18, 20, 22, 24);
                        List<Integer> rohelised = List.of(0, 25, 12, 11);
                        int random = (int) ((Math.random() * 26));
                        if (punased.contains(random)) { // Kui random valis numbri, mis on punases listis.
                            if (valik.equals("p")) {  //Kui mängija valis punase värvi, siis ta võitis
                                System.out.println(random);
                                System.out.println("Sa võitsid");
                                System.out.println("+" +panus * 2 + " on sinu võidusumma");
                                mangija.setKrediidid(mangija.getKrediidid() + panus * 2);
                                System.out.println(mangija.getKrediidid() + " on su krediidid");
                            } else { // kui mängija valis mingi teise, siis ta kaotas
                                System.out.println("Punane oli");
                                System.out.println("Sa kaotasid aga proovi uuesti");
                                System.out.println("Su krediidid: " + mangija.getKrediidid());


                            }
                        } else if (mustad.contains(random)) {// Kui random valis numbri, mis on punases listis.
                            if (valik.equals("m")) { // Kui mängija valis musta
                                System.out.println("Must oli");
                                System.out.println("Tubli! Sa võitsid, ära anna kunagi alla.");
                                System.out.println("+"+panus * 2 + " on sinu võidusumma");
                                mangija.setKrediidid(mangija.getKrediidid() + panus * 2);
                                System.out.println(mangija.getKrediidid() + " on su krediidid");

                            } else {// kui mängija valis mingi teise, siis ta kaotas
                                System.out.println("Must oli");
                                System.out.println("Sa kaotasid aga proovi uuesti. 99% mängijatest lõpetavad enne oma suurt võitu.");
                                System.out.println("Su krediidid: " + mangija.getKrediidid());


                            }
                        } else if (rohelised.contains(random)) {// Kui random valis numbri, mis on punases listis.
                            if (valik.equals("r")) { // kui mängija valis rohelise
                                System.out.println("Roheline oli");
                                System.out.println("Sa võitsid, ära kunagi anna alla.");
                                System.out.println("+"+panus * 14 + " on sinu võidusumma");
                                mangija.setKrediidid(mangija.getKrediidid() + panus * 14);
                                System.out.println(mangija.getKrediidid() + " on su krediidid");
                            } else {// kui mängija valis mingi teise, siis ta kaotas
                                System.out.println("Roheline oli");
                                System.out.println("Sa kaotasid aga proovi uuesti, 99% lõpetavad enne oma suurt võitu!");
                                System.out.println("Su krediidid: " + mangija.getKrediidid());
                            }

                        }
                    } else {
                        System.out.println("Vale sisend");
                        mangija.setKrediidid(mangija.getKrediidid() + panus);
                        System.out.println(mangija.getKrediidid() + " on hetkel sinu krediidid.");
                        break;
                    }

                } else if (panus > mangija.getKrediidid()) {
                    System.out.println("Sul pole piisavalt vahendeid");
                    continue;

                }
            } else if (mangimine.equals("2")) {
                System.out.println("Sinu krediidid " + mangija.getKrediidid());
                break;
            } else if (mangimine.equals("3")) {
                System.out.println(mangija.getKrediidid() + " on sinu krediidid.");
            } else {
                System.out.println("Vale sisend");
                continue;
            }
        }
        return 0;
    }
}

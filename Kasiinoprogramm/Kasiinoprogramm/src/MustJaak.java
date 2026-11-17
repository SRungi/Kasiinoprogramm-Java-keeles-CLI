import javax.xml.transform.Source;
import java.io.FilterOutputStream;
import java.util.*;

public class MustJaak extends Mang {
    private Kasutaja mangur;

    @Override
    public double mangi(Kasutaja mangur) {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Kui soovid mängida Blackjacki. 1 - mängi, 2 - tagasi menüüsse, 3 - kuva krediite ");
            String mangimine = scan.nextLine();
            if (mangimine.equals("1")) {
                List<Kaart> kasutatud = new ArrayList<>(); // kui kaart kasutatakse ära siis lisatakse siia.
                List<Kaart> kaardipakk = new ArrayList<>();
                List<Kaart> kaes = new ArrayList<>(); // mängija käsi
                List<Kaart> dealer = new ArrayList<>(); // dealeri käsi
                //kaardipaki loomine
                for (int i = 1; i < 10; i++) {
                    kaardipakk.add(new Kaart("ärtu", i));
                }
                for (int i = 0; i < 3; i++) {
                    kaardipakk.add(new Kaart("ärtu", 10));
                }
                for (int i = 2; i < 10; i++) {
                    kaardipakk.add(new Kaart("risti", i));
                }
                for (int i = 0; i < 3; i++) {
                    kaardipakk.add(new Kaart("poti", 10));
                }
                for (int i = 2; i < 10; i++) {
                    kaardipakk.add(new Kaart("ruutu", i));
                }
                for (int i = 0; i < 3; i++) {
                    kaardipakk.add(new Kaart("ruutu", 10));
                }
                for (int i = 2; i < 10; i++) {
                    kaardipakk.add(new Kaart("risti", i));
                }
                for (int i = 0; i < 3; i++) {
                    kaardipakk.add(new Kaart("risti", 10));
                }
                for (int i = 0; i < 2; i++) {
                    kaardipakk.add(new Kaart("ärtu äss", 1));
                }
                for (int i = 0; i < 2; i++) {
                    kaardipakk.add(new Kaart("ruutu äss", 1));
                }
                for (int i = 0; i < 2; i++) {
                    kaardipakk.add(new Kaart("risti äss", 1));
                }
                for (int i = 0; i < 2; i++) {
                    kaardipakk.add(new Kaart("poti äss", 1));
                }


                Collections.shuffle(kaardipakk); // segamine


                if (kaardipakk.size() > 10) { // enne mängu minemist kontrollitakse kas kaardipakis on vähemalt 10 kaarti, kui ei ole siis lisatakse kasutatud arraylistist juurde
                    System.out.println("Sisesta oma panus: ");
                    System.out.println("Sinu krediidid: " + mangur.getKrediidid());
                    double panus = scan.nextDouble();
                    scan.nextLine();
                    if (panus <= mangur.getKrediidid()) { // panuse kontroll
                        mangur.setKrediidid(mangur.getKrediidid() - panus);


                            // esimene kaart
                            Kaart esimene_kaart = kaardipakk.get(0);
                            kaardipakk.remove(esimene_kaart);
                            kasutatud.add(esimene_kaart);

                            // teine kaart
                            Kaart teine_kaart = kaardipakk.get(0);
                            kaardipakk.remove(teine_kaart);
                            kasutatud.add(teine_kaart);

                            // hand
                            kaes.add(esimene_kaart);
                            kaes.add(teine_kaart);



                            //SUMMAD
                            int summa = 0;
                            int dealeri_summa = 0;

                            // DEALERi esimene kaart
                            Kaart dealeri1_kaart = kaardipakk.get(0);
                            kaardipakk.remove(dealeri1_kaart);
                            kasutatud.add(dealeri1_kaart);

                            //dealeri teine kaart
                            Kaart dealeri2_kaart = kaardipakk.get(0);
                            kaardipakk.remove(dealeri2_kaart);
                            kasutatud.add(dealeri2_kaart);

                            dealer.add(dealeri1_kaart);
                            dealer.add(dealeri2_kaart);



                            if (esimene_kaart.getNumber() == 1) { // Kui esimene kaart, mis valiti on äss
                                summa = 11 + teine_kaart.getNumber();
                            } else if (teine_kaart.getNumber() == 1) {// Kui teine kaart, mis valiti on äss
                                summa = esimene_kaart.getNumber() + 11;
                            } else if (esimene_kaart.getNumber() == 1 && teine_kaart.getNumber() == 1) { // Kui on mõlemad ässad
                                summa = 12;
                            } else {
                                summa = esimene_kaart.getNumber() + teine_kaart.getNumber();
                            }


                            if (dealeri1_kaart.getNumber() == 1) {// Kui esimene kaart, mis valiti on äss
                                dealeri_summa += dealeri2_kaart.getNumber() + 11;
                            } else if (dealeri2_kaart.getNumber() == 1) {// Kui teine kaart, mis valiti on äss
                                dealeri_summa += dealeri1_kaart.getNumber() + 11;
                            } else if (dealeri1_kaart.getNumber() == 1 && dealeri2_kaart.getNumber() == 1) {// Kui on mõlemad ässad
                                dealeri_summa += 12;
                            } else {
                                dealeri_summa += dealeri1_kaart.getNumber() + dealeri2_kaart.getNumber();
                            }

                            System.out.println("Sinu summa on " + summa);
                            System.out.println("Kaardid " + kaes);

                            while (summa < 21) { // niikaua kuni mängija summa on alla 21;

                                System.out.println("HIT või STAND [H/S]");
                                String valik2 = scan.nextLine().toLowerCase();

                                if (valik2.equals("h")) { // kui mängija valis hit
                                    if ((kaardipakk.get(0).getMast().equals("ärtu äss ") || kaardipakk.get(0).getMast().equals("ruutu äss") || kaardipakk.get(0).getMast().equals("risti äss") || kaardipakk.get(0).getMast().equals("poti äss")) && summa + 11 > 21) { // kui kaart on äss ja  praegusele summale liites 11 on suurem kui 21
                                        summa += 1;
                                        kaes.add(kaardipakk.get(0));
                                        System.out.println("Sinu summa on " + summa);
                                        kasutatud.add(kaardipakk.get(0));
                                        System.out.println("Kaardid " + kaes);
                                        kaardipakk.remove(kaardipakk.get(0));
                                    } else if ((kaardipakk.get(0).getMast().equals("ärtu äss") || kaardipakk.get(0).getMast().equals("ruutu äss") || kaardipakk.get(0).getMast().equals("risti äss") || kaardipakk.get(0).getMast().equals("poti äss")) && summa + 11 <= 21) { // kui kaart on äss ja  praegusele summale liites 11 on väiksem võrdne kui 21
                                        summa += 11;
                                        kaes.add(kaardipakk.get(0));
                                        System.out.println("Sinu summa on " + summa);
                                        kasutatud.add(kaardipakk.get(0));
                                        System.out.println("Kaardid " + kaes);
                                        kaardipakk.remove(kaardipakk.get(0));
                                    } else {
                                        summa += kaardipakk.get(0).getNumber();
                                        kaes.add(kaardipakk.get(0));
                                        System.out.println("Sinu summa on " + summa);
                                        kasutatud.add(kaardipakk.get(0));
                                        System.out.println("Kaardid " + kaes);
                                        kaardipakk.remove(kaardipakk.get(0));
                                    }
                                    if (summa == 21) {
                                        System.out.println("JACKPOTTT");
                                        System.out.println(summa);
                                        System.out.println("Su võidu käsi " + kaes);
                                        System.out.println("Võidusumma: +" + panus * 2);
                                        mangur.setKrediidid(mangur.getKrediidid() + panus * 2);
                                        System.out.println("Su krediidid: " + mangur.getKrediidid());
                                    } else if (summa > 21) {
                                        System.out.println("Summa läks lõhki " + summa);
                                        System.out.println("Su käsi " + kaes);
                                        System.out.println("Su krediidid " + mangur.getKrediidid());
                                    } else {
                                        continue;
                                    }

                                } else if (valik2.equals("s")) { // Kui mängija valis stand
                                    // Kui dealer kaartide summa on 17 või üle, siis ta ei hiti enam.
                                    while (dealeri_summa < 17) {
                                        if ((kaardipakk.get(0).getMast().equals("ärtu äss") || kaardipakk.get(0).getMast().equals("ruutu äss") || kaardipakk.get(0).getMast().equals("risti äss") || kaardipakk.get(0).getMast().equals("poti äss")) && dealeri_summa + 11 <= 21) {// kui kaart on äss ja  praegusele summale liites 11 on suurem kui 21
                                            dealeri_summa += kaardipakk.get(0).getNumber();
                                            dealer.add(kaardipakk.get(0));
                                            kasutatud.add(kaardipakk.get(0));
                                            kaardipakk.remove(0);
                                        } else if ((kaardipakk.get(0).getMast().equals("ärtu äss") || kaardipakk.get(0).getMast().equals("ruutu äss") || kaardipakk.get(0).getMast().equals("risti äss") || kaardipakk.get(0).getMast().equals("poti äss")) && dealeri_summa + 11 > 21) {// kui kaart on äss ja  praegusele summale liites 11 on suurem kui 21
                                            dealeri_summa += 1;
                                            dealer.add(kaardipakk.get(0));
                                            kasutatud.add(kaardipakk.get(0));
                                            kaardipakk.remove(0);
                                        } else {
                                            dealeri_summa += kaardipakk.get(0).getNumber();
                                            dealer.add(kaardipakk.get(0));
                                            kasutatud.add(kaardipakk.get(0));
                                            kaardipakk.remove(0);
                                        }
                                    }

                                    if (dealeri_summa <= 21) {
                                        if (dealeri_summa == 21) { // diileri blackjack
                                            System.out.println("Diiler sai blackjacki");
                                            System.out.println("Dealeri käsi " + dealer + " , Summa = " + dealeri_summa);
                                            System.out.println("Su krediidid " + mangur.getKrediidid());
                                            break;
                                        } else if (dealeri_summa > summa) { // kui diileril on rohkem kui mängijal
                                            System.out.println("Sa kaotasid, diileril oli rohkem: " + dealeri_summa + " > " + summa);
                                            System.out.println("Dealeri käsi " + dealer);
                                            System.out.println("Su krediidid " + mangur.getKrediidid());
                                            break;
                                        } else if (summa > dealeri_summa) { // kui diileril on vähem
                                            System.out.println("Sa võitsid. Diileri summa: " + dealeri_summa + " < " + summa + " Su summa");
                                            System.out.println(kaes);
                                            System.out.println("Dealeri käsi " + dealer);
                                            mangur.setKrediidid(mangur.getKrediidid() + panus * 2);
                                            System.out.println("Võidusumma: +" + panus*2);
                                            System.out.println("Su krediidid: " + mangur.getKrediidid());

                                            break;
                                        } else { // kui on sama palju
                                            System.out.println("saad panuse tagasi");
                                            System.out.println("Diileri summa: " + dealeri_summa + " = " + summa + " sinu summa");
                                            System.out.println("Dealeri käsi " + dealer);
                                            mangur.setKrediidid(mangur.getKrediidid() + panus);
                                            System.out.println("Su krediidid " + mangur.getKrediidid());
                                            break;
                                        }
                                    } else { // kui diiler hittis enne 17 ja läks üle 21
                                        System.out.println("Diiler läks lõhki");
                                        System.out.println(dealeri_summa);
                                        System.out.println("Dealeri käsi " + dealer);
                                        mangur.setKrediidid(mangur.getKrediidid() + panus * 2);
                                        System.out.println("Su võidusumma: +" + panus*2);
                                        System.out.println("Su krediidid: " + mangur.getKrediidid());
                                        break;
                                    }


                                } else { //vale sisestus
                                    System.out.println("Vale sisend");
                                    System.out.println("Sisesta,kas [H/S]");
                                    continue;
                                }

                            }
                            if (summa == 21) { // kui esimese händiga juba blackjack
                                System.out.println("JACKPOTT");
                                System.out.println("Su käsi " + kaes);
                                mangur.setKrediidid(mangur.getKrediidid() + panus * 2);
                                System.out.println("Su võidusumma: +" + panus*2);
                                System.out.println("Su krediidid " + mangur.getKrediidid());
                            }


                    } else if (panus == 0) { // kui proovitakse panuseks panna 0
                        System.out.println("Miinimum panus on 0.1");
                        continue;
                        }
                    } else { // kui panus suurem kui krediidid
                    System.out.println("Jälgi oma krediiti");
                    System.out.println(mangur.getKrediidid());
                    continue;


                }if (kaardipakk.size() < 10){
                for (Kaart kaart: kasutatud) {
                    kaardipakk.add(kaart);
                }

                }



            } else if (mangimine.equals("2")) {
                break;
            } else if (mangimine.equals("3")) {
                System.out.println("Sinu krediidid: " + mangur.getKrediidid());
            } else {
                System.out.println("VALE SISEND!");
                continue;
            }


        }
        return 0;
    }
}

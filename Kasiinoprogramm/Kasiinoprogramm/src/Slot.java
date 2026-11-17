import java.util.Random;
import java.util.Scanner;
//Sloti mäng kus on kolm eraldi tulpa kus võivad tulla suvalised sümbolid.
//Vastavalt sümbolile(Mis on märgitud emojidega) , kui tuleb kolm samasugust sümbolit, kasutaja võidab olenevalt sümbolile mingi kordaja oma panusest.
public class Slot extends Mang {
    private Random rnd = new Random();
    //Sümbolite massiiv kus sümbolite sagedus näitab kui suur on sümboli esinemise võimalus.
    private String[] symbolid = {
            "🍒", "🍒", "🍒", "🍒",
            "🍋", "🍋", "🍋",
            "💵", "💵",
            "⭐",
            "💎"
    };
    //Tagastab ühte tulpa ühe suvalise sümboli.
    private String keeruta() {
        return symbolid[rnd.nextInt(symbolid.length)];
    }

    @Override
    public double mangi(Kasutaja mangur) {
        Scanner scan = new Scanner(System.in);
        //Mängumenüü, mis võimaldab kasutajal mängida mitu korda järjest või lahkuda mängust/vaadata oma krediite.
        while (true) {
            System.out.println("1 - mängi 🎰, 2 - tagasi, 3 - krediidid");
            String valik = scan.nextLine();

            if (valik.equals("1")) {
                System.out.println("Sisesta panus:");
                System.out.println("Sinu krediidid: " + mangur.getKrediidid());
                double panus = scan.nextDouble();
                scan.nextLine();
                //Kiire panusekontroll, et kasutajal oleks piisavalt "raha" kontol või panus oleks suurem kui 0.
                if (panus <= 0) {
                    System.out.println("Panus peab olema suurem kui 0!");
                    continue;
                }

                if (panus > mangur.getKrediidid()) {
                    System.out.println("Pole piisavalt krediiti!");
                    continue;
                }
                //Panuse kontolt maha võtmine.
                mangur.setKrediidid(mangur.getKrediidid() - panus);
                //Kasutame meetodit keeruta kolm korda, et kõigil kolmel tulbal oleks suvaline sümbol.
                String r1 = keeruta();
                String r2 = keeruta();
                String r3 = keeruta();

                System.out.println("Keerutab...");
                System.out.println("[" + r1 + " | " + r2 + " | " + r3 + "]");

                //Juhul, kui kasutaja saab kolm samasugust sümbolit, kasutaja võitis vastavalt sümbolitele oma panuse kordaja.
                //Samuti näitab palju võideti.
                if (r1.equals(r2) && r2.equals(r3)) {

                    if (r1.equals("💎")) {
                        double voit = panus * 20;
                        System.out.println("!!! JACKPOT (💎💎💎) !!! Võitsid " + voit);
                        mangur.setKrediidid(mangur.getKrediidid() + voit);

                    } else if (r1.equals("⭐")) {
                        double voit = panus * 7;
                        System.out.println("Suur võit! Võitsid " + voit);
                        mangur.setKrediidid(mangur.getKrediidid() + voit);

                    } else if (r1.equals("🔔")) {
                        double voit = panus * 5;
                        System.out.println("Hea võit! Võitsid " + voit);
                        mangur.setKrediidid(mangur.getKrediidid() + voit);

                    } else {
                        double voit = panus * 3;
                        System.out.println("Võitsid! " + voit);
                        mangur.setKrediidid(mangur.getKrediidid() + voit);
                    }

                } else {
                    System.out.println("Kaotasid...");
                }
                //Näitame aktiivselt kasutajale tema krediite.
                System.out.println("Sinu krediidid: " + mangur.getKrediidid());

            } else if (valik.equals("2")) {
                break;

            } else if (valik.equals("3")) {
                System.out.println("Sinu krediidid: " + mangur.getKrediidid());

            } else {
                System.out.println("Vale sisend!");
            }
        }

        return 0;
    }
}
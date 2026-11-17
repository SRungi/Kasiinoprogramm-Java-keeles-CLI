import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class minesweeper extends Mang{

    @Override
    public double mangi(Kasutaja mangur) {

        while (true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Kui soovid mängida MineSweeperit. 1 - mängi, 2 - tagasi menüüsse, 3 - kuva krediite ");
            String mangimine = scan.nextLine();
            if (mangimine.equals("1")) {
                int[][] vali = { // algne numbrite kuvamise jaoks

                        {1, 2, 3, 4},
                        {6, 7, 8, 9},
                        {10, 11, 12, 13},
                        {14, 15, 16, 17},

                };

                String[][] vali_pommid = { // maatriks kus toimuvad muutused

                        {"1", "2", "3", "4"},
                        {"6", "7", "8", "9"},
                        {"10", "11", "12", "13"},
                        {"14", "15", "16", "17"},

                };


                System.out.println("Sisesta oma panus");
                double panus = scan.nextDouble();
                if (panus <= mangur.getKrediidid()) { // panuse kontroll
                        System.out.println("Maatriks näeb välja selline");
                        for (int i = 0; i < vali.length; i++) {
                            System.out.println(Arrays.toString(vali[i]));

                        }
                        mangur.setKrediidid(mangur.getKrediidid()-panus);



                    boolean lopeta = false;
                        while (!lopeta) {

                                    
                            int random_rida = (int) (Math.random() * 4);
                            int random_veerg = (int) (Math.random() * 4);

                            while (vali_pommid[random_rida][random_veerg].equals("X") || vali_pommid[random_rida][random_veerg].equals("O")){ // nii kaua käib tsükkel kuniks muudetakse number X-iks, mis ei ole O ega X veel.
                                random_rida = (int) (Math.random() * 4);
                                random_veerg = (int) (Math.random() * 4);

                            }
                            vali_pommid[random_rida][random_veerg] = "X";



                            System.out.println("Sisesta number"); // kasutaja valib numbri näitel välja
                            double number = scan.nextDouble();


                            for (int i = 0; i < vali_pommid.length; i++) {
                                for (int j = 0; j < vali_pommid[0].length; j++) {
                                    if (vali[i][j] == number) {
                                        if (vali_pommid[i][j].equals("X")) { // kui kasutaja valib välja, mis on pommitud
                                            System.out.println("Lendasid õhku");
                                            System.out.println(vali[i][j] + " oli pommitud!");
                                            System.out.println("Sa kaotasid kõik");
                                            for (int l = 0; l < vali_pommid.length; l++) {
                                                System.out.println(Arrays.toString(vali_pommid[l]));
                                            }

                                            lopeta = true;
                                            break;

                                        } else { // kui kasutaja valib välja mis pole pommitatud
                                            vali_pommid[i][j] = "O";
                                            panus *= 1.25;
                                            System.out.println("Suurendasid võidusummat: +" +panus);
                                        }
                                    }
                                }
                            }
                            boolean onVeelNumbreid = false;

                            for (int i = 0; i < vali_pommid.length; i++) { // kontrollib maatriksit, et kui numbreid ei ole siis boolean onveel numbreid muutub trueks.
                                for (int j = 0; j < vali_pommid[0].length; j++) {
                                    if (!vali_pommid[i][j].equals("O") && !vali_pommid[i][j].equals("X")) {
                                        onVeelNumbreid = true;
                                        break;
                                    }
                                }
                                if (onVeelNumbreid) {
                                    break;
                                }
                            }

                            if (!onVeelNumbreid) { // Kui onveelnumbreid on true, siis boolean lopeta läheb trueks
                                System.out.println("Kõik väljad avatud!");
                                System.out.println("VÕITSID MÄNGU!");
                                System.out.println("Sinu võidusumma: +" + panus);

                                mangur.setKrediidid(mangur.getKrediidid() + panus);
                                System.out.println("Su krediidid: " + mangur.getKrediidid());

                                lopeta = true;
                            }


                            if (lopeta){ // mäng lõpetab
                                break;
                            }

                            for (int i = 0; i < vali_pommid.length; i++) { // kuvab maatriksi, kus on valitud numbrid ja pommid
                                System.out.println(Arrays.toString(vali_pommid[i]));
                            }



                        while (true) { // valiku kinnitamine
                            System.out.println("Hetkene võidusumma: +" +panus);
                            System.out.println("Kas tahad jätkata? --- [Y/n]");
                            scan.nextLine();
                            String valik3 = scan.nextLine().toLowerCase();

                            if (valik3.equals("y")) {
                                break;

                            } else if (valik3.equals("n")) {
                                System.out.println("Su võidusumma: +" + panus);
                                mangur.setKrediidid(mangur.getKrediidid() + panus);
                                System.out.println("Su krediidid: " + (mangur.getKrediidid() + panus));
                                lopeta = true;
                                break;

                            } else {
                                System.out.println("VALE SISEND");

                            }
                        }
                        if (lopeta){ // valikust välja saamine
                            break;
                        }

                        }












                } else if (panus == 0){
                    System.out.println("Miinimum on 0.1");
                } else {
                    System.out.println("Sul on liiga vähe krediiti.");
                    break;
                }




            } else if (mangimine.equals("2")){
                break;
            } else if (mangimine.equals("3")){
                System.out.println("Sinu krediidid: " + mangur.getKrediidid());
            } else {
                System.out.println("Sisesta õige sisend");
                continue;
            }
        }
        return 0;
    }
}

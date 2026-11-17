public class Kasutaja {
    private String nimi;
    private double krediidid;
    private double valjamakse_ajalugu;

    public Kasutaja(String nimi, double krediidid, double valjamakse_ajalugu) {
        this.nimi = nimi;
        this.krediidid = krediidid;
        this.valjamakse_ajalugu = valjamakse_ajalugu;
    }

    public double getKrediidid() {
        return krediidid;
    }

    public void setKrediidid(double krediidid) {
        this.krediidid = krediidid;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    public void juurdemakse(double n){
        krediidid += n;

    }
    public void valjamakse(double n){
        if (n <= krediidid) {
            krediidid -= n;
            valjamakse_ajalugu += n;
        } else {
            System.out.println("Pole piisavalt raha!");
        }
    }

    public void muudaKrediiti(double summa) {
        this.krediidid += summa;
    }



}

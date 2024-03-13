package Macchina;

public class Macchina {
    int km;
    final int anno;

    public Macchina(int km, int anno) {
        this.km = km;
        this.anno = anno;
    }

    public void guida(int km) {
        this.km += km;
    }
}

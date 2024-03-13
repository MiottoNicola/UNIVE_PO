package Macchina;

public class Tesla extends Macchina{
    String modello;

    public Tesla(String modello) {
        super(0, 2023);
        this.modello = modello;
    }

    public Tesla(int km, int anno, String modello){
        super(km, anno);
        this.modello = modello;
    }

    public static void main(String[] args) {
        Tesla t = new Tesla("S");
        t.guida(100);
        System.out.println(t.km);
    }

}

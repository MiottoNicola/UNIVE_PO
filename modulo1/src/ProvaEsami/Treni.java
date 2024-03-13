package ProvaEsami;

import java.sql.Date;
import java.util.ArrayList;

/* Domanda 1
 * Si implementi in Java un programma orientato ad oggetti di gestione dei treni. 
 * In particolare, si implementi una classe Treno, in cui ciascun treno e’ identificato da un numero, 
 * e contiene una serie di stazioni in cui si ferma (ad esempio, parte da Venezia Mestre, ferma a Padova, 
 * Bologna, Firenze, Roma e arriva a Napoli). 
 * Ciascuna stazione e’ rappresentata da una stringa (ad esempio, “Venezia Mestre”). 
 * Per ciascuna stazione, il treno fornisce un metodo che ritorna l’orario di arrivo 
 * (o partenza nel caso in cui sia la stazione di partenza) del treno come istanza della classe java.util.Date. 
 * Nel momento in cui viene costruito un treno, e’ necessario inserire queste informazioni, 
 * e non deve essere possibile modificarle successivamente. 
 * Un treno generico deve definire poi un metodo che ritorna una collezioni di classi (rappresentate 
 * come stringhe, ad esempio “Standard”, “Premium”, “Business” ed “Executive”) che pero’ non puo’ essere 
 * implementato genericamente.
 */

/* Domanda 2:
 * Implementare poi due tipi di treno specifici, ovvero le classi TrenoRegionale e FrecciaRossa. 
 * Le classi di viaggio sono uguali per tutti i treni della stessa tipologia, ovvero “Prima classe” e 
 * “Seconda classe” per i treni regionali, e “Standard”, “Premium”, “Business” ed “Executive” per le frecce rosse).
 */

 /* Domanda 3:
  * Si implementi infine la classe Biglietto. 
  * Tale classe deve contenere una classe di viaggio, e opzionalmente l’identificativo di un treno. 
  * Deve inoltre essere possibile validare il biglietto. Si implementi quindi un metodo che validi il biglietto. 
  * Tale metodo riceve un treno, e una classe, e valida il biglietto se questo e’ valido, ovvero: 
  * (i) se il biglietto e’ stato gia’ validato lanciata un’eccezione controllata, 
  * (ii) se il treno e’ regionale controlla solo la classe (in quanto tali biglietti non sono specifici per un treno), 
  * (iii) se il treno e’ una freccia rossa, controlla sia classe che identificativo treno. 
  * Il metodo ritorna true se il biglietto viene validato correttamente, false altrimenti. 
  * Tutte le implementazioni devono massimizzare l’incapsulamento (encapsulation) attraverso tecniche di 
  * information hiding.
  */

abstract class Treno{
    private int cod;
    private final ArrayList<String> fermate;
    private final ArrayList<Date> orari;

    Treno(int cod, ArrayList<String> f, ArrayList<Date> o){
        this.cod = cod;
        if(f.size() != o.size())
            throw new IllegalArgumentException("Numero di fermate e orari non corrispondenti");
        this.fermate = f;
        this.orari = o;
    }

    public int getCod(){ return this.cod; };
    public ArrayList<String> getFermate(){ return this.fermate; };
    public ArrayList<Date> getOrari(){ return this.orari; };
    public Date getOrari(String nome){
        for(int i=0; i<fermate.size(); i++)
            if(fermate.get(i) == nome)
                return orari.get(i);
        return null;
    }

    abstract public ArrayList<String> getClassi();
}

 class TrenoRegionale extends Treno{
    TrenoRegionale(int cod, ArrayList<String> f, ArrayList<Date> o){
        super(cod, f, o);
    }

    @Override
    public ArrayList<String> getClassi(){
        ArrayList<String> classi = new ArrayList<String>();
        classi.add("Prima Classe");
        classi.add("Seconda Classe");
        return classi;
    }
 }

  class FrecciaRossa extends Treno{
    FrecciaRossa(int cod, ArrayList<String> f, ArrayList<Date> o){
        super(cod, f, o);
    }

    @Override
    public ArrayList<String> getClassi(){
        ArrayList<String> classi = new ArrayList<String>();
        classi.add("Standard");
        classi.add("Premium");
        classi.add("Business");
        classi.add("Executive");
        return classi;
    }
 }

class Biglietto{
    private final String classe;
    private final int codtreno;
    private boolean validato;

    Biglietto(String classe, int codtreno){
        this.classe = classe;
        this.codtreno = codtreno;
        this.validato = false;
    }
    Biglietto(String classe){
        this.classe = classe;
        this.codtreno = -1;
        this.validato = false;
    }

    public String getClasse(){ return this.classe; }
    public int getCodTreno(){ return this.codtreno; }
    public boolean getValidato(){ return this.validato; }

    public boolean validaBiglietto(Treno t, String c) throws Exception{
        if(this.validato)
            throw new Exception("Biglietto già validato");
        
        if(t instanceof TrenoRegionale){
            if(t.getClassi().contains(c) && this.classe == c){
                this.validato = true;
                return true;
            }
        }
        if(t instanceof FrecciaRossa){
            if(t.getClassi().contains(c) && this.classe == c && this.codtreno == t.getCod()){
                this.validato = true;
                return true;
            }
        }
        return false;
    }
}
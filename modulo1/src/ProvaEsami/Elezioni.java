package ProvaEsami;

import java.util.ArrayList;

/* Domanda 1 (10 punti) :
 * Si implementi una classe Elezioni il cui scopo è di registrare il numero di voti ottenuti da 
 * diversi partiti a delle elezioni politiche. In particolare, questa classe deve contenere: 
 * • un costruttore che riceve il numero di persone aventi diritto al voto come valore intero; 
 * • un metodo addPartito che dato il nome di un partito (rappresentato come stringa) e 
 *   il numero di voti ottenuti (rappresentato come valore intero) memorizza internamente tale informazione. 
 *   Nel caso in cui in precedenza fosse stato già registrato un numero di voti per il partito dato, 
 *   il metodo dovrà lanciare un’eccezione definita appositamente per rappresentare tale situazione di errore; 
 * • un metodo getAffluenza che ritorna la percentuale di votanti (definita come la divisione tra 
 *   i votanti totali per il numero di persone aventi diritto al voto moltiplicata per 100). 
 */

/* Domanda 2 (5 punti) :
 * Si consideri poi la seguente interfaccia:
 *  interface AssegnazioneDeputati {
 *      public int getDeputatiPartito(String partito);
 *  }
 * Il metodo getDeputatiPartito, dato il nome di un partito, ritorna il numero di deputati ottenuti alle 
 * elezioni da quel partito. 
 * Si implementi una classe AssegnazioneDeputatiProporzionale 
 * • sottotipo di AssegnazioneDeputati 
 * • il cui costruttore riceve un oggetto di tipo Elezioni e un intero con il numero totale di 
 *   deputati che siedono in parlamento. 
 * • che implementa il metodo getDeputatiPartito seguendo la logica del sistema proporzionale, 
 *   ovvero assegnando il numero di deputati di ciascun partito in maniera direttamente proporzionale al 
 *   numero di voti. 
 *   Immaginiamo ad esempio che alle elezioni si presentino 3 partiti, che il numero di 
 *   deputati sia 10, che in totale abbiano votato 200 persone dando 80 voti a “Partito1”, 
 *   20 a “Partito2” e 100 a “Partito3”. getDeputatiPartito dovrà quindi ritornare 4 quando verrà passato 
 *   “Partito1”, 1 con “Partito2” e 5 con “Partito3”. 
 * Nota: ai fini dell’esercizio potete assumere che non ci sia necessità di arrotondare per eccesso o 
 * difetto il numero di parlamentari (come nell’esempio precedente in cui veniva assegnato un parlamentare 
 * ogni 20 voti, e il numero di voti di ciascun partito era multiplo di 20). 
 * */

interface AssegnazioneDeputati {
    public int getDeputatiPartito(String partito);
}

class AssegnazioneDeputatiProporzionale implements AssegnazioneDeputati{
    private Elezioni el;
    private int deputati;

    public AssegnazioneDeputatiProporzionale(Elezioni e, int n){
        this.el = e;
        this.deputati = n;
    }

    @Override
    public int getDeputatiPartito(String partito) {
        if(el.getVotiPartito(partito) == -1) return 5;
        return (int) (el.getVotiPartito(partito)/el.getVotanti())*this.deputati;
    }
    
}
class ElectionException extends Exception{
    public ElectionException(String msg){
        super(msg);
    }
}

class Elezioni {
    ArrayList<String> partiti = new ArrayList<String>();
    ArrayList<Integer> votiPartiti = new ArrayList<Integer>();
    private final int votanti;

    public Elezioni(int n){
        this.votanti = n;
    }

    public void addPartito(String nome, int v) throws ElectionException{
        for(int i=0; i<this.partiti.size(); i++)
            if(this.partiti.get(i) == nome) throw new ElectionException("Partito già registrato");
            if (v < 0) throw new ElectionException("Il numero di voti deve essere non negativo");
        this.partiti.add(nome);
        this.votiPartiti.add(v);
    }

    public float getAffluenza(){
        int totVoti=0;
        for(int i=0; i<this.votiPartiti.size(); i++)
            totVoti += this.votiPartiti.get(i);
        return (totVoti/this.votanti)*100;
    }

    public int getVotiPartito(String nome){
        for(int i=0; i<this.partiti.size(); i++)
            if(this.partiti.get(i) == nome) return this.votiPartiti.get(i);
        return -1;
    }

    public int getVotanti(){
        return this.votanti;
    }
}


class TestMain {
    public static void main(String[] args) {
        try{
            Elezioni elem = new Elezioni(15000);
            elem.addPartito("PartyA", 300);
            elem.addPartito("PartyB", 1000);
            elem.addPartito("PartyC", 3000);
            elem.addPartito("PartyD", 6000);
            System.err.println(elem.getVotiPartito("PartyB"));
            System.err.println(elem.getAffluenza());
            AssegnazioneDeputatiProporzionale el = new AssegnazioneDeputatiProporzionale(elem, 10);
            System.err.println(el.getDeputatiPartito("PartyA"));
            System.err.println(el.getDeputatiPartito("PartyB"));
            System.err.println(el.getDeputatiPartito("PartyC"));
            System.err.println(el.getDeputatiPartito("PartyD"));
        }catch(ElectionException e){
            System.out.println(e.getMessage());
        }

    }
}
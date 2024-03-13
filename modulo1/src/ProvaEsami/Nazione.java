package ProvaEsami;

import java.util.ArrayList;

/* Domanda 1 (8 punti) :
 * Si implementi una interfaccia Nazione che definisca 
 * • un metodo che, data una nazione, ritorna un valore booleano che e’ vero se la nazione data e’ “amica” 
 *   della nazione corrente, e falso altrimenti;
 * • un metodo che ritorna il nome (rappresentato da una stringa) della nazione.
 * Si implementi poi una interfaccia NazioneAmicaInterfaccia sottotipo di Nazione che rappresenta una 
 * nazione che e’ amica di tutte le nazioni. Si implementi infine una classe NazioneAmica sottotipo 
 * di NazioneAmicaInterfaccia che permetta di inizializzare in maniera appropriata il nome della nazione, 
 * e tale nome non puo’ cambiare una volta costruito l’oggetto. 
*/

/* Domanda 2 (7 punti) : 
 * Si implementi la classe UnioneDiNazioni. 
 * Tale classe rappresenta un insieme di nazioni inizialmente vuoto. 
 * Si implementi un metodo all’interno della classe UnioneDiNazioni che rimuova una nazione dall’unione. 
 * Tale metodo deve ritornare true se la nazione e’ stata effettivamente rimossa. 
 * Si implementi poi sempre all’interno di tale classe un metodo per aggiungere all’unione una nuova nazione. 
 * Tale nazione viene aggiunta solo se le nazioni gia’ facenti parte dell’unione ritengono tutte amica la 
 * nazione aggiunta, e se la nazione data non e’ gia’ presente nell’unione di nazioni.
 *  Il metodo ritorna true se e solo se la nazione e’ stata effettivamente aggiunta all’unione.
 */

class UnioneDiNazioni{
    private ArrayList<Nazione> nazioni;
    UnioneDiNazioni(){
        this.nazioni = new ArrayList<>();
    }

    boolean rimoveNazione(Nazione n){
        return nazioni.remove(n);
    }

    boolean addNazione(Nazione n){
        for(int i=0; i<this.nazioni.size(); i++){
            if(!this.nazioni.get(i).isAmica(n) || this.nazioni.get(i) == n)
                return false;
        }
        return this.nazioni.add(n);
    }


}

interface Nazione {
    public boolean isAmica(Nazione n);
    public String getNome();
}

interface NazioneAmicaInterfaccia extends Nazione{
    default public boolean isAmica(Nazione n){ return true; }
}

class NazioneAmica implements NazioneAmicaInterfaccia{
    private final String nome;

    NazioneAmica(String n){
        this.nome = n;
    }

    @Override
    public String getNome(){ return nome; }

    @Override 
    public boolean isAmica(Nazione n){ return n instanceof NazioneAmicaInterfaccia; }
}

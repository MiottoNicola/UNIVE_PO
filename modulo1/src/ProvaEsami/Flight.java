package ProvaEsami;

import java.sql.Date;
import java.util.ArrayList;

/* Domanda 1 (5 punti) :
 * Si implementi una classe Flight che rappresenta un volo aereo. 
 * In particolare, tale classe deve memorizzare la città di partenza e di arrivo (rappresentate da stringhe), 
 * l’orario di partenza e di arrivo (rappresentati come oggetti istanza di java.util.Date), 
 * e il numero del volo (che è un valore numerico intero compreso tra 0 e 9999). 
 * Una volta inizializzate, queste informazioni non possono più essere modificate, 
 * ma deve essere possibile leggerle esternamente. Il costruttore controlla che l’orario di 
 * partenza sia prima di quello di arrivo.
 */

/* Domanda 2 (5 punti) :
 * Si implementi una classe astratta Airplane che contenga un elenco di voli che un aeromobile percorre. 
 * Ciascuna tratta è un’istanza della classe Flight definita nell’esercizio 1. 
 * L’elenco dei voli deve essere inizialmente vuoto, e deve essere visibile solamente dallo 
 * stesso package e dalle sottoclassi. 
 * La classe Airplane deve implementare poi un metodo per aggiungere un volo all’elenco. 
 * Nel caso in cui il volo aggiunto abbia un orario di partenza precedente all’orario di 
 * arrivo dell’ultimo volo in elenco, tale metodo deve lanciare una FlightException 
 * (definita nel testo dell’esercizio 1). 
*/

/* Domanda 3 (5 punti) :
 * Si implementi una classe Boeing747 che estende Airplane. 
 * La classe Boeing747 deve implementare un metodo che esegue il primo volo dell’elenco. 
 * Tale metodo deve controllare che la data attuale (ottenibile istanziando la classe java.util.Date 
 * enza passare alcun parametro al costruttore) sia successiva alla data di partenza del volo 
 * (usando i metodi after e before specificati nell’esercizio 1), e levare il primo volo dall’elenco.
 * Nel caso in cui una delle condizioni elencate non sia validata, il metodo deve lanciare una 
 * FlightException (definita nell’esercizio 1). 
 * In caso di successo, tale metodo deve ritornare l’orario di arrivo previsto del volo.
 */

class FlightExpetion extends Exception{
    FlightExpetion(String s){
        super(s);
    }
}

abstract class Airplane{
    protected ArrayList<Flight> voli;

    Airplane(){
        this.voli = new ArrayList<Flight>();
    }

    void addFlight(Flight f) throws FlightExpetion{
        if(this.voli.size()>0)
            if(f.getOrarioPartenza().getTime() < this.voli.get(voli.size()).getOrarioArrivo().getTime())
                throw new FlightExpetion("L'orario di partenza è precedente all'orario di arrivo");

        this.voli.add(f);
    }
}

class Boeing747 extends Airplane{
    Boeing747(){
        super();
    }

    Date firstFlight() throws FlightExpetion{
        Date now = new Date(System.currentTimeMillis());
        if(now.getTime() < this.voli.get(0).getOrarioPartenza().getTime())
            throw new FlightExpetion("La data attuale è precedente alla data di partenza del volo");

        Date ris = this.voli.get(0).getOrarioArrivo();
        this.voli.remove(0);
        
        return ris;
    }
}

class Flight {
    private final String cittaPartenza;
    private final String cittaArrivo;
    private final Date orarioPartenza;
    private final Date orarioArrivo;
    private final int numeroVolo;

    Flight(String cp, String ca, Date op, Date oa, int nv) throws FlightExpetion{
        this.cittaPartenza = cp;
        this.cittaArrivo = ca;
        this.orarioPartenza = op;
        if(oa.getTime() < op.getTime())
            throw new FlightExpetion("L'orario di partenza è precedente all'orario di arrivo");
        this.orarioArrivo = oa;
        if(nv < 0 || nv > 999)
            throw new FlightExpetion("Il numero di volo deve essere compreso tra 0 e 999");
        this.numeroVolo = nv;
    }

    String getCittaPartenza(){
        return this.cittaPartenza;
    }
    String getCittaArrivo(){
        return this.cittaArrivo;
    }
    Date getOrarioPartenza(){
        return this.orarioPartenza;
    }
    Date getOrarioArrivo(){
        return this.orarioArrivo;
    }
    int getNumeroVolo(){
        return this.numeroVolo;
    }
}

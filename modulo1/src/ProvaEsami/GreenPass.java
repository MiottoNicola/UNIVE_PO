package ProvaEsami;

import java.sql.Date;
import java.util.ArrayList;

/* Domanda 1
 * Si implementi una classe astratta GreenPass che contenga il codice fiscale di una persona 
 * (rappresentato attraverso una stringa) e la data di scadenza (rappresentato come un oggetto di 
 * tipo Date del package java.util).
 * Una volta creato un green pass, non deve essere più possibile modificare né il codice fiscale né la 
 * data di scadenza. 
 * Deve però esserci la possibilità di accedere alle informazioni 
 * (codice fiscale e scadenza) anche al di fuori della classe. 
 * Si implementino poi una classe VaccinoGreenPass che estende la classe GreenPass e rappresenta un 
 * GreenPass ottenuto tramite vaccino. 
 * Tale classe verrà utilizzata all’interno di collezioni (Collection) successivamente. 
 * Negli esercizi seguenti si assuma che oltre alla classe VaccinoGreenPass ne sia stata implementata 
 * un’altra chiamata TamponeGreenPass che rappresenta un green pass ottenuto tramite tampone, 
 * e con un’implementazione analoga a quella di VaccinoGreenPass.
*/

/* Domanda 2:
 * Si implementi la classe PersonaConGreenPass che contiene il codice fiscale 
 * (sempre rappresentato come una stringa) di una persona, e una collezione (Collection) di green pass. 
 * Quando istanziata, la classe non contiene alcun GreenPass. La collezione di GreenPass deve 
 * poter essere accessibile pubblicamente tramite un metodo getter. 
 * Si implementi poi un metodo pubblico addGreenPass che riceve un oggetto di tipo GreenPass e 
 * lo aggiunge alla collezione. Se uno stesso GreenPass viene aggiunto due volte deve essere
 * contenuto una sola volta nella collection. 
 * Nel caso in cui il green pass aggiunto fa riferimento ad un codice fiscale diverso, 
 * deve essere lanciata una eccezione PersonaSbagliataException implementata come segue.
 */

/* Domanda 3:
 * Si implementi una classe ControlloGreenPass che implementa due metodi statici haGreenPassNormale e 
 * haGreenPassSuper che ricevono una PersonaConGreenPass e ritornano un valore booleano true se e solo 
 * se tale persona è coperta da un qualsiasi GreenPass o se è coperta da un VaccinoGreenPass per la data 
 * odierna, rispettivamente. 
 * La data odierna può essere ottenuta invocando la seguente catena di metodi Calendar.getInstance().getTime() 
 * che ritorna un oggetto di tipo Date. 
 * Il confronto tra date invece (per sapere se una data è prima o dopo di un’altra) può essere 
 * effettuato attraverso i metodi seguenti della classe Date.
 * 
 * after(Date when): Restituisce true se l'istanza di Date su cui viene chiamato è successiva alla data 
 * specificata (when), altrimenti restituisce false. Ad esempio:
 *   Date date1 = new Date();
 *   Date date2 = new Date(System.currentTimeMillis() + 1000); // Creo una data successiva di 1 secondo
 *   boolean isAfter = date2.after(date1);
 *   System.out.println(isAfter); // Stampa true
 * 
 * before(Date when): Restituisce true se l'istanza di Date su cui viene chiamato è precedente alla data s
 * pecificata (when), altrimenti restituisce false. Ad esempio:
 *   Date date1 = new Date();
 *   Date date2 = new Date(System.currentTimeMillis() - 1000); // Creo una data precedente di 1 secondo
 *   boolean isBefore = date2.before(date1);
 *   System.out.println(isBefore); // Stampa true
 * 
 * Entrambi i metodi considerano le ore, i minuti, i secondi e i millisecondi nella comparazione. 
 * Sono spesso utilizzati quando si deve confrontare la sequenza temporale di due istanze di Date per 
 * determinare l'ordine cronologico. 
 * Tuttavia, nota che la classe Date è obsoleta e si consiglia di utilizzare le nuove classi di data e 
 * ora introdotte in Java 8, come LocalDate e LocalDateTime.
*/
abstract class GreenPass {
    private final String codFiscale;
    private final Date scadenza;

    public GreenPass(String codFiscale, Date scadenza){
        this.codFiscale = codFiscale;
        this.scadenza = scadenza;
    }

    String getCodiceFiscale(){ return codFiscale; }
    Date getScadenza(){ return scadenza; }
}

class VaccinoGreenPass extends GreenPass{
    public VaccinoGreenPass(String codFiscale, Date scadenza){
        super(codFiscale, scadenza);
    }
}

class TamponeGreenPass extends GreenPass{
    public TamponeGreenPass(String codFiscale, Date scadenza){
        super(codFiscale, scadenza);
    }
}


class PersonaSbagliataException extends Exception {
    public PersonaSbagliataException(String s){
        super(s);
    }
}

class PersonaConGreenPass{
    private final String codFiscale;
    private ArrayList<GreenPass> greenPasses;

    PersonaConGreenPass(String cf){
        codFiscale = cf;
        greenPasses = new ArrayList<GreenPass>();
    }

    ArrayList<GreenPass> getGreenPasses(){ return greenPasses; }
    void addGreenPass(GreenPass gp )throws PersonaSbagliataException{
        if(gp.getCodiceFiscale() != this.codFiscale) throw new PersonaSbagliataException("GreenPass non valido per quresta persona");
        if(!greenPasses.contains(gp))
            greenPasses.add(gp);
    }
}

class ControlloGreenPass{
    static boolean haGreenPassNormale(PersonaConGreenPass p){
        Date now = new Date(System.currentTimeMillis());
        for(int i=0; i<p.getGreenPasses().size(); i++){
            if(now.after(p.getGreenPasses().get(i).getScadenza()))
                return true;
        }
        return false;
    }
    static boolean haGreenPassSuper(PersonaConGreenPass p){
        Date now = new Date(System.currentTimeMillis());
        for(int i=0; i<p.getGreenPasses().size(); i++){
            if((p.getGreenPasses().get(i) instanceof VaccinoGreenPass) && now.after(p.getGreenPasses().get(i).getScadenza()))
                return true;
        }
        return false;
    }
}


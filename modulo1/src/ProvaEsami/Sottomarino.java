package ProvaEsami;

import java.util.ArrayList;

/* Domanda 1;
 * Si implementi una classe Submarine che rappresenta un sottomarino. 
 * In particolare, tale classe deve memorizzare il nome del sottomarino, la profondità massima a 
 * cui può andare il sottomarino, e la profondità attuale. 
 * Il livello di profondità è espresso in metri con un valore numerico floating point. 
 * Tali informazioni devono essere inizializzate durante la costruzione dell’oggetto, 
 * devono essere esternamente accessibili ma non modificabili. 
 * Si implementi poi un metodo immersione che riceve un valore floating point che rappresenta di quanto 
 * il sottomarino si deve immergere. Nel caso in cui tale valore sia compatibile con la profondità massima, 
 * questo metodo deve aggiornare la profondità coerentemente e ritornare un valore true, 
 * o in caso contrario non deve modificare la profondità ritornando false.
*/

/* Domanda 2:
 * Si estenda la classe Submarine con una classe PoweredSubmarine che rappresenta una sottomarino motorizzato. 
 * In particolare, il PoweredSubmarine contiene un valore numerico floating point che rappresenta di 
 * quanto il sottomarino si immerge quando il motore è acceso, e un flag booleano che è true se il 
 * motore è accesso. Si implementino poi due metodi pubblici per accendere e spegnere il motore, e 
 * un metodo step() che se il motore è acceso lo fa scendere della profondità attesa, e che ritorna 
 * true se e solo se alla fine del metodo il sottomarino è sceso della profondità attesa.
 */

/* Domanda 3:
 * Si implementi un metodo statico rescueSubmarine che riceve una collezione di Submarine. 
 * Per ciascun sottomarino all’interno della collezione, controlla se la profondità attuale del 
 * sottomarino è maggiore di quella massima, e in tal caso crea un PoweredSubmarine che può raggiungere 
 * tale profondità con il motore acceso.
 */

class Submarine {
    private final String nome;
    private final float profonditaMax;
    private float profonditàNow;

    public Submarine(String n, float max, float now){
        this.nome = n;
        this.profonditaMax = max;
        this.profonditàNow = now;
    }

    public String getNome() { return nome; }
    public float getProfonditaMax() { return profonditaMax; }
    public float getProfonditàNow() { return profonditàNow; }

    public boolean immersione(float immersione){
        if(immersione+profonditàNow>profonditaMax) 
            return false;
        
        profonditàNow += immersione;
        return true;
    }
}


class PoweredSubmarine extends Submarine{
    private final float engineOn;
    private boolean engineStatus;

    public PoweredSubmarine(String n, float max, float now, float engine, boolean engineStatus){
        super(n, max, now);
        this.engineOn = engine;
        this.engineStatus = engineStatus;
    }

    public float getEngine() { return engineOn; }
    public boolean getEngineStatus() { return engineStatus; }

    public void setEngineStatus() { engineStatus = !engineStatus; }
    public boolean step(){
        if(!getEngineStatus())
            return true;
        return immersione(engineOn);
    }

}

class SubmarineRescue {
    public static ArrayList<PoweredSubmarine> rescueSubmarine(ArrayList<Submarine> submarines) {
        ArrayList<PoweredSubmarine> rescueSubmarines = new ArrayList<>();

        for (Submarine submarine : submarines) {
            if (submarine.getProfonditàNow() > submarine.getProfonditaMax()) {
                PoweredSubmarine rescueSubmarine = new PoweredSubmarine(submarine.getNome(), submarine.getProfonditaMax()+1, 0 , submarine.getProfonditaMax(), true );
                rescueSubmarines.add(rescueSubmarine);
            }
        }

        return rescueSubmarines;
    }
}
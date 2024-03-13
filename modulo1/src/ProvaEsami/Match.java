package ProvaEsami;

import java.util.ArrayList;

/* Domanda 1 :
 * Si implementi una classe astratta Match che rappresenta un incontro sportivo. 
 * In particolare, tale classe deve memorizzare i nomi delle due squadre o persone che si sono affrontate
 * nell’incontro (rappresentate come stringhe), e fornire un metodo che ritorna il vincitore
 * dell’incontro o un valore null in caso di pareggio.
 * Deve essere possibile conoscere i nomi delle due squadre esternamente.
 */

/* Domanda 2:
 * Si implementi una classe SoccerMatch che registra, oltre alle informazioni della classe Match,
 * anche il numero di goal segnati da ciascuna delle due squadre, e implementi in maniera
 * adeguata il metodo che ritorna il vincitore dell’incontro.
*/

/*
 * Si implementi una classe SoccerTournament che rappresenta un torneo calcistico, e che memorizza 
 * una collezione di squadre (rappresentate come stringhe) e una collezione di Match di un torneo calcistico. 
 * Nel caso in cui la collezione di Match contenga incontri non calcistici, o di squadre non presenti 
 * nella collezione di squadre data, il costruttore deve lanciare una eccezione controllata (checked) 
 * definita appositamente.
 * Si implementi inoltre un metodo getPoints che data una squadra, ritorna il numero di punti
 * ottenuti da quella squadra nel torneo. Per il calcolo dei punti, dovranno essere conteggiati 3
 * punti per ogni vittoria, 1 punto per ogni pareggio, e 0 punti per ogni sconfitta.
*/

class TorunamentExpection extends Exception{
    public TorunamentExpection(String message){
        super(message);
    }
}

class SoccerTournament{
    private final ArrayList<String> squadre;
    private final ArrayList<Match> matches;

    SoccerTournament(ArrayList<String> s, ArrayList<Match> m) throws TorunamentExpection {
        for(int i=0; i<m.size(); i++){
            if(!(m.get(i) instanceof SoccerMatch)) throw new TorunamentExpection("Incontri non calcistici");
            if(!s.contains(m.get(i).getSquad1()) || !s.contains(m.get(i).getSquad2())) throw new TorunamentExpection("Squadre non presenti");
        }
        this.squadre = s;
        this.matches = m;
    }

    int getPoints(String s) throws TorunamentExpection{
        if(!squadre.contains(s)) throw new TorunamentExpection(s + " non è presente nel torneo");
        int ris=0;
        for(int i=0; i<this.matches.size(); i++){
            if(this.matches.get(i).getSquad1() == s || this.matches.get(i).getSquad2() == s)
                if(this.matches.get(i).getWinner() == s) ris += 3;
                else if(this.matches.get(i).getWinner() == null) ris += 1;
        }
        return ris;
    }
}
abstract class Match {
    private final String squad1;
    private final String squad2;

    public Match(String squad1, String squad2) {
        this.squad1 = squad1;
        this.squad2 = squad2;
    }

    public String getSquad1(){
        return squad1;
    }

    public String getSquad2(){
        return squad2;
    }

    abstract public String getWinner();
}

class SoccerMatch extends Match{
    private int goal1;
    private int goal2;

    SoccerMatch(String s1, String s2, int g1, int g2){
        super(s1, s2);
        this.goal1 = g1;
        this.goal2 = g2;
    }

    @Override
    public String getWinner(){
        if(this.goal1 > this.goal2)
            return getSquad1();
        if(this.goal1 < this.goal2)
            return getSquad2();
        return null;
    }
}


package ProvaEsami;

import java.util.ArrayList;

class PersoneException extends Exception{
    public PersoneException(String message){
        super(message);
    }
}
class StudentException extends Exception{
    public StudentException(String message){
        super(message);
    }
}
class ProfessoreException extends Exception{
    public ProfessoreException(String message){
        super(message);
    }
}
class AccademyException extends Exception{
    public AccademyException(String message){
        super(message);
    }
}

abstract class Persone{
    private final String nome;
    private final String cognome;
    private final String codFiscale;

    public Persone(String nome, String cognome, String codFiscale){
        this.nome = nome;
        this.cognome = cognome;
        this.codFiscale = codFiscale;
    }

    public String getNome(){ return this.nome; }
    public String getCognome(){ return this.cognome; }
    public String getCodFiscale(){ return this.codFiscale; }
}

class Studente extends Persone{
    private final String matricola;
    private ArrayList<Corso> corsi;
    private ArrayList<Integer> voti;
    private int votoMedio;
    private boolean tutor;

    public Studente(String nome, String cognome, String codFiscale, String matricola, ArrayList<Corso> corsi, ArrayList<Integer> voti, boolean booltutor) throws StudentException{
        super(nome, cognome, codFiscale);
        this.matricola = matricola;
        if(corsi.size() != voti.size()) throw new StudentException("lista corsi e lista voti hanno dimensioni diverse.");
        this.voti = voti;
        this.corsi = corsi;
        this.votoMedio = this.findVototMedio();
        this.tutor = booltutor;
    }
    public Studente(String nome, String cognome, String codFiscale, String matricola, boolean tutor){
        super(nome, cognome, codFiscale);
        this.matricola = matricola;
        this.corsi = new ArrayList<Corso>();
        this.voti = new ArrayList<Integer>();
        this.votoMedio = 0;
        this.tutor = tutor;
    }
    public Studente(String nome, String cognome, String codFiscale, String matricola){
        super(nome, cognome, codFiscale);
        this.matricola = matricola;
        this.corsi = new ArrayList<Corso>();
        this.voti = new ArrayList<Integer>();
        this.votoMedio = 0;
        this.tutor = false;
    }
    
    public int findVototMedio(){
        int somma = 0;
        for(int i=0; i<this.corsi.size(); i++)
            somma += this.voti.get(i);
        return somma/this.corsi.size();
    }
    public boolean addCorso(Corso corso, int voto){
        if(voto<0 || voto>30) return false;

        this.corsi.add(corso);
        this.voti.add(voto);
        this.findVototMedio();
        return true;
    }
    public boolean addMultipleCorsi(ArrayList<Corso> corsi, ArrayList<Integer> voti){
        if(corsi.size() != voti.size()) return false;

        for(int i=0; i<corsi.size(); i++){
            boolean res = this.addCorso(corsi.get(i), voti.get(i));
            if(!res) return false;
        }
        return true;
    }
    public boolean removeCorso(Corso corso){
        if(!this.corsi.contains(corso)) return false;

        this.voti.remove(this.corsi.indexOf(corso));
        this.corsi.remove(corso);
        this.findVototMedio();
        return true;
    }
    public boolean setTutor(boolean value){
        this.tutor = value;
        return true;
    }

    public String getMatricola(){ return this.matricola; }
    public ArrayList<Corso> getCorsi(){ return this.corsi;}
    public ArrayList<Integer> getVoti(){ return this.voti; }
    public int getVotoMedio(){ return this.votoMedio; }
    public boolean isTutor(){ return this.tutor; }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        
        Studente s = (Studente) obj;
        return getNome().equals(s.getNome()) && getCognome().equals(s.getCognome()) && this.matricola.equals(s.getMatricola()) && this.voti.equals(s.getVoti()) && this.corsi.equals(s.getCorsi());
    }
    public int hashCode(){
        return this.getNome().hashCode() + this.getCognome().hashCode() + this.matricola.hashCode() + this.voti.hashCode() + this.corsi.hashCode();
    }
}

class Professore extends Persone{
    private final String codDocente;

    public Professore(String nome, String cognome, String codFiscale, String codDocente){
        super(nome, cognome, codFiscale);
        this.codDocente = codDocente;
    }

    public String getCodDocente(){ return this.codDocente; }
    public boolean addVoto(Studente s, Corso c, int voto){
        if(voto<0 || voto>30 || s.getCorsi().contains(c)) return false;

        s.addCorso(c, voto);
        return true;
    }
    public boolean changeVoto(Studente s, Corso c, int voto){
        if(voto<0 || voto>30 || !s.getCorsi().contains(c)) return false;

        s.getVoti().set(s.getCorsi().indexOf(c), voto);
        return true;
    }
    
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        
        Professore p = (Professore) obj;
        return getNome().equals(p.getNome()) && getCognome().equals(p.getCognome()) && this.codDocente.equals(p.getCodDocente());
    }
    public int hashCode(){
        return this.getNome().hashCode() + this.getCognome().hashCode() + this.codDocente.hashCode();
    }
}

class Corso{
    private final String nome;
    private final String cod;
    private final int cfu;
    private ArrayList<Studente> studenti;
    private ArrayList<Professore> professori;
    private ArrayList<Studente> tutor;

    public Corso(String nome, String cod, int cfu, ArrayList<Studente> studenti, ArrayList<Professore> professori, ArrayList<Studente> tutor){
        this.nome = nome;
        this.cod = cod;
        this.cfu = cfu;
        this.studenti = studenti;
        this.professori = professori;
        this.tutor = tutor;
    }
    public Corso(String nome, String cod, int cfu, ArrayList<Professore> professori){
        this.nome = nome;
        this.cod = cod;
        this.cfu = cfu;
        this.studenti = new ArrayList<Studente>();
        this.professori = professori;
        this.tutor = new ArrayList<Studente>();
    }
    public Corso(String nome, String cod, int cfu){
        this.nome = nome;
        this.cod = cod;
        this.cfu = cfu;
        this.studenti = new ArrayList<Studente>();
        this.professori = new ArrayList<Professore>();
        this.tutor = new ArrayList<Studente>();
    }

    public boolean addStudente(Studente s){
        if(this.studenti.contains(s)) return false;

        this.studenti.add(s);
        return true;
    }
    public boolean addMultipleStudenti(ArrayList<Studente> s){
        for(int i=0; i<s.size(); i++){
            boolean res = this.addStudente(s.get(i));
            if(!res) return false;
        }
        return true;
    }
    public boolean removeStudente(Studente s){
        if(!this.studenti.contains(s)) return false;

        this.studenti.remove(s);
        return true;
    }
    public boolean addProfessore(Professore p){
        if(this.professori.contains(p)) return false;

        this.professori.add(p);
        return true;
    }
    public boolean addMultipleProfessori(ArrayList<Professore> p){
        for(int i=0; i<p.size(); i++){
            boolean res = this.addProfessore(p.get(i));
            if(!res) return false;
        }
        return true;
    }
    public boolean removeProfessore(Professore p){
        if(!this.professori.contains(p)) return false;

        this.professori.remove(p);
        return true;
    }
    public boolean addTutor(Studente s){
        if(this.tutor.contains(s) || !s.isTutor()) return false;

        this.tutor.add(s);
        return true;
    }
    public boolean addMultipleTutor(ArrayList<Studente> s){
        for(int i=0; i<s.size(); i++){
            boolean res = this.addTutor(s.get(i));
            if(!res) return false;
        }
        return true;
    }
    public boolean removeTutor(Studente s){
        if(!this.tutor.contains(s)) return false;

        this.tutor.remove(s);
        return true;
    }
    
    public boolean examDay(){
        for(int i=0; i<this.studenti.size(); i++)
            professori.get(0).addVoto(studenti.get(i), this, i);

        return true;
    }

    public String getNome(){ return this.nome; }
    public String getCod(){ return this.cod; }
    public int getCfu(){ return this.cfu; }
    public ArrayList<Studente> getStudenti(){ return this.studenti; }
    public ArrayList<Professore> getProfessori(){ return this.professori; }
    public ArrayList<Studente> getTutor(){ return this.tutor; }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        
        Corso c = (Corso) obj;
        return this.nome.equals(c.getNome()) && this.cod.equals(c.getCod()) && this.cfu == c.getCfu() && this.studenti.equals(c.getStudenti()) && this.professori.equals(c.getProfessori()) && this.tutor.equals(c.getTutor());
    }
    public int hashCode(){
        return this.nome.hashCode() + this.cod.hashCode() + this.cfu + this.studenti.hashCode() + this.professori.hashCode() + this.tutor.hashCode();
    }
}

class Accademy{
    private final String nome;
    private final int cod;
    ArrayList<Studente> studenti;
    ArrayList<Professore> professori;
    ArrayList<Corso> corsi;

    public Accademy(String nome, int cod){
        this.nome = nome;
        this.cod = cod;
        this.studenti = new ArrayList<Studente>();
    }
    public Accademy(String nome, int cod, ArrayList<Studente> studenti){
        this.nome = nome;
        this.cod = cod;
        this.studenti= studenti;
    }

    public boolean addStudente(Studente s){
        if(this.studenti.contains(s)) return false;

        this.studenti.add(s);
        return true;
    }
    public boolean addMultipleStudenti(ArrayList<Studente> s){
        for(int i=0; i<s.size(); i++){
            boolean res = this.addStudente(s.get(i));
            if(!res) return false;
        }
        return true;
    }
    public boolean removeStudente(Studente s){
        if(!this.studenti.contains(s)) return false;

        this.studenti.remove(s);
        return true;
    }
    public Studente findStudente(String matricola){
        for(int i=0; i<this.studenti.size(); i++){
            if(this.studenti.get(i).getMatricola().equals(matricola))
            return this.studenti.get(i);
        }

        return null;
    }
    
    public boolean addProfessore(Professore p){
        if(this.professori.contains(p)) return false;

        this.professori.add(p);
        return true;
    }
    public boolean addMultipleProfessori(ArrayList<Professore> p){
        for(int i=0; i<p.size(); i++){
            boolean res = this.addProfessore(p.get(i));
            if(!res) return false;
        }
        return true;
    }
    public boolean removeProfessore(Professore p){
        if(!this.professori.contains(p)) return false;

        this.professori.remove(p);
        return true;
    }
    public Professore findProfessore(String codDocente){
        for(int i=0; i<this.professori.size(); i++){
            if(this.professori.get(i).getCodDocente().equals(codDocente))
            return this.professori.get(i);
        }

        return null;
    }

    public boolean addCorso(Corso c){
        if(this.corsi.contains(c)) return false;

        this.corsi.add(c);
        return true;
    }
    public boolean addMultipleCorsi(ArrayList<Corso> c){
        for(int i=0; i<c.size(); i++){
            boolean res = this.addCorso(c.get(i));
            if(!res) return false;
        }
        return true;
    }
    public boolean removeCorso(Corso c){
        if(!this.corsi.contains(c)) return false;

        this.corsi.remove(c);
        return true;
    }
    public Corso findCorso(String nome){
        for(int i=0; i<this.corsi.size(); i++){
            if(this.corsi.get(i).getNome().equals(nome))
            return this.corsi.get(i);
        }

        return null;
    }
    
    public String getNome(){ return this.nome; }
    public int getCod(){ return this.cod; }
    public ArrayList<Studente> getStudenti(){ return this.studenti; }
    public ArrayList<Professore> getProfessori(){ return this.professori; }
    public ArrayList<Corso> getCorsi(){ return this.corsi; }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        
        Accademy a = (Accademy) obj;
        return this.nome.equals(a.getNome()) && this.cod == a.getCod() && this.studenti.equals(a.getStudenti()) && this.professori.equals(a.getProfessori()) && this.corsi.equals(a.getCorsi());
    }
    public int hashCode(){
        return this.nome.hashCode() + this.cod + this.studenti.hashCode() + this.professori.hashCode() + this.corsi.hashCode();
    }
}
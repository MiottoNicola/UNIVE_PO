package Avogador_es.MethodsEqualsHashcode;

abstract class Sport {
    private String name;
    public Sport( String name ) { this.name = name; }
    public String getName() { return name; }

    abstract public boolean equals(Object o);
    abstract public int hashCode();
}

abstract class TeamSport extends Sport {
    int teamSize;
    public TeamSport(String name, int n) { super(name); teamSize = n; }
}

class Golf extends Sport {
    public Golf() { super("Golf"); }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof Golf)) return false;
        Golf golf = (Golf) o;
        return golf.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}

class Tennis extends Sport {
    public Tennis() { super("Tennis"); }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof Golf)) return false;
        Tennis tennis = (Tennis) o;
        return tennis.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}

class FightingSport extends Sport {
    int numberOfRounds;
    public FightingSport(String name, int n) { super(name); numberOfRounds = n; }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof FightingSport)) return false;
        FightingSport fightingSport = (FightingSport) o;
        return fightingSport.getName().equals(this.getName()) && fightingSport.numberOfRounds == this.numberOfRounds;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() + this.numberOfRounds;
    }
}

class Soccer extends TeamSport {
    public Soccer() { super("Soccer", 11); }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof Soccer)) return false;
        Soccer soccer = (Soccer) o;
        return soccer.getName().equals(this.getName()) && soccer.teamSize == this.teamSize;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() + this.teamSize;
    }
}

class Volleyball extends TeamSport {
    public Volleyball() { super("Volleyball", 7); }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof Volleyball)) return false;
        Volleyball volleyball = (Volleyball) o;
        return volleyball.getName().equals(this.getName()) && volleyball.teamSize == this.teamSize;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() + this.teamSize;
    }
}

class Main{
    public static void main(String[] args) {
        Sport golf1 = new Golf();
        Sport golf2 = new Golf();
        Sport tennis1 = new Tennis();
        Sport tennis2 = new Tennis();
        Sport soccer1 = new Soccer();
        Sport soccer2 = new Soccer();
        Sport volleyball1 = new Volleyball();
        Sport volleyball2 = new Volleyball();
        Sport fightingSport1 = new FightingSport("Boxing", 12);
        Sport fightingSport2 = new FightingSport("Boxing", 12);

        System.out.println("golf1.equals(golf2) = " + golf1.equals(golf2));
        System.out.println("golf1.equals(tennis1) = " + golf1.equals(tennis1));
        System.out.println("tennis1.equals(tennis2) = " + tennis1.equals(tennis2));
        System.out.println("tennis1.equals(soccer1) = " + tennis1.equals(soccer1));
        System.out.println("soccer1.equals(soccer2) = " + soccer1.equals(soccer2));
        System.out.println("soccer1.equals(volleyball1) = " + soccer1.equals(volleyball1));
        System.out.println("volleyball1.equals(volleyball2) = " + volleyball1.equals(volleyball2));
        System.out.println("volleyball1.equals(fightingSport1) = " + volleyball1.equals(fightingSport1));
        System.out.println("fightingSport1.equals(fightingSport2) = " + fightingSport1.equals(fightingSport2));
    }
}



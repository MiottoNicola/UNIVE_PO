package DADD.objects.defensive;


import DADD.figures.Restore;

/**
 * This class is aimed at representing an armor in D&D
 *
 * @since 1.0
 * @author Pietro Ferrara pietro.ferrara@unive.it
 */
public class Armor implements DefensiveObject {

    private int defense;

    private final int maximal_defense;

    public Armor(int i) {
        if(i>=0)
            this.defense = i;
        else this.defense = 0;
        this.maximal_defense = this.defense;
    }

    public int getDefense() {
        return this.defense;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "defense=" + defense +
                '}';
    }

    @Override
    public int getInfoDefense() {
        return this.defense;
    }

    public int getStrength() { return this.getInfoDefense();}

    @Restore(amount = 2)
    public void restore() {
        this.defense = Math.min(
                this.maximal_defense,
                this.defense+2);
    }
}

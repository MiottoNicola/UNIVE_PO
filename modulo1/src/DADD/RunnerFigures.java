package DADD;

import DADD.fight.FightBetweenFigure;
import DADD.fight.MissingFigureException;
import DADD.figures.Fighter;
import DADD.figures.Figure;
import DADD.figures.Wizard;
import DADD.objects.defensive.Armor;
import DADD.objects.offensive.fighter.Sword;
import DADD.objects.offensive.magic.Magic;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.Random;

public class RunnerFigures {
    int i;

    public static void main(String[] args) throws MissingFigureException, JAXBException, IOException {


        Sword w1 = new Sword(10);
        Armor a1 = new Armor(2);
        Figure figure1 = new Fighter(w1,a1);

        figure1.setLifePoints(100);


        Magic m1 = new Magic(3, 5, 3);
        Figure figure2 = new Wizard(m1);

        Magic.marshal(m1);
        Magic m2 = Magic.unmarshall();




        for(int j = 0; j < 3; j++) {

            for(int i = 0; i < 150; i++) {
                int winner = new FightBetweenFigure(figure1, figure2).fight();
                switch(winner) {
                    case 1:
                        System.out.println("The fighter " + figure1 + " won!");
                        figure2 = new Random().nextDouble()>=0.5 ?
                                new Wizard(m1) :
                                new Fighter(w1,a1);
                        break;
                    case 2:
                        System.out.println("The wizard " + figure2 + " won!");
                        figure1 = new Random().nextDouble()>=0.5 ?
                                new Wizard(m1) :
                                new Fighter(w1,a1);
                        break;
                    default: System.err.println("This looks odd");
                }
            }
        }

        System.out.println("Created "+Fighter.getNumberOfInstantiatedFigures()+" fighters");

        System.out.println("Created "+Wizard.getNumberOfInstantiatedFigures()+" wizards");
    }

}

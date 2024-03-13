package DADD;

import DADD.figures.Figure;
import DADD.objects.DaDObject;

import java.util.Collection;

public class RunnerRoom {
    public static void main(String[] args) {
        Figure myself = Factory.getFigure();
        Figure opponent = Factory.getFigure();
        Collection<DaDObject> objs = Factory.getDaDObjects();
        Room r = new Room(objs, opponent);
        r.enter(myself);

    }
}

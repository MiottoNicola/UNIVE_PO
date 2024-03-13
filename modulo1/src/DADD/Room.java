package DADD;

import DADD.figures.Figure;
import DADD.fight.FightBetweenFigure;
import DADD.fight.MissingFigureException;
import DADD.objects.DaDObject;
import DADD.objects.defensive.DefensiveObject;
import DADD.objects.offensive.OffensiveObject;

import java.util.Collection;

public class Room {
    private Collection<DaDObject> objs;
    private Figure guardian;

    public Room(Collection<DaDObject> objs, Figure guardian) {
        this.objs = objs;
        this.guardian = guardian;
    }

    public boolean enter(Figure f) {
        if(f==null)
            return false;
        if(guardian!=null) {
            try {
                int result = new FightBetweenFigure(f, guardian).fight();
                if (result != 1)
                    return false;
            }
            catch(MissingFigureException e) {
                throw new UnexpectedBehaviorError(
                        "I'm always passing two figures to the fight, but it still complains.",
                        e);
            }
        }
        for(DaDObject obj : objs) {
            if(obj instanceof DefensiveObject)
                f.collectDefensiveObject((DefensiveObject) obj);
            else if(obj instanceof OffensiveObject)
                f.collectOffensiveObject((OffensiveObject) obj);
        }
        return true;
    }
}

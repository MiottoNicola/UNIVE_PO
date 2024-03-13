package DADD.objects.offensive;

import DADD.objects.DaDObject;

public interface OffensiveObject extends DaDObject {
    default public int getDamage() {
        return this.getInnerDamage();
    }

    default public int getInfoDamage() {
        return this.getInnerDamage();
    }

    /*public*/ int getInnerDamage();


}

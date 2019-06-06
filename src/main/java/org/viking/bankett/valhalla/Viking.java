package org.viking.bankett.valhalla;

import org.viking.bankett.Chieftain;

import javax.annotation.Nonnull;

@SuppressWarnings("WeakerAccess")
public class Viking {
    private final @Nonnull Chieftain chieftain;
    private final @Nonnull GatesOfValhalla gates;
    boolean berserker;

    public Viking(@Nonnull Chieftain chieftain, @Nonnull GatesOfValhalla gates,
                  boolean berserker) {
        this.chieftain = chieftain;
        this.gates = gates;
        this.berserker = berserker;
    }

    public boolean isBerserker() {
        return berserker;
    }

    public void setBerserker(boolean berserker) {
        this.berserker = berserker;
    }

    protected void eat() {
        int pos = chieftain.acquireSeatAndPlates(isBerserker());
        try {
            Thread.sleep((long) (Math.random()*100));
        } catch (InterruptedException ignored) {}
        chieftain.releaseSeatAndPlates(pos);
    }

    protected void pray() {
        gates.pray(chieftain.getGod());
    }

    public void run() {
        Thread.currentThread().setName("Thread-"+this.toString());
        eat();
        pray();
        Thread.currentThread().setName("Thread-dead-"+this.toString());
    }

    @Override
    public String toString() {
        return String.format("Viking@%x", System.identityHashCode(this));
    }
}

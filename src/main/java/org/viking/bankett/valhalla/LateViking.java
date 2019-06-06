package org.viking.bankett.valhalla;

import org.viking.bankett.Chieftain;

import javax.annotation.Nonnull;

public class LateViking extends Viking {
    public LateViking(@Nonnull Chieftain chieftain, @Nonnull GatesOfValhalla gates,
                      boolean berserker) {
        super(chieftain, gates, berserker);
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Thread-"+this.toString());
        pray();
        Thread.currentThread().setName("Thread-dead-"+this.toString());
    }

    @Override
    public String toString() {
        return String.format("LateViking@%x", System.identityHashCode(this));
    }
}

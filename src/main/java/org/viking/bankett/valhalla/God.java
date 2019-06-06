package org.viking.bankett.valhalla;


import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum  God {
    BALDR {
        public @Nonnull Set<God> getRivals() {
            return Collections.singleton(LOKI);
        }
    },
    LOKI {
        public @Nonnull Set<God> getRivals() {
            return Collections.singleton(BALDR);
        }
    },
    VALI {
        public @Nonnull Set<God> getRivals() {
            return Collections.singleton(HODER);
        }
    },
    HODER {
        public @Nonnull Set<God> getRivals() {
            return Collections.singleton(VALI);
        }
    },
    FRIGG {
        public @Nonnull Set<God> getRivals() {
            return Collections.singleton(JORD);
        }
    },
    JORD {
        public @Nonnull Set<God> getRivals() {
            return Collections.singleton(FRIGG);
        }
    },
    ODIN,
    THOR;

    private static Set<God> superGods = null;
    public static @Nonnull Set<God> getSuperGods() {
        if (superGods == null) {
            HashSet<God> set = new HashSet<>();
            set.add(ODIN);
            set.add(THOR);
            // a single assignment. May run more than once, but will
            // always point to a correct set
            superGods = set;
        }
        return superGods;
    }

    public @Nonnull Set<God> getRivals() {
        return Collections.emptySet();
    }

    public boolean isSuperGod() {
        return this == ODIN || this == THOR;
    }

    public boolean isRival(God other) {
        return this.getRivals().contains(other);
    }
}

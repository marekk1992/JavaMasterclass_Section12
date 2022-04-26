package exercises.setsChallenge;

import java.util.HashSet;
import java.util.Set;

public abstract class HeavenlyBody {
    private final String name;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;
    private final HeavenlyBodyType bodyType;
    private final Key key;

    public HeavenlyBody(String name, double orbitalPeriod, HeavenlyBodyType bodyType) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        satellites = new HashSet<>();
        this.bodyType = bodyType;
        key = new Key(name, bodyType);
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public boolean addSatellite(HeavenlyBody moon) {
        if (moon.getBodyType() == HeavenlyBodyType.MOON) {
            return satellites.add(moon);
        }
        return false;
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(this.satellites);
    }

    public String getName() {
        return name;
    }

    public HeavenlyBodyType getBodyType() {
        return bodyType;
    }

    public Key getKey() {
        return key;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof HeavenlyBody) {
            HeavenlyBody object = (HeavenlyBody) obj;
            return this.key.equals(object.getKey());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return this.key.hashCode();
    }

    public static Key makeKey(String name, HeavenlyBodyType bodyType) {
        return new Key(name, bodyType);
    }

    public static final class Key {
        private final String name;
        private final HeavenlyBodyType bodyType;

        public Key(String name, HeavenlyBodyType bodyType) {
            this.name = name;
            this.bodyType = bodyType;
        }

        public String getName() {
            return name;
        }

        public HeavenlyBodyType getBodyType() {
            return bodyType;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() + 57 + this.bodyType.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Key key = (Key) obj;
            return name.equals(key.getName()) && bodyType.equals(key.getBodyType());
        }
    }
}

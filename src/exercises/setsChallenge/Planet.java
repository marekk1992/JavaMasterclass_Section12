package exercises.setsChallenge;

public class Planet extends HeavenlyBody {

    public Planet(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, HeavenlyBodyType.PLANET);
    }

    @Override
    public boolean addSatellite(HeavenlyBody moon) {
        return super.addSatellite(moon);
    }
}

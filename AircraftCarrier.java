package battleship;

public class AircraftCarrier extends Ship{
    private AircraftCarrier(String name, int length, int capacity) {
        super(name, length, capacity);
    }

    public static AircraftCarrier aircraftCarrier = new AircraftCarrier("Aircraft Carrier", 5, 5);
    public static AircraftCarrier aircraftCarrierEnemy = new AircraftCarrier("Aircraft Carrier", 5, 5);

}

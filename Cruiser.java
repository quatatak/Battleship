package battleship;

public class Cruiser extends Ship{
    private Cruiser(String name, int length, int capacity) {
        super(name, length, capacity);
    }
    public static Cruiser cruiser = new Cruiser("Cruiser", 3, 3);
    public static Cruiser cruiserEnemy = new Cruiser("Cruiser", 3, 3);
}

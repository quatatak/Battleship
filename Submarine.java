package battleship;

public class Submarine extends Ship{
    private Submarine(String name, int length, int capacity) {
        super(name, length, capacity);
    }

    public static Submarine submarine = new Submarine("Submarine", 3, 3);
    public static Submarine submarineEnemy = new Submarine("Submarine", 3, 3);
}

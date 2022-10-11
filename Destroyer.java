package battleship;

public class Destroyer extends Ship{
    public Destroyer(String name, int length, int capacity) {
        super(name, length, capacity);
    }
    public static Destroyer destroyer = new Destroyer("Destroyer", 2, 2);
    public static Destroyer destroyerEnemy = new Destroyer("Destroyer", 2, 2);
}

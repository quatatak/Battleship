package battleship;

public class Battleship extends Ship{
    private Battleship(String name, int length, int capacity) {
        super(name, length, capacity);
    }

    public static Battleship battleship = new Battleship("Battleship", 4, 4);
    public static Battleship battleshipEnemy = new Battleship("Battleship", 4, 4);
}

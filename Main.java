package battleship;

import java.io.IOException;
import java.util.Scanner;

class Cell {


    public Cell(String representation, String fogRepresentation) {
        this.fogRepresentation = fogRepresentation;
        this.representation = representation;
    }
    boolean isPlaceable = true;
    String representation;

    boolean isPlaced = false;

    String fogRepresentation;

    Ship ship;
}

public class Main {

    public static final String FIRST_COLUMN = "0ABCDEFGHIJ";

    static Scanner scanner = new Scanner(System.in);

    static int count1 = 5;
    static int count2 = 5;
    public static final Cell[][] map = new Cell[11][11];

    public static final Cell[][] mapEnemy = new Cell[11][11];

    public static void main(String[] args) {
        // Write your code here
        System.out.println("Player 1, place your ships on the game field");
        setMap(map);
        battleship(map);
        promptEnterKey();
        System.out.println("...");
        System.out.println("Player 2, place your ships to the game field");
        setMap(mapEnemy);
        battleshipEnemy(mapEnemy);
        while (count1 != 0 && count2 != 0) {
            promptEnterKey();
            System.out.println("...");
            displayFog(mapEnemy);
            System.out.println("---------------------");
            displayMap(map);
            System.out.println("Player 1, it's your turn:");
            if (shot(mapEnemy)) {
                break;
            }
            promptEnterKey();
            System.out.println("...");
            displayFog(map);
            System.out.println("---------------------");
            displayMap(mapEnemy);
            System.out.println("Player 2, it's your turn:");
            if (shot(map)) {
                break;
            }
        }
    }

    public static void battleship(Cell[][] map) {
        AircraftCarrier.aircraftCarrier.placing(map);
        Battleship.battleship.placing(map);
        Submarine.submarine.placing(map);
        Cruiser.cruiser.placing(map);
        Destroyer.destroyer.placing(map);
    }

    public static void battleshipEnemy(Cell[][] map) {
        AircraftCarrier.aircraftCarrierEnemy.placing(map);
        Battleship.battleshipEnemy.placing(map);
        Submarine.submarineEnemy.placing(map);
        Cruiser.cruiserEnemy.placing(map);
        Destroyer.destroyerEnemy.placing(map);
    }

    public static void displayMap(Cell[][] map) {
        System.out.println();
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                System.out.print(cell.representation);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void displayFog(Cell[][] map) {
        System.out.println();
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                System.out.print(cell.fogRepresentation);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void setMap(Cell[][] map) {
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == 0 && j == 0) {
                    map[i][j] = new Cell("  ", " ");
                } else if (i == 0) {
                    map[i][j] = new Cell(j + " ", j + " ");
                } else if (j == 0) {
                    map[i][j] = new Cell(FIRST_COLUMN.charAt(i) + " ", FIRST_COLUMN.charAt(i) + " ");
                } else {
                    map[i][j] = new Cell("~ ", "~ ");
                }
                System.out.print(map[i][j].representation);
            }
            System.out.println();
        }
    }

    public static boolean shot(Cell[][] map){
        System.out.println();
        String coordinate = scanner.next();
        int yCo = FIRST_COLUMN.indexOf(coordinate.charAt(0));
        int xCo = Integer.parseInt(coordinate.substring(1));
        if (yCo == -1 || xCo < 1 || xCo > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            shot(map);
            return false;
        } else if(!(map[yCo][xCo].isPlaced)) {
            map[yCo][xCo].representation = "M ";
            map[yCo][xCo].fogRepresentation = "M ";
            System.out.println("You missed!");
            return false;
           // displayMap();
        } else {
            map[yCo][xCo].representation = "X ";
            map[yCo][xCo].fogRepresentation = "X ";
            map[yCo][xCo].ship.setCapacity();
            if (map[yCo][xCo].ship.getCapacity() != 0) {
                System.out.println("You hit a ship! Try again:");
            } else {
                if (map == mapEnemy) {
                    if (count2 == 1) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        return true;
                    } else {
                        System.out.println("You sank a ship! Specify a new target:");
                        count2--;
                        return false;
                    }
                } else if (map == Main.map) {
                    if (count1 == 1) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        return true;
                    } else {
                        System.out.println("You sank a ship! Specify a new target:");
                        count1--;
                        return false;
                    }
                }
            }
            return false;

            //displayMap();
        }
    }
    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

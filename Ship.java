package battleship;

class Ship {

    private final String name;
    private int capacity;

    public Ship(String name, int length, int capacity) {
        this.name = name;
        this.length = length;
        this.capacity = capacity;
    }
    private final int length;

    public void setCapacity() {
        this.capacity--;
    }



    public void placing(Cell[][] map) {
        System.out.println();
        System.out.println("Enter the coordinates of the " +
                this.name + " (" + this.length + " cells):");
        String begin = null;
        String end = null;
        int length1 = 0;
        int length2 = 0;
        try {
            begin = Main.scanner.next();
            end = Main.scanner.next();
            System.out.println();
            length1 = Math.abs((begin.charAt(0) - end.charAt(0))) + 1;
            length2 = Math.abs((Integer.parseInt(begin.substring(1)) - Integer.parseInt(end.substring(1)))) + 1;
        } catch (NumberFormatException e) {
            System.out.println("Error: Unknown command. Try again: ");
            placing(map);
        }
        if (begin.charAt(0) != end.charAt(0) && begin.charAt(1) != end.charAt(1)) {
            System.out.println();
            System.out.println("Error! Wrong ship location! Try again:");
            System.out.println();
            placing(map);
        } else if (this.length != length1 && this.length != length2) {
            System.out.println();
            System.out.println("Error! Wrong length of the " + this.name + "! Try again:");
            System.out.println();
            placing(map);
        } else if (!checkValidOfCell(begin, end, map)) {
            System.out.println();
            System.out.println("Error! You placed it too close to another one. Try again:");
            System.out.println();
            placing(map);
        } else {
            if (begin.charAt(0) == end.charAt(0)) {
                int letter = Main.FIRST_COLUMN.indexOf(begin.charAt(0));
                int numberOffset = Integer.parseInt(begin.substring(1));
                int numberEnd = Integer.parseInt(end.substring(1));
                shipPlacingUtil(letter, numberOffset, numberEnd, false, map);
            } else {
                int number = Integer.parseInt(begin.substring(1));
                int letterOffset = Main.FIRST_COLUMN.indexOf(begin.charAt(0));
                int letterEnd = Main.FIRST_COLUMN.indexOf(end.charAt(0));
                shipPlacingUtil(number, letterOffset, letterEnd, true, map);
            }
            Main.displayMap(map);
        }
    }

    public void shipPlacingUtil(int common, int offset, int end, boolean numeric, Cell[][] map) {
            if (offset > end) {
                int temp = end;
                end = offset;
                offset = temp;
            }
            for (int i = offset; i <= end; i++) {
                if (numeric) {
                    map[i][common].representation = "O ";
                    map[i][common].isPlaceable = false;
                    map[i][common].isPlaced = true;
                    map[i][common].ship = this;
                    if (common != 0) {
                        map[i][common - 1].isPlaceable = false;
                    }
                    if (common != 10) {
                        map[i][common + 1].isPlaceable = false;
                    }
                } else {
                    map[common][i].representation = "O ";
                    map[common][i].isPlaceable = false;
                    map[common][i].isPlaced = true;
                    map[common][i].ship = this;
                    if (common != 0) {
                        map[common - 1][i].isPlaceable = false;
                    }
                    if (common != 10) {
                        map[common + 1][i].isPlaceable = false;
                    }
                }
            }
            if (numeric) {
                if (offset != 1) {
                    if (common != 1) {
                        map[offset - 1][common - 1].isPlaceable = false;
                    }
                        map[offset - 1][common].isPlaceable = false;
                    if (common != 10) {
                        map[offset - 1][common + 1].isPlaceable = false;
                    }
                }
                if (end != 10) {
                    if (common != 1) {
                        map[end + 1][common - 1].isPlaceable = false;
                    }
                    map[end + 1][common].isPlaceable = false;
                    if (common != 10) {
                        map[end + 1][common + 1].isPlaceable = false;
                    }
                }
            } else {
                if (offset != 1) {
                    if (common != 1) {
                        map[common - 1][offset - 1].isPlaceable = false;
                    }
                    map[common][offset - 1].isPlaceable = false;
                    if (common != 10) {
                        map[common + 1][offset - 1].isPlaceable = false;
                    }
                }
                if (end != 10) {
                    if (common != 1) {
                        map[common - 1][end + 1].isPlaceable = false;
                    }
                    map[common][end + 1].isPlaceable = false;
                    if (common != 10) {
                        map[common + 1][end + 1].isPlaceable = false;
                    }
                }
            }
    }
    public boolean checkValidOfCell(String begin, String end, Cell[][] map) {
        if (begin.charAt(0) == end.charAt(0)) {
            int letter = Main.FIRST_COLUMN.indexOf(begin.charAt(0));
            int numberOffset = Character.getNumericValue(begin.charAt(1));
            int numberEnd = Character.getNumericValue(end.charAt(1));
            if (numberOffset > numberEnd) {
                int temp = numberEnd;
                numberEnd = numberOffset;
                numberOffset = temp;
            }
            for (int i = numberOffset; i <= numberEnd; i++) {
                if (!map[letter][i].isPlaceable) {
                    return false;
                }
            }
        } else {
            int number = Character.getNumericValue(begin.charAt(1));
            int letterOffset = Main.FIRST_COLUMN.indexOf(begin.charAt(0));
            int letterEnd = Main.FIRST_COLUMN.indexOf(end.charAt(0));
            if (letterOffset > letterEnd) {
                int temp = letterEnd;
                letterEnd = letterOffset;
                letterOffset = temp;
            }
            for (int i = letterOffset; i <= letterEnd; i++) {
                if (!map[i][number].isPlaceable) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getCapacity() {
        return capacity;
    }
}

import java.util.Scanner;

public class TestGrid {
    public static void main(String[] args) {
        RandomInteger random = new RandomInteger();
        int Ox = random.RandomNumber(0, 3);
        int Oy = random.RandomNumber(0, 3);
        Character ch = Factory.createChar(CharType.Warrior, "Mundo", Ox, Oy,1,1);
        System.out.println(ch);
        Grid map = Grid.createMap(3, 3, ch);
        map.printMap();
        System.out.println();

        Scanner scan = new Scanner(System.in);
        while (ch.current_life!=0 && map.current_player_cell.type != CellEntity.FINISH) {
            try {
                System.out.println("Enter a move - N, S, W, E");
                String move = scan.nextLine();
                switch (move) {
                    case "N" -> map.goNorth();
                    case "S" -> map.goSouth();
                    case "W" -> map.goWest();
                    case "E" -> map.goEast();
                    default -> throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                e.printStackTrace();
                System.out.println("Try again!");
            }
            map.printMap();
            System.out.println();
            System.out.println("There is " + map.current_player_cell.type);
        }
        System.out.println("Victory!");
    }
}

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class TestHardCoded {
    public static void main(String[] args) {
        JsonInput input = new JsonInput();
        Map<CellEntity, ArrayList<String>> dictionary = input.createStory();
        ArrayList<Account> accounts = input.createAccounts();
        Scanner scan = new Scanner(System.in);
        System.out.println("Select an account: ");
        System.out.println();
        for (int i=0; i<accounts.size();i++) {
            System.out.println(i + ". " + accounts.get(i).account.name);
        }
        System.out.println();
        int number = -1;
        boolean selected = false;
        while (!selected){
            String select_account = scan.nextLine();
            System.out.println();
            try {
                number = Integer.parseInt(select_account);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format!\n");
            }
            if (number>=0 && number<accounts.size()) {
                selected = true;
                System.out.println("Account: " + accounts.get(number).account.name);
                System.out.println("Password: ");
                String password = scan.nextLine();
                if (accounts.get(number).account.credential.getPassword().equals(password)) {
                    System.out.println("\nLogin successfully!\n");
                    System.out.println();
                } else {
                    System.out.println("\nIncorrect password!\n");
                    return;
                }
            }
            if (!selected) {
                System.out.println("\nThis account does not exist!\n");
            }
        }

        System.out.println("\nSelect a character: ");
        System.out.println();
        for (int i=0; i<accounts.get(number).all_characters.size(); i++) {
            System.out.println(i + ". " + accounts.get(number).all_characters.get(i).name);
        }
        System.out.println();

        selected = false;
        int character = -1;
        while (!selected) {
            String select_character = scan.nextLine();
            try {
                character = Integer.parseInt(select_character);
            } catch (NumberFormatException e){
                System.out.println("Incorrect format!\n");
            }
            System.out.println();
            if (character>=0 && character<accounts.get(number).all_characters.size()) {
                selected = true;
                System.out.println("Name : " + accounts.get(number).all_characters.get(character).name);
                System.out.println("Level: " + accounts.get(number).all_characters.get(character).level);
                System.out.println("XP   : " + accounts.get(number).all_characters.get(character).current_experience);
            } else {
                System.out.println("\nThis character does not exist!\n");
            }
        }
        Character player = accounts.get(number).all_characters.get(character);
        player.Ox = 0;
        player.Oy = 0;
        Grid map = Grid.createMap(5, 5, player);

        //line 0
        map.get(0).get(1).type = CellEntity.EMPTY;
        map.get(0).get(2).type = CellEntity.EMPTY;
        map.get(0).get(4).type = CellEntity.EMPTY;

        map.get(0).get(3).type = CellEntity.SHOP;
        map.get(0).get(3).content = new Shop();
        Shop shop = (Shop) map.get(0).get(3).content;
        shop.potions.add(new HealthPotion(10, 1, 10));
        shop.potions.add(new ManaPotion(10,2,5));
        map.get(0).get(3).content = shop;

        //line 1
        map.get(1).get(0).type = CellEntity.EMPTY;
        map.get(1).get(1).type = CellEntity.EMPTY;
        map.get(1).get(2).type = CellEntity.EMPTY;
        map.get(1).get(3).content = new Shop();
        map.get(1).get(3).type = CellEntity.SHOP;
        map.get(1).get(4).type = CellEntity.EMPTY;

        //line 3
        map.get(2).get(0).content = new Shop();
        map.get(2).get(0).type = CellEntity.SHOP;
        map.get(2).get(1).type = CellEntity.EMPTY;
        map.get(2).get(2).type = CellEntity.EMPTY;
        map.get(2).get(3).type = CellEntity.EMPTY;
        map.get(2).get(4).type = CellEntity.EMPTY;

        //line 4
        map.get(3).get(0).type = CellEntity.EMPTY;
        map.get(3).get(1).type = CellEntity.EMPTY;
        map.get(3).get(2).type = CellEntity.EMPTY;
        map.get(3).get(3).type = CellEntity.EMPTY;
        map.get(3).get(4).type = CellEntity.ENEMY;
        map.get(3).get(4).content = new Enemy();
        Enemy enemy = (Enemy) map.get(3).get(4).content;
        enemy.current_life = 100;
        map.get(3).get(4).content = enemy;

        //line 5
        map.get(4).get(0).type = CellEntity.EMPTY;
        map.get(4).get(1).type = CellEntity.EMPTY;
        map.get(4).get(2).type = CellEntity.EMPTY;
        map.get(4).get(3).type = CellEntity.EMPTY;
        map.get(4).get(4).type = CellEntity.FINISH;

        System.out.println();
        Game test = Game.getInstance();
        test.accounts = accounts;
        test.map = map;
        test.dictionary = dictionary;
        test.map.printMap();
        test.options(0);
    }
}

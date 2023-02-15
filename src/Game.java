import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {

    static Game Instance;
    ArrayList<Account> accounts;
    Map<CellEntity, ArrayList<String>> dictionary;
    Grid map;

    public static Game getInstance() {
        if (Instance == null)
            Instance = new Game();
        return Instance;
    }

    private Game() {}

    public void run() {
        JsonInput input = new JsonInput();
        Map dictionary = input.createStory();
        ArrayList accounts = input.createAccounts();
        this.dictionary = dictionary;
        this.accounts = accounts;

        Scanner scan = new Scanner(System.in);

        try {
            System.out.println("Choose the game mode:   1. Console");
            System.out.println("                        2. Graphic Interface");
            String game_mode = scan.nextLine();
            System.out.println();
            switch (game_mode) {
                case "1" -> System.out.println("Console game!");
                case "2" -> System.out.println("Graphic Interface game!");
                default -> throw new InvalidCommandException();
            }

            //console game
            if (game_mode.equals("1")) {
                System.out.println();
                System.out.println("Select an account: ");
                System.out.println();
                for (int i=0; i<this.accounts.size();i++) {
                    System.out.println(i + ". " + this.accounts.get(i).account.name);
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
                    if (number>=0 && number<this.accounts.size()) {
                        selected = true;
                        System.out.println("Account: " + this.accounts.get(number).account.name);
                        System.out.println("Password: ");
                        String password = scan.nextLine();
                        if (this.accounts.get(number).account.credential.getPassword().equals(password)) {
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
                for (int i=0; i<this.accounts.get(number).all_characters.size(); i++) {
                    System.out.println(i + ". " + this.accounts.get(number).all_characters.get(i).name);
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
                    if (character>=0 && character<this.accounts.get(number).all_characters.size()) {
                        selected = true;
                        System.out.println("Name : " + this.accounts.get(number).all_characters.get(character).name);
                        System.out.println("Level: " + this.accounts.get(number).all_characters.get(character).level);
                        System.out.println("XP   : " + this.accounts.get(number).all_characters.get(character).current_experience);
                    } else {
                        System.out.println("\nThis character does not exist!\n");
                    }
                }
                RandomInteger rand = new RandomInteger();
                int measure = rand.RandomNumber(3,11);
                int Ox = rand.RandomNumber(0, measure);
                int Oy = rand.RandomNumber(0, measure);
                Character player = this.accounts.get(number).all_characters.get(character);
                player.Ox = Ox;
                player.Oy = Oy;
                this.map = Grid.createMap(measure, measure, player);
                System.out.println();
                this.startRules();
                System.out.println();
                this.map.printMap();
                System.out.println();
                this.options(number);
            }

            //graphic game
            if (game_mode.equals("2")) {
                System.out.println();
                ArrayList<Account> list = new ArrayList<>();

                Main start = new Main();
                start.CreateWindow();
                while (!start.start.getModel().isPressed()) {
                    System.out.print("");
                }

                Vector users = new Vector();
                for(int i=0; i<accounts.size(); i++) {
                    list.add((Account) accounts.get(i));
                    users.add(list.get(i).account.name);
                }

                boolean choose = false;

                String name = null;
                while (!choose) {
                    SelectAccount sa = new SelectAccount(users, "Account");
                    while (!sa.selected && !sa.create) {
                        System.out.print("");
                    }

                    if (sa.selected) {
                        name = sa.selected_name;
                        choose = true;
                    }

                    if (sa.create) {
                        NewGraphicAccount nou = new NewGraphicAccount();
                        while (!nou.create) {
                            System.out.print("");
                        }

                        if (nou.success){
                            if (users.contains(nou.new_account.account.name)) {
                                System.out.println("This account already exists!");
                                System.out.println();
                            } else {
                                list.add(nou.new_account);
                                users.add(nou.new_account.account.name);
                                accounts.add(nou.new_account);
                                System.out.println();
                                System.out.println("New account:");
                                System.out.println(nou.new_account);
                                System.out.println();
                                JsonOutput save = new JsonOutput();
                                save.createOutput(this.accounts);
                                input = new JsonInput();
                                dictionary = input.createStory();
                                accounts = input.createAccounts();
                                this.dictionary = dictionary;
                                this.accounts = accounts;

                            }
                        }
                        else {
                            System.out.println("Try again!");
                            System.out.println();
                        }
                    }
                }


                ArrayList<Account> account = new ArrayList<>();
                for (Object o : accounts) {
                    account.add((Account) o);
                }

                Login log = new Login(name, account);
                while (!log.success) {
                    System.out.print("");
                }
                System.out.println("\nHello, " + name + "!");
                System.out.println();

                Account selected_account = null;
                int s;
                for (s=0; s<account.size(); s++) {
                    if (account.get(s).account.name.equals(name)) {
                        selected_account = account.get(s);
                        break;
                    }
                }

                Vector characters_list = new Vector();
                for (int j=0; j<selected_account.all_characters.size(); j++) {
                    characters_list.add(selected_account.all_characters.get(j).name);
                }

                choose = false;
                String character_name = null;
                Character new_character;
                while (!choose) {
                    SelectAccount sc = new SelectAccount(characters_list, "Character");
                    while (!sc.selected && !sc.create) {
                        System.out.print("");
                    }

                    if (sc.selected) {
                        character_name = sc.selected_name;
                        choose = true;
                    }

                    if (sc.create) {
                        NewGraphicCharacter nou = new NewGraphicCharacter();
                        while (!nou.create) {
                            System.out.print("");
                        }

                        if (characters_list.contains(nou.char_name)) {
                            System.out.println("This character already exists!");
                            System.out.println();
                        } else {
                            try {
                                if (nou.char_name == null)
                                    throw new InformationIncompleteException();
                                else {
                                    new_character = Factory.createChar(nou.type, nou.char_name, 1,1,0,1);
                                    characters_list.add(new_character.name);
                                    character_name = new_character.name;
                                    selected_account.all_characters.add(new_character);
                                    System.out.println();
                                    System.out.println("New character:");
                                    System.out.println(new_character);
                                    System.out.println();
                                    JsonOutput save = new JsonOutput();
                                    save.createOutput(this.accounts);
                                    input = new JsonInput();
                                    dictionary = input.createStory();
                                    accounts = input.createAccounts();
                                    this.dictionary = dictionary;
                                    this.accounts = accounts;

                                }
                            } catch (InformationIncompleteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                Character selected_character = null;
                int i;
                for (i=0; i<selected_account.all_characters.size(); i++) {
                    if (selected_account.all_characters.get(i).name.equals(character_name)) {
                        selected_character = selected_account.all_characters.get(i);
                        break;
                    }
                }

                RandomInteger rand = new RandomInteger();
                int measure = rand.RandomNumber(3,11);
                int Ox = rand.RandomNumber(0, measure);
                int Oy = rand.RandomNumber(0, measure);
                selected_character.Ox = Ox;
                selected_character.Oy = Oy;

                this.map = Grid.createMap(measure, measure, selected_character);
                this.optionsGraphic(i);
                System.exit(0);
            }

        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }

    }

    public void optionsGraphic(int number) {
        JsonInputPictures pic = new JsonInputPictures();
        ArrayList war = pic.getPictures("Warrior");
        ArrayList rog = pic.getPictures("Rogue");
        ArrayList mag = pic.getPictures("Mage");

        RandomInteger rand_pic = new RandomInteger();
        int pic_rand;
        if (map.player instanceof Warrior) {
            pic_rand = rand_pic.RandomNumber(0, war.size());
            map.player.picture = String.valueOf(war.get(pic_rand));
            war.remove(pic_rand);
        }
        if (map.player instanceof Rogue) {
            pic_rand = rand_pic.RandomNumber(0, rog.size());
            map.player.picture = String.valueOf(rog.get(pic_rand));
            rog.remove(pic_rand);
        }
        if (map.player instanceof Mage) {
            pic_rand = rand_pic.RandomNumber(0, mag.size());
            map.player.picture = String.valueOf(mag.get(pic_rand));
            mag.remove(pic_rand);
        }


        while (map.player.current_life!=0 && map.current_player_cell.type != CellEntity.FINISH) {
            Table game = new Table(map.player, map);
            String move;
            while (game.move.equals("NoNe")) {
                System.out.print("");
            }
            move = game.move;
            if (move.equals("N"))
                map.goNorth();
            if (move.equals("E"))
                map.goEast();
            if (move.equals("W"))
                map.goWest();
            if (move.equals("S"))
                map.goSouth();
            stories();

            CellEntity type = map.current_player_cell.type;

            if (type == CellEntity.FINISH) {
                RandomInteger rand = new RandomInteger();
                int xp = rand.RandomNumber(20, 51);
                map.player.current_experience = map.player.current_experience + xp;
                map.player.upgradeLevel();
                int games = this.accounts.get(number).games_number;
                this.accounts.get(number).setGames_number(games+1);

                ShowCharacter shwow = new ShowCharacter(map.player);
                while (!shwow.exit_pressed)
                    System.out.print("");

                JsonOutput save = new JsonOutput();
                save.createOutput(this.accounts);
                return;
            }

            if (type == CellEntity.SHOP) {
                Shop shopping = (Shop) map.current_player_cell.content;

                boolean exit = false;
                while (!exit && shopping.potions.size()!=0) {
                    ShopGraphic shop = new ShopGraphic(map.player, shopping);
                    while (!shop.sell && !shop.exit_pressed) {
                        System.out.print("");
                    }

                    if (shop.sell) {
                        map.player.buyPotion(shopping.selectPotion(shop.potion));
                    }
                    else {
                        exit = true;
                    }
                }
            }

            if (type == CellEntity.ENEMY) {
                Enemy enemy = (Enemy) map.current_player_cell.content;
                RandomInteger rand_pic_enemy = new RandomInteger();
                int pic_rand_enemy = rand_pic_enemy.RandomNumber(1,4);
                int img_rand_enemy;
                if (pic_rand_enemy == 1) {
                    img_rand_enemy = rand_pic_enemy.RandomNumber(0, war.size());
                    enemy.picture = String.valueOf(war.get(img_rand_enemy));
                    war.remove(img_rand_enemy);
                }
                if (pic_rand_enemy == 2) {
                    img_rand_enemy = rand_pic_enemy.RandomNumber(0, rog.size());
                    enemy.picture = String.valueOf(rog.get(img_rand_enemy));
                    rog.remove(img_rand_enemy);
                }
                if (pic_rand_enemy == 3) {
                    img_rand_enemy = rand_pic_enemy.RandomNumber(0, mag.size());
                    enemy.picture = String.valueOf(mag.get(img_rand_enemy));
                    mag.remove(img_rand_enemy);
                }

                while (enemy.current_life>0 && map.player.current_life>0) {
                    EnemyGraphic fight = new EnemyGraphic(map.player, enemy);
                    while (!fight.attack_pressed && !fight.potion_pressed && !fight.ability_pressed) {
                        System.out.print("");
                    }

                    //attack
                    if (fight.attack_pressed) {
                        System.out.println("-> Attack\n");
                        enemy.recieveDamage(map.player.getDamage(null, true));
                    }

                    //use ability
                    if (fight.ability_pressed) {
                        AbilityGraphic use = new AbilityGraphic(map.player);
                        while (!use.pressed) {
                            System.out.print("");
                        }
                        Spell spell = map.player.abilities.get(use.ability);
                        map.player.useAbility(spell, enemy, false);
                    }

                    //use potion
                    if (fight.potion_pressed) {
                        if (map.player.backpack.potions.size() == 0) {
                            System.out.println("-> There is no potion in your backpack!");
                            System.out.println("-> You lost your turn!\n");
                        } else {
                            PotionGraphic potion = new PotionGraphic(map.player);
                            while (!potion.select) {
                                System.out.print("");
                            }
                            Potion p = map.player.backpack.potions.get(potion.potion);
                            if (p instanceof HealthPotion)
                                map.player.lifeRegenerarion(p.regenerationValue());
                            else
                                map.player.manaRegeneration(p.regenerationValue());
                            map.player.backpack.removePotion(p);
                        }
                    }

                    //if enemy is alive
                    if (enemy.current_life > 0) {
                        System.out.println();
                        //if enemy has spells
                        if (enemy.abilities.size() > 0) {
                            //chance enemy uses spell or normal attack
                            RandomInteger rand = new RandomInteger();
                            int chance = rand.RandomNumber(1, 101);
                            if (chance >= 70) {
                                System.out.println("-> It used a spell against you!\n");
                                int spell = rand.RandomNumber(0, enemy.abilities.size());
                                enemy.useAbility(enemy.abilities.get(spell), map.player, false);
                            } else
                                map.player.recieveDamage(enemy.getDamage(null, true));
                        } else
                            map.player.recieveDamage(enemy.getDamage(null, true));
                    }
                }
                if (map.player.current_life > 0) {
                    System.out.println("-> Victory!\n");
                    map.player.killd_enemies++;
                    map.player.victoryGold();
                    RandomInteger rand = new RandomInteger();
                    int xp = rand.RandomNumber(10,31);
                    map.player.current_experience = map.player.current_experience + xp;
                    map.player.upgradeLevel();
                } else {
                    System.out.println("\n-> Defeat!\n");
                    System.out.println("\n>>> GAME OVER <<<\n");
                    return;
                }

            }


        }
    }


    public void options(int number) {
        Scanner scan = new Scanner(System.in);
        while (map.player.current_life!=0 && map.current_player_cell.type != CellEntity.FINISH) {
            try {
                boolean P_key = false;
                while (!P_key){
                    System.out.println("Press \"P\" for next move!");
                    String move = scan.nextLine();
                    if (move.equals("P"))
                        P_key = true;
                }
                System.out.println();
                System.out.println("Enter a move - N (north)");
                System.out.println("               S (south)");
                System.out.println("               E (east)");
                System.out.println("               W (west)");
                String move = scan.nextLine();
                System.out.println();
                switch (move) {
                    case "N" -> map.goNorth();
                    case "S" -> map.goSouth();
                    case "W" -> map.goWest();
                    case "E" -> map.goEast();
                    default -> throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                e.printStackTrace();
                System.out.println("\nTry again!\n");
            }
            map.printMap();
            System.out.println();
            stories();

            CellEntity type = map.current_player_cell.type;

            //finish
            if (type == CellEntity.FINISH) {
                System.out.println(">>> CONGRATULATIONS! <<<");
                RandomInteger rand = new RandomInteger();
                int xp = rand.RandomNumber(20, 51);
                map.player.current_experience = map.player.current_experience + xp;
                map.player.upgradeLevel();
                int games = this.accounts.get(number).games_number;
                this.accounts.get(number).setGames_number(games+1);
                System.out.println();
                System.out.println("Name  : " + map.player.name);
                System.out.println("Level : " + map.player.level);
                System.out.println("XP    : " + map.player.current_experience);
                JsonOutput save = new JsonOutput();
                save.createOutput(this.accounts);
            }

            //shop
            if (type == CellEntity.SHOP) {
                Character player = map.player;
                Shop shop = (Shop) map.current_player_cell.content;
                System.out.println("\n BACKPACK:");
                System.out.println("->Gold       : " + player.backpack.gold);
                System.out.println("->Left weight: " + player.backpack.leftWeight());
                System.out.println("->Potions: ");
                for (int i = 0; i < player.backpack.potions.size(); i++) {
                    System.out.println(i + ". " + player.backpack.potions.get(i));
                }

                boolean buy = true;
                while (shop.potions.size() > 0 && buy) {
                    boolean correct = false;
                    while (!correct) {
                        System.out.println("\n SHOP:");
                        for (int i = 0; i < shop.potions.size(); i++) {
                            System.out.println(i + ". " + shop.potions.get(i));
                        }
                        System.out.println("\n? Want to buy something from this store <Y = yes - N = no> ?");
                        String shopping = scan.nextLine();
                        System.out.println();
                        if (shopping.equals("Y")) {
                            System.out.println("\nPick a potion!\n");
                            String p = scan.nextLine();
                            System.out.println();
                            int potion = -1;
                            try {
                                potion = Integer.parseInt(p);
                            } catch (NumberFormatException e) {
                                System.out.println("Incorrect format!\n");
                            }
                            if (potion >= 0 && potion < shop.potions.size()) {
                                if (player.buyPotion(shop.selectPotion(potion))) {
                                    correct = true;
                                    System.out.println("\n BACKPACK:");
                                    System.out.println("->Gold       : " + player.backpack.gold);
                                    System.out.println("->Left weight: " + player.backpack.leftWeight());
                                    System.out.println("->Potions: ");
                                    for (int i = 0; i < player.backpack.potions.size(); i++) {
                                        System.out.println(i + ". " + player.backpack.potions.get(i));
                                    }
                                }
                            }
                        }
                        if (shopping.equals("N")) {
                            buy = false;
                            correct = true;
                        }
                    }
                }
                System.out.println("\n BACKPACK:");
                System.out.println("->Gold       : " + player.backpack.gold);
                System.out.println("->Left weight: " + player.backpack.leftWeight());
                System.out.println("->Potions: ");
                for (int i = 0; i < player.backpack.potions.size(); i++) {
                    System.out.println(i + ". " + player.backpack.potions.get(i));
                }
                System.out.println();
                map.printMap();
                System.out.println();
            }

            //enemy
            if (type == CellEntity.ENEMY) {
                System.out.println("\n-> Kill the enemy!\n");
                Character player = map.player;
                Enemy enemy = (Enemy) map.current_player_cell.content;
                while (player.current_life>0 && enemy.current_life>0) {
                    try {
                        System.out.println();
                        System.out.println("1. Attack");
                        System.out.println("2. Use ability");
                        System.out.println("3. Use potion");
                        System.out.println();
                        String option = scan.nextLine();
                        System.out.println();
                        switch (option) {
                            case "1" -> {
                                System.out.println("Attack\n");
                                enemy.recieveDamage(player.getDamage(null, true));
                                System.out.println("\nEnemy -> health = " + enemy.current_life);
                                System.out.println("      -> mana = " + enemy.current_mana);
                                System.out.println("You -> health = " + player.current_life);
                                System.out.println("    -> mana = " + player.current_mana);
                            }
                            case "2" -> {
                                System.out.println("Ability\n");
                                System.out.println("Choose a spell: ");
                                for (int i = 0; i < player.abilities.size(); i++) {
                                    System.out.println(i + ". " + player.abilities.get(i));
                                }
                                boolean correct = false;
                                while (!correct) {
                                    String spell = scan.nextLine();
                                    int ability = -1;
                                    try {
                                        ability = Integer.parseInt(spell);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Incorrect format!\n");
                                    }

                                    if (ability >= 0 && ability < player.abilities.size()) {
                                        Spell s = player.abilities.get(ability);
                                        player.useAbility(s, enemy, false);
                                        System.out.println("\nEnemy -> health = " + enemy.current_life);
                                        System.out.println("      -> mana = " + enemy.current_mana);
                                        System.out.println("You -> health = " + player.current_life);
                                        System.out.println("    -> mana = " + player.current_mana);
                                        correct = true;
                                    } else
                                        System.out.println("\nTry again!\n");
                                }
                            }
                            case "3" -> {
                                System.out.println("Potion\n");
                                if (player.backpack.potions.size() == 0) {
                                    System.out.println("-> There is no potion in your backpack!");
                                    System.out.println("-> You lost your turn!\n");
                                } else {
                                    System.out.println("Choose a potion: ");
                                    for (int i = 0; i < player.backpack.potions.size(); i++) {
                                        System.out.println(i + ". " + player.backpack.potions.get(i));
                                    }
                                    boolean correct = false;
                                    while (!correct) {
                                        String potions = scan.nextLine();
                                        int potion = -1;
                                        try {
                                            potion = Integer.parseInt(potions);
                                        } catch (NumberFormatException e) {
                                            System.out.println("Incorrect format!\n");
                                        }

                                        if (potion >= 0 && potion < player.backpack.potions.size()) {
                                            Potion p = player.backpack.potions.get(potion);
                                            if (p instanceof HealthPotion)
                                                player.lifeRegenerarion(p.regenerationValue());
                                            else
                                                player.manaRegeneration(p.regenerationValue());
                                            player.backpack.removePotion(p);
                                            System.out.println("You -> health = " + player.current_life);
                                            System.out.println("    -> mana = " + player.current_mana);
                                            System.out.println();
                                            for (int i = 0; i < player.backpack.potions.size(); i++) {
                                                System.out.println(i + ". " + player.backpack.potions.get(i));
                                            }
                                            System.out.println();
                                            correct = true;
                                        } else
                                            System.out.println("\nTry again!\n");
                                    }
                                }
                            }
                            default -> throw new InvalidCommandException();
                        }
                    } catch (InvalidCommandException e) {
                        e.printStackTrace();
                    }

                    //if enemy is alive
                    if (enemy.current_life > 0) {
                        System.out.println("\n-> Enemy's turn.");
                        //if enemy has spells
                        if (enemy.abilities.size() > 0) {
                            //chance enemy uses spell or normal attack
                            RandomInteger rand = new RandomInteger();
                            int chance = rand.RandomNumber(1, 101);
                            if (chance >= 70) {
                                System.out.println("\n-> It used a spell against you!\n");
                                int spell = rand.RandomNumber(0, enemy.abilities.size());
                                enemy.useAbility(enemy.abilities.get(spell), player, false);
                            } else
                                player.recieveDamage(enemy.getDamage(null, true));
                        } else
                            player.recieveDamage(enemy.getDamage(null, true));

                        System.out.println("\nEnemy -> health = " + enemy.current_life);
                        System.out.println("      -> mana = " + enemy.current_mana);
                        System.out.println("You -> health = " + player.current_life);
                        System.out.println("    -> mana = " + player.current_mana);
                        System.out.println();
                    }

                }
                if (player.current_life > 0) {
                    System.out.println("\n-> Victory!\n");
                    player.victoryGold();
                    RandomInteger rand = new RandomInteger();
                    int xp = rand.RandomNumber(10,31);
                    player.current_experience = player.current_experience + xp;
                    player.upgradeLevel();
                    System.out.println("Experience: " + player.current_experience);
                    System.out.println("Level: " + player.level);
                    System.out.println();
                } else {
                    System.out.println("\n-> Defeat!\n");
                    System.out.println("\n>>> GAME OVER <<<\n");
                    return;
                }

                map.printMap();
                System.out.println();
            }
        }
    }

    public void stories() {
        if (dictionary.isEmpty())
            return;
        if (map.current_player_cell.visited != 1)
            return;
        RandomInteger rand = new RandomInteger();
        int story;
        CellEntity type = map.current_player_cell.type;
        System.out.print("<<< ");
        if(dictionary.containsKey(CellEntity.EMPTY) && type == CellEntity.EMPTY) {
            story = rand.RandomNumber(0, dictionary.get(CellEntity.EMPTY).size());
            System.out.print(dictionary.get(CellEntity.EMPTY).get(story));
        } else
        if(dictionary.containsKey(CellEntity.ENEMY) && type == CellEntity.ENEMY) {
            story = rand.RandomNumber(0, dictionary.get(CellEntity.ENEMY).size());
            System.out.print(dictionary.get(CellEntity.ENEMY).get(story));
        } else
        if(dictionary.containsKey(CellEntity.SHOP) && type == CellEntity.SHOP) {
            story = rand.RandomNumber(0, dictionary.get(CellEntity.SHOP).size());
            System.out.print(dictionary.get(CellEntity.SHOP).get(story));
        }
        else if(dictionary.containsKey(CellEntity.FINISH) && type == CellEntity.FINISH) {
            story = rand.RandomNumber(0, dictionary.get(CellEntity.FINISH).size());
            System.out.print(dictionary.get(CellEntity.FINISH).get(story));
        }
        System.out.print(" >>>\n");
        System.out.println();
    }

    public void startRules() {
        try {
            System.out.println("-> Welcome to our dangerous world!");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("-> I'll give you a map! You are the big point ◉ .");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("""
                    -> If you are lucky, you will find shops where\s
                    you can buy elixirs for the health or mana regeneration.
                    -> Shop is the "S" on the map.""");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("-> Take care! You may also encounter enemies.");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("-> So, if you see an \"E\" on the map\n" +
                    "all I can do is wish you good luck.");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("-> I say start walking...\n" +
                    "Find the \"F\" on the map! Be very careful!");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

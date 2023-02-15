import java.util.ArrayList;

public class Grid extends ArrayList<ArrayList<Cell>> {
    int height;
    int width;
    Character player;
    Cell current_player_cell;

    private Grid(int height, int width, Character player) {
        this.height = height;
        this.width = width;
        this.player = player;
        current_player_cell = new Cell(player.Ox, player.Oy, CellEntity.EMPTY, null, 1);
    }

    static Grid createMap(int height, int width, Character player) {
        Grid map = new Grid(height, width, player);
        for (int i=0; i<height; i++) {
            ArrayList<Cell> list = new ArrayList<>();
            for (int j=0; j<width; j++) {
                list.add(new Cell(i, j, CellEntity.EMPTY, null, 0));
            }
            map.add(list);
        }

        //start cell
        map.get(map.current_player_cell.Ox).get(map.current_player_cell.Oy).visited = 1;
        map.get(map.current_player_cell.Ox).get(map.current_player_cell.Oy).type = CellEntity.PLAYER;

        //finish cell
        RandomInteger random = new RandomInteger();
        int finish = 0;
        while (finish == 0) {
            int Ox = random.RandomNumber(0, height);
            int Oy = random.RandomNumber(0, width);
            if (map.get(Ox).get(Oy).type == CellEntity.EMPTY) {
                map.get(Ox).get(Oy).type = CellEntity.FINISH;
                finish = 1;
            }
        }

        int shop_number = random.RandomNumber(2,(height*width)/4+1);
        int enemy_number = random.RandomNumber(4,(height*width)/2+1);

        //generate shop
        for(int i=1; i<=shop_number; i++){
            int ox = random.RandomNumber(0, height);
            int oy = random.RandomNumber(0, width);
            if(map.get(ox).get(oy).type == CellEntity.EMPTY){
                map.get(ox).get(oy).type = CellEntity.SHOP;
                map.get(ox).get(oy).content = new Shop();
            } else
                i--;
        }

        //generate enemy
        for(int i=1; i<=enemy_number; i++){
            int ox = random.RandomNumber(0, height);
            int oy = random.RandomNumber(0, width);
            if(map.get(ox).get(oy).type == CellEntity.EMPTY){
                map.get(ox).get(oy).type = CellEntity.ENEMY;
                map.get(ox).get(oy).content = new Enemy();
            } else
                i--;
        }

        return map;
    }

    public void printMap() {
        for (int i=0; i<this.height; i++){
            for (int j=0; j<this.width; j++) {
                if (i==player.Ox && j==player.Oy)
                    System.out.print("◉");
                if (this.get(i).get(j).visited == 0)
                    System.out.print("?");
                else if (this.get(i).get(j).type == CellEntity.EMPTY)
                    System.out.print("_");
                else if (this.get(i).get(j).type == CellEntity.FINISH)
                        System.out.print("F");
                else if (this.get(i).get(j).content == null)
                        System.out.print("");
                else
                        System.out.print(this.get(i).get(j).content.toCharacter());
                System.out.print("   ");
            }
            System.out.println();
        }
    }

    public void goNorth() {
        if (player.Ox == 0) {
            System.out.println("Impossible to walk in that direction!");
            System.out.println();
            return;
        }
        if (this.get(player.Ox).get(player.Oy).type == CellEntity.FINISH)
            return;

        this.get(player.Ox).get(player.Oy).type = CellEntity.EMPTY;
        player.Ox--;
        current_player_cell = this.get(player.Ox).get(player.Oy);
        current_player_cell.visited++;
        if (current_player_cell.visited == 1 && current_player_cell.type == CellEntity.EMPTY) {
            RandomInteger random = new RandomInteger();
            int chance = random.RandomNumber(1, 101);
            int amount = random.RandomNumber(20, 31);
            if(chance <= 20) {
                player.backpack.gold = player.backpack.gold + amount;
                System.out.println("-> You found some gold! +" + amount);
                System.out.println();
            }
        }
    }

    public void goSouth() {
        if(player.Ox == height-1) {
            System.out.println("Impossible to walk in that direction!");
            System.out.println();
            return;
        }
        if(this.get(player.Ox).get(player.Oy).type == CellEntity.FINISH)
            return;

        this.get(player.Ox).get(player.Oy).type = CellEntity.EMPTY;
        player.Ox++;
        current_player_cell = this.get(player.Ox).get(player.Oy);
        current_player_cell.visited++;
        if (current_player_cell.visited == 1 && current_player_cell.type == CellEntity.EMPTY) {
            RandomInteger random = new RandomInteger();
            int chance = random.RandomNumber(1, 101);
            int amount = random.RandomNumber(20, 31);
            if(chance <= 20) {
                player.backpack.gold = player.backpack.gold + amount;
                System.out.println("-> You found some gold! +" + amount);
                System.out.println();
            }
        }
    }

    public void goWest() {
        if (player.Oy == 0) {
            System.out.println("Impossible to walk in that direction!");
            System.out.println();
            return;
        }
        if (this.get(player.Ox).get(player.Oy).type == CellEntity.FINISH)
            return;

        this.get(player.Ox).get(player.Oy).type = CellEntity.EMPTY;
        player.Oy--;
        current_player_cell = this.get(player.Ox).get(player.Oy);
        current_player_cell.visited++;
        if (current_player_cell.visited == 1 && current_player_cell.type == CellEntity.EMPTY) {
            RandomInteger random = new RandomInteger();
            int chance = random.RandomNumber(1, 101);
            int amount = random.RandomNumber(20, 31);
            if(chance <= 20) {
                player.backpack.gold = player.backpack.gold + amount;
                System.out.println("-> You found some gold! +" + amount);
                System.out.println();
            }
        }
    }
    public void goEast() {
        if (player.Oy == width-1) {
            System.out.println("Impossible to walk in that direction!");
            System.out.println();
            return;
        }
        if (this.get(player.Ox).get(player.Oy).type == CellEntity.FINISH)
            return;

        this.get(player.Ox).get(player.Oy).type = CellEntity.EMPTY;
        player.Oy++;
        current_player_cell = this.get(player.Ox).get(player.Oy);
        current_player_cell.visited++;
        if (current_player_cell.visited == 1 && current_player_cell.type == CellEntity.EMPTY) {
            RandomInteger random = new RandomInteger();
            int chance = random.RandomNumber(1, 101);
            int amount = random.RandomNumber(20, 31);
            if(chance <= 20) {
                player.backpack.gold = player.backpack.gold + amount;
                System.out.println("-> You found some gold! +" + amount);
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
        return "Grid{" +
                "height=" + height +
                ", width=" + width +
                ", player=" + player +
                ", current_player_cell=" + current_player_cell +
                '}';
    }
}

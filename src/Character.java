import java.util.ArrayList;

public abstract class Character extends Entity {
    public String name;
    public int Ox;
    public int Oy;
    public Inventory backpack;
    public int current_experience;
    public int level;
    public int strength;
    public int charisma;
    public int dexterity;
    public int killd_enemies;

    public Character(ArrayList<Spell> abilities, int max_life, int max_mana, boolean fire_protection, boolean ice_protection, boolean earth_protection, String name, int ox, int oy, int current_experience, int level, int strength, int charisma, int dexterity) {
        super(abilities, max_life, max_mana, fire_protection, ice_protection, earth_protection);
        this.name = name;
        Ox = ox;
        Oy = oy;

        Inventory backpack = new Inventory(100);
        if (this.fire_protection)
            backpack.max_weight = 60;
        if (this.earth_protection)
            backpack.max_weight = 40;
        if (this.ice_protection)
            backpack.max_weight = 20;

        backpack.gold = 100;

        this.backpack = backpack;
        this.current_experience = current_experience;
        this.level = level;
        this.strength = strength;
        this.charisma = charisma;
        this.dexterity = dexterity;
        this.killd_enemies = 0;
    }

    public boolean buyPotion(Potion potion) {
        if(backpack.getGold() >= potion.price() && backpack.leftWeight() >= potion.weight()) {
            backpack.addPotion(potion);
            backpack.setGold(backpack.getGold() - potion.price());
            return true;
        }
        else {
            System.out.println("\nYou can't buy this potion.");
            if(backpack.getGold() < potion.price()) {
                System.out.println("\nYou don't have enough gold!");
                System.out.println("Potion price = " + potion.price());
                System.out.println("Gold =  " + backpack.getGold());
                System.out.println();
            } else {
                System.out.println("\nExceed weight!");
                System.out.println("Potion weight = " + potion.weight());
                System.out.println("Backpack weight available = " + backpack.leftWeight());
                System.out.println();
            }
            return false;
        }
    }

    public void victoryGold() {
        RandomInteger random = new RandomInteger();
        int chance = random.RandomNumber(1, 101);
        if(chance <= 80) {
            int amount = random.RandomNumber(10, 21);
            this.backpack.gold = this.backpack.gold + amount;
            System.out.println("-> Nice fight! Take some gold! +" + amount);
            System.out.println("\n");
        }
    }

    public void upgradeLevel() {
        if(current_experience >= 100) {
            level++;
            current_experience = current_experience-100;
        }

        if (this instanceof Warrior) {
            strength = level * 4;
            dexterity = level * 2;
            charisma = level * 2;
        }
        else if (this instanceof Rogue) {
            strength = level * 2;
            dexterity = level * 4;
            charisma = level * 2;
        } else {
            strength = level * 2;
            dexterity = level * 2;
            charisma = level * 4;
        }

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Character{");
        sb.append("name='").append(name).append('\'');
        sb.append(", Ox=").append(Ox);
        sb.append(", Oy=").append(Oy);
        sb.append(", backpack=").append(backpack);
        sb.append(", current_experience=").append(current_experience);
        sb.append(", level=").append(level);
        sb.append(", strength=").append(strength);
        sb.append(", charisma=").append(charisma);
        sb.append(", dexterity=").append(dexterity);
        sb.append('}');
        return sb.toString();
    }
}

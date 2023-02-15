import java.util.ArrayList;

public class Factory {

    public static Character createChar(CharType type, String name, int Ox, int Oy, int experience , int level) {
        ArrayList<Spell> abilities = new ArrayList<>();
        RandomInteger rand = new RandomInteger();
        int damage = rand.RandomNumber(10, 31);
        int mana = rand.RandomNumber(20, 31);
        abilities.add(new Fire(damage, mana));

        damage = rand.RandomNumber(10, 31);
        mana = rand.RandomNumber(20, 31);
        abilities.add(new Ice(damage, mana));

        damage = rand.RandomNumber(10, 31);
        mana = rand.RandomNumber(20, 31);
        abilities.add(new Earth(damage, mana));

        return switch (type) {
            case Warrior -> new Warrior(abilities, 100, 100,
                    true, false, false, name, Ox, Oy, experience, level);
            case Rogue -> new Rogue(abilities, 100, 100,
                    false, false, true, name, Ox, Oy, experience, level);
            case Mage -> new Mage(abilities, 100, 100,
                    false, true, false, name, Ox, Oy, experience, level);
        };
    }
}

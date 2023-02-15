import java.util.ArrayList;

public class Warrior extends Character {

    public Warrior(ArrayList<Spell> abilities, int max_life, int max_mana, boolean fire_protection, boolean ice_protection, boolean earth_protection, String name, int Ox, int Oy, int current_experience, int level) {
        super(abilities, max_life, max_mana, fire_protection, ice_protection, earth_protection, name, Ox, Oy, current_experience, level, 0, 0, 0);
        upgradeLevel();
    }

    @Override
    public void recieveDamage(int damage) {
        RandomInteger rand = new RandomInteger();
        int chance = rand.RandomNumber(1, 101);
        if (charisma > 10 && dexterity > 20 && chance > 50)
            current_life = Math.max(current_life - damage/2, 0);
        else
            current_life = Math.max(current_life - damage, 0);
    }

    @Override
    public int getDamage(Spell ability, boolean simple_attack) {
        int normal_damage = strength * 2;
        RandomInteger rand = new RandomInteger();
        if (simple_attack || current_mana<ability.mana_cost) {
            int chance = rand.RandomNumber(1, 101);
            if(chance > 50)
                normal_damage = normal_damage*2;
            return normal_damage;
        }

        int damage = ability.damage + normal_damage;
        this.current_mana = Math.max(current_mana - ability.mana_cost, 0);
        int chance = rand.RandomNumber(1, 101);
        if (chance > 50)
            damage = damage*2;
        return damage;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

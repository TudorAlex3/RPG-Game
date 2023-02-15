import java.util.ArrayList;

public class Mage extends Character {

    public Mage(ArrayList<Spell> abilities, int max_life, int max_mana, boolean fire_protection, boolean ice_protection, boolean earth_protection, String name, int ox, int oy, int current_experience, int level) {
        super(abilities, max_life, max_mana, fire_protection, ice_protection, earth_protection, name, ox, oy, current_experience, level, 0, 0, 0);
        upgradeLevel();
    }

    @Override
    public void recieveDamage(int damage) {
        RandomInteger rand = new RandomInteger();
        int chance = rand.RandomNumber(1, 101);
        if(strength > 20 && dexterity > 10 && chance > 50)
            current_life = current_life - damage/2;
        else
            current_life = current_life - damage;
    }

    @Override
    public int getDamage(Spell ability, boolean simple_attack) {
        int normal_damage = charisma*2;
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

    public void upgradeLevel() {
        if(current_experience == 100) {
            level++;
            current_experience = 0;
        }

        if (level != 0) {
            strength = level * 2;
            dexterity = level * 2;
            charisma = level * 3;
        }
        else {
            strength = 3;
            dexterity = 3;
            charisma = 5;
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

import java.util.ArrayList;

public class Enemy extends Entity implements CellElement {

    public Enemy() {
        super();
        RandomInteger rand = new RandomInteger();

        if(rand.RandomNumber(1, 3) == 1)
            fire_protection = true;
        else
            fire_protection = false;

        if(rand.RandomNumber(1, 3) == 1)
            ice_protection = true;
        else
            ice_protection = false;

        if(rand.RandomNumber(1, 3) == 1)
            earth_protection = true;
        else
            earth_protection = false;

        ArrayList<Spell> abilities = new ArrayList<>();
        int number = rand.RandomNumber(2, 5);
        for (int i=1; i<=number; i++) {
            int rand_potion = rand.RandomNumber(1, 4);
            int rand_damage = rand.RandomNumber(1, 6);
            int rand_mana = rand.RandomNumber(1, 11);
            if (rand_potion == 1)
                abilities.add(new Fire(rand_damage, rand_mana));
            if (rand_potion == 2)
                abilities.add(new Ice(rand_damage, rand_mana));
            if (rand_potion == 3)
                abilities.add(new Earth(rand_damage, rand_mana));
        }

        this.abilities = abilities;
        this.max_life = rand.RandomNumber(50, 101);
        this.current_life = this.max_life;
        this.max_mana = rand.RandomNumber(50, 101);
        this.current_mana = this.max_mana;
    }

    @Override
    public void recieveDamage(int damage) {
        RandomInteger rand = new RandomInteger();
        int chance = rand.RandomNumber(1, 101);
        if (chance <= 50) {
            current_life = Math.max(current_life - damage, 0);
            System.out.println("-> Nice shot!");
        } else
            System.out.println("-> Oh no! It avoided the blow!");
    }

    @Override
    public int getDamage(Spell ability, boolean simple_attack) {
        int normal_damage = 2;

        RandomInteger rand = new RandomInteger();
        if(simple_attack || this.current_mana < ability.mana_cost) {
            int chance = rand.RandomNumber(1, 101);
            if (chance > 50)
                normal_damage = normal_damage*2;
            return normal_damage;
        }

        int damage = ability.damage;
        this.current_mana = Math.max(current_mana - ability.mana_cost, 0);
        int chance = rand.RandomNumber(1, 101);
        if (chance > 50)
            damage = damage*2;
        return damage;
    }

    @Override
    public char toCharacter() {
        return 'E';
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Enemy{");
        sb.append("abilities=").append(abilities);
        sb.append(", current_life=").append(current_life);
        sb.append(", max_life=").append(max_life);
        sb.append(", current_mana=").append(current_mana);
        sb.append(", max_mana=").append(max_mana);
        sb.append(", fire_protection=").append(fire_protection);
        sb.append(", ice_protection=").append(ice_protection);
        sb.append(", earth_protection=").append(earth_protection);
        sb.append('}');
        return sb.toString();
    }
}

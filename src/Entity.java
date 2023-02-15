import java.util.ArrayList;

public abstract class Entity implements Element {
    public ArrayList<Spell> abilities;
    public int current_life;
    public int max_life;
    public int current_mana;
    public int max_mana;
    public boolean fire_protection;
    public boolean ice_protection;
    public boolean earth_protection;
    public String picture;

    public Entity() {}

    public Entity(ArrayList<Spell> abilities, int max_life, int max_mana, boolean fire_protection, boolean ice_protection, boolean earth_protection) {
        this.abilities = abilities;
        this.max_life = max_life;
        this.max_mana = max_mana;
        this.fire_protection = fire_protection;
        this.ice_protection = ice_protection;
        this.earth_protection = earth_protection;
        current_life = max_life;
        current_mana = max_mana;
    }

    public void lifeRegenerarion(int value) {
        int sum = current_life + value;
        current_life = Math.min(sum, max_life);
    }

    public void manaRegeneration(int value) {
        int sum = current_mana + value;
        current_mana = Math.min(sum, max_mana);
    }

    public void useAbility(Spell ability, Entity enemy, boolean normal_damage) {
        if (this.current_mana < ability.mana_cost) {
            if (this instanceof Enemy) {
                return;
            }
            System.out.println("-> Not enought mana!");
            System.out.println();
            return;
        }

        if(ability.name.equals("Ice") && enemy.ice_protection) {
            if (this instanceof Enemy) {
                this.abilities.remove(ability);
            }
            System.out.println("-> Ice protection!");
            System.out.println();
            return;
        }
        if(ability.name.equals("Fire") && enemy.fire_protection) {
            if (this instanceof Enemy) {
                this.abilities.remove(ability);
            }
            System.out.println("-> Fire protection!");
            System.out.println();
            return;
        }
        if(ability.name.equals("Earth") && enemy.earth_protection) {
            if (this instanceof Enemy) {
                this.abilities.remove(ability);
            }
            System.out.println("-> Earth protection!");
            System.out.println();
            return;
        }
        System.out.println("-> " + ability.name + " spell used!");
        enemy.recieveDamage(this.getDamage(ability, normal_damage));
        if (this instanceof Enemy) {
            this.abilities.remove(ability);
        }
    }


    public abstract void recieveDamage(int damage);
    public abstract int getDamage(Spell ability, boolean normal_damage);

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entity{");
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

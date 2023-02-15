public abstract class Spell implements Visitor {
    public String name;
    public int damage;
    public int mana_cost;

    public Spell(String name, int damage, int mana_cost) {
        this.damage = damage;
        this.mana_cost = mana_cost;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", damage=" + damage +
                ", mana_cost=" + mana_cost +
                '}';
    }
}

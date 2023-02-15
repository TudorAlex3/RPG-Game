public class Fire extends Spell {
    public Fire(int damage, int mana_cost) {
        super("Fire", damage, mana_cost);
    }

    @Override
    public void visit(Entity entity) {
        entity.accept(this);
    }
}

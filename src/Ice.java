public class Ice extends Spell {
    public Ice(int damage, int mana_cost) {
        super("Ice", damage, mana_cost);
    }

    @Override
    public void visit(Entity entity) {
        entity.accept(this);
    }
}

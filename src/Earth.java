public class Earth extends Spell {
    public Earth(int damage, int mana_cost) {
        super("Earth", damage, mana_cost);
    }

    @Override
    public void visit(Entity entity) {
        entity.accept(this);
    }
}

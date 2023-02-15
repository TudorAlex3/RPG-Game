public class ManaPotion implements Potion {

    private int price;
    private int weight;
    private int regeneration_value;

    public ManaPotion(int price, int weight, int regeneration_value) {
        this.price = price;
        this.weight = weight;
        this.regeneration_value = regeneration_value;
    }

    @Override
    public String usePotion() {
        return "Mana potion";
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public int regenerationValue() {
        return regeneration_value;
    }

    @Override
    public int weight() {
        return weight;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ManaPotion{");
        sb.append("price=").append(price);
        sb.append(", weight=").append(weight);
        sb.append(", regeneration_value=").append(regeneration_value);
        sb.append('}');
        return sb.toString();
    }
}

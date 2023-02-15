public class HealthPotion implements Potion {

    private int price;
    private int weight;
    private int regeneration_value;

    public HealthPotion(int price, int weight, int regeneration_value) {
        this.price = price;
        this.weight = weight;
        this.regeneration_value = regeneration_value;
    }

    @Override
    public String usePotion() {
        return "Health potion";
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
        final StringBuilder sb = new StringBuilder("HealthPotion{");
        sb.append("price=").append(price);
        sb.append(", weight=").append(weight);
        sb.append(", regeneration_value=").append(regeneration_value);
        sb.append('}');
        return sb.toString();
    }
}

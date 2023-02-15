import java.util.ArrayList;

public class Inventory {
    public ArrayList<Potion> potions;
    public int max_weight;
    public int gold;
    public int current_weight;

    public Inventory(int max_weight) {
        this.max_weight = max_weight;
        potions = new ArrayList<>();
        current_weight = 0;
        gold = 0;
    }

    public void addPotion(Potion potion) {
        potions.add(potion);
        current_weight = current_weight + potion.weight();
    }

    public void removePotion(Potion potion) {
        current_weight = current_weight - potion.weight();
        potions.remove(potion);
    }

    public int leftWeight() {
        return max_weight - current_weight;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Inventory{");
        sb.append("potions=").append(potions);
        sb.append(", max_weight=").append(max_weight);
        sb.append(", gold=").append(gold);
        sb.append(", current_weight=").append(current_weight);
        sb.append('}');
        return sb.toString();
    }
}

import java.util.ArrayList;

public class Shop implements CellElement {
    public ArrayList<Potion> potions;

    public Shop() {
        ArrayList<Potion> potions = new ArrayList<>();
        RandomInteger rand = new RandomInteger();
        int number = rand.RandomNumber(2, 5);
        for(int i=1; i<=number; i++){
            int rand_potion = rand.RandomNumber(1, 3);
            int price = rand.RandomNumber(5, 16);
            int weight = rand.RandomNumber(1, 6);
            int reg = rand.RandomNumber(5,11);
            if(rand_potion == 1)
                potions.add(new HealthPotion(price, weight, reg));
            else
                potions.add(new ManaPotion(price, weight, reg));
        }
        this.potions = potions;
    }

    public Potion selectPotion(int index) {
        Potion potion = potions.get(index);
        potions.remove(index);
        return potion;
    }

    @Override
    public char toCharacter() {
        return 'S';
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shop{");
        sb.append("potions=").append(potions);
        sb.append('}');
        return sb.toString();
    }
}

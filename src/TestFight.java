public class TestFight {
    public static void main(String[] args) {
        Enemy fight1 = new Enemy();
        System.out.println(fight1);
        System.out.println("fight1 life " + fight1.current_life);
        System.out.println("fight1 mana " + fight1.current_mana);
        System.out.println();

        Character fight2 = Factory.createChar(CharType.Warrior, "Mundo", 1,1,1,1);
        fight2.current_mana = 60;
        System.out.println(fight2);
        System.out.println("fight2 life " + fight2.current_life);
        System.out.println("fight2 mana " + fight2.current_mana);
        System.out.println();

        Shop shop = new Shop();
        System.out.println(shop);
        System.out.println();

        fight2.buyPotion(shop.selectPotion(0));
        System.out.println(shop);
        System.out.println(fight2);
        System.out.println();

        System.out.println("1 fights 2");
        fight1.useAbility(fight1.abilities.get(0), fight2, false);
        System.out.println(fight1);
        System.out.println("fight1 life " + fight1.current_life);
        System.out.println("fight1 mana " + fight1.current_mana);
        System.out.println();
        System.out.println(fight2);
        System.out.println("fight2 life " + fight2.current_life);
        System.out.println("fight2 mana " + fight2.current_mana);
        System.out.println();

        System.out.println("2 uses a potion");
        if(fight2.backpack.potions.get(0) != null) {
            if (fight2.backpack.potions.get(0) instanceof HealthPotion)
                fight2.lifeRegenerarion(fight2.backpack.potions.get(0).regenerationValue());
            else
                fight2.manaRegeneration(fight2.backpack.potions.get(0).regenerationValue());
            fight2.backpack.removePotion(fight2.backpack.potions.get(0));
        }
        System.out.println(fight2);
        System.out.println("fight2 life " + fight2.current_life);
        System.out.println("fight2 mana " + fight2.current_mana);
        System.out.println();

        while(fight1.current_life!=0) {
            System.out.println("2 fights 1");
            fight2.useAbility(fight2.abilities.get(0), fight1, false);
            System.out.println("fight1 life " + fight1.current_life);
            System.out.println("fight1 mana " + fight1.current_mana);
            System.out.println();
            System.out.println("fight2 life " + fight2.current_life);
            System.out.println("fight2 mana " + fight2.current_mana);
            System.out.println();
        }
        fight2.victoryGold();
        System.out.println("Dead!");
        System.out.println(fight2);

    }
}

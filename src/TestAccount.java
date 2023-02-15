import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class TestAccount {
    public static void main(String[] args) {
            Account nou1 = new Account();

            Account.Information.InfoBuilder build1 = new Account.Information.InfoBuilder();

            Credentials c1 = new Credentials();
            c1.setEmail("yahoo");
            c1.setPassword("123");
            build1.credentials(c1);

            SortedSet favorite_games = new TreeSet();
            favorite_games.add("LOL");
            favorite_games.add("Counter");
            favorite_games.add("GTA");
            build1.favorite_games(favorite_games);

            build1.country("UK");
            build1.name("Account1");

        Account.Information info1 = null;
        try {
            info1 = new Account.Information(build1);
        } catch (InformationIncompleteException e) {
            e.printStackTrace();
        }
        nou1.setAccount(info1);


            nou1.setGames_number(3);

            nou1.all_characters = new ArrayList<>();
            Character ch1 = Factory.createChar(CharType.Warrior, "Mundo", 1,1,1,1);
            nou1.addCharacter(ch1);

            System.out.println(nou1);

        //alt cont

        Account nou2 = new Account();

        Account.Information.InfoBuilder build2 = new Account.Information.InfoBuilder();

        Credentials c2 = new Credentials();
        c2.setEmail("gmail");
        c2.setPassword("321");
        build2.credentials(c2);

        build2.favorite_games = new TreeSet();
        build2.favorite_games.add("Z-Day");
        build2.favorite_games.add("WorldOfMarcel");
        build2.favorite_games.add("CS");
        build2.favorite_games.add("Avatar");

        build2.country("England");
        build2.name("Account2");

        Account.Information info2 = null;
        try {
            info2 = new Account.Information(build2);
        } catch (InformationIncompleteException e) {
            e.printStackTrace();
        }
        nou2.setAccount(info2);
        nou2.setGames_number(4);

        System.out.println(nou2);

        //alt cont
        Account nou3 = new Account();

        Account.Information.InfoBuilder build3 = new Account.Information.InfoBuilder();

        build3.country("UK");
        build3.name("Account3");

        Account.Information info3 = null;
        try {
            info3 = new Account.Information(build3);
        } catch (InformationIncompleteException e) {
            e.printStackTrace();
        }
        nou3.setAccount(info3);

        System.out.println(nou3);
    }
}

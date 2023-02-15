import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonInput {
    public ArrayList<Account> createAccounts() {
        String first = "D:\\Facultate\\Programare orientata pe obiecte\\Proiect\\src\\accounts.json";
        try {
            String content = new String((Files.readAllBytes(Paths.get(first))));
            JSONObject obj = new JSONObject(content);
            JSONArray accountsArray = (JSONArray) obj.get("accounts");

            ArrayList<Account> accounts = new ArrayList<>();
            for (int i=0; i < accountsArray.length(); i++) {
                JSONObject account_i = (JSONObject) accountsArray.get(i);

                Account new_account = new Account();
                Account.Information.InfoBuilder build = new Account.Information.InfoBuilder();

                //name, country, games_number
                String name = (String) account_i.get("name");
                String country = (String) account_i.get("country");
                int games_number = (int) account_i.get("maps_completed");
                build.name(name);
                build.country(country);
                new_account.setGames_number(games_number);

                //credentials
                Credentials c = new Credentials();
                try {
                    JSONObject credentials = (JSONObject) account_i.get("credentials");
                    String email = (String) credentials.get("email");
                    String password = (String) credentials.get("password");
                    c.setEmail(email);
                    c.setPassword(password);
                    build.credentials(c);
                } catch (JSONException e) {
                    System.out.println("! This account doesn't have all credentials !");
                }

                //favorite games
                SortedSet favorite_games = new TreeSet();
                try {
                    JSONArray games = (JSONArray) account_i.get("favorite_games");
                    for (int j=0; j<games.length(); j++)
                        favorite_games.add(games.get(j));
                    build.favorite_games(favorite_games);
                } catch (JSONException e) {
                    System.out.println("! This account doesn't have favorite games !");
                }

                //characters
                new_account.all_characters = new ArrayList<>();
                try {
                    JSONArray char_list = (JSONArray) account_i.get("characters");
                    for (int j=0; j<char_list.length(); j++) {
                        JSONObject char_i = (JSONObject) char_list.get(j);
                        String cname = (String) char_i.get("name");
                        String profession = (String) char_i.get("profession");
                        String level = (String) char_i.get("level");
                        int lvl = Integer.parseInt(level);
                        Integer experience = (Integer) char_i.get("experience");

                        Character new_char = null;
                        if(profession.equals("Warrior"))
                            new_char = Factory.createChar(CharType.Warrior, cname, 0, 0, experience, lvl);
                        if(profession.equals("Rogue"))
                            new_char = Factory.createChar(CharType.Rogue, cname, 0, 0,experience,lvl);
                        if(profession.equals("Mage"))
                            new_char = Factory.createChar(CharType.Mage, cname, 0, 0,experience,lvl);
                        new_account.addCharacter(new_char);
                    }
                } catch (JSONException e) {
                    System.out.println("! This account doesn't have characters !");
                }

                Account.Information info = null;
                try {
                    info = new Account.Information(build);
                } catch (InformationIncompleteException e) {
                    e.printStackTrace();
                }
                new_account.setAccount(info);
                accounts.add(new_account);
            }
            return accounts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<CellEntity, ArrayList<String>> createStory() {
        String first = "D:\\Facultate\\Programare orientata pe obiecte\\Proiect\\src\\stories.json";
        try {
            String content = new String((Files.readAllBytes(Paths.get(first))));
            JSONObject obj = new JSONObject(content);
            JSONArray storyArray = (JSONArray) obj.get("stories");
            ArrayList empty = new ArrayList<>();
            ArrayList enemy = new ArrayList<>();
            ArrayList shop = new ArrayList<>();
            ArrayList finish = new ArrayList<>();

            for (int i=0; i < storyArray.length(); i++) {
                JSONObject dates = (JSONObject) storyArray.get(i);
                String type = (String) dates.get("type");
                String value = (String) dates.get("value");
                switch (type) {
                    case "EMPTY" -> empty.add(value);
                    case "ENEMY" -> enemy.add(value);
                    case "SHOP" -> shop.add(value);
                    default -> finish.add(value);
                }
            }

            Map story = new HashMap();
            story.put(CellEntity.EMPTY, empty);
            story.put(CellEntity.ENEMY, enemy);
            story.put(CellEntity.SHOP, shop);
            story.put(CellEntity.FINISH, finish);
            return story;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

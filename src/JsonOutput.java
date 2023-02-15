import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;

public class JsonOutput {
    public void createOutput(ArrayList<Account> list) {
        ArrayList<JSONObject> accounts = new ArrayList<>();
        JSONObject output = new JSONObject();
        for (Account player : list) {
            JSONObject account_i = new JSONObject();

            account_i.put("name", player.account.name);
            account_i.put("country", player.account.country);
            account_i.put("maps_completed", player.games_number);

            JSONObject credentials = new JSONObject();
            credentials.put("email", player.account.credential.getEmail());
            credentials.put("password", player.account.credential.getPassword());
            account_i.put("credentials", credentials);

            ArrayList<JSONObject> characters = new ArrayList<>();
            for (int j = 0; j < player.all_characters.size(); j++) {
                Character character = player.all_characters.get(j);
                JSONObject char_j = new JSONObject();
                char_j.put("name", character.name);

                if (character instanceof Warrior)
                    char_j.put("profession", "Warrior");
                else if (character instanceof Rogue)
                    char_j.put("profession", "Rogue");
                else
                    char_j.put("profession", "Mage");

                char_j.put("level", Integer.toString(character.level));
                char_j.put("experience", character.current_experience);

                characters.add(char_j);
            }

            account_i.put("characters", characters);
            account_i.put("favorite_games", player.account.favorite_games);
            accounts.add(account_i);
        }
        output.put("accounts", accounts);
        try {
            FileWriter file = new FileWriter("D:\\Facultate\\Programare orientata pe obiecte\\Proiect\\src\\accounts.json");
            file.write(output.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

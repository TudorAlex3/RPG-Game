import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonInputPictures {

    public ArrayList getPictures(String character) {
        String first = "D:\\Facultate\\Programare orientata pe obiecte\\Proiect\\src\\pictures.json";
        try {
            String content = new String((Files.readAllBytes(Paths.get(first))));
            JSONObject obj = new JSONObject(content);
            JSONArray Warrior_Array = (JSONArray) obj.get("Warrior");
            JSONArray Rogue_Array = (JSONArray) obj.get("Rogue");
            JSONArray Mage_Array = (JSONArray) obj.get("Mage");
            ArrayList warrior = new ArrayList<>();
            ArrayList rogue = new ArrayList<>();
            ArrayList mage = new ArrayList<>();

            for (int i = 0; i < Warrior_Array.length(); i++) {
                String name = (String) Warrior_Array.get(i);
                warrior.add(name);
            }

            for (int i = 0; i < Rogue_Array.length(); i++) {
                String name = (String) Rogue_Array.get(i);
                rogue.add(name);
            }

            for (int i = 0; i < Mage_Array.length(); i++) {
                String name = (String) Mage_Array.get(i);
                mage.add(name);
            }

            if (character.equals("Warrior"))
                return warrior;
            if (character.equals("Rogue"))
                return rogue;
            if (character.equals("Mage"))
                return mage;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

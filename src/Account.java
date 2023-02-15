import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Account {
    Information account;
    ArrayList<Character> all_characters;
    int games_number;

    public static class Information {
        Credentials credential;
        SortedSet favorite_games;
        String name;
        String country;

        public static class InfoBuilder {
            Credentials credential;
            SortedSet favorite_games;
            String name;
            String country;

            public InfoBuilder() {
                this.credential = null;
                this.favorite_games = null;
                this.name = null;
                this.country = null;
            }

            public InfoBuilder credentials(Credentials credential) {
                this.credential = credential;
                return this;
            }

            public InfoBuilder favorite_games(SortedSet favorite_games) {
                this.favorite_games = new TreeSet(favorite_games);
                return this;
            }

            public InfoBuilder name(String name) {
                this.name = name;
                return this;
            }

            public InfoBuilder country(String country) {
                this.country = country;
                return this;
            }
        }

        public Information(InfoBuilder build) throws InformationIncompleteException {
            credential = build.credential;
            if (credential == null)
                throw new InformationIncompleteException();

            name = build.name;
            if (name == null)
                throw new InformationIncompleteException();

            favorite_games = build.favorite_games;
            country = build.country;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Information{");
            sb.append("credential=").append(credential);
            sb.append(", favorite_games=").append(favorite_games);
            sb.append(", name='").append(name).append('\'');
            sb.append(", country='").append(country).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public void setAccount(Information account) {
        this.account = account;
    }

    public void setGames_number(int games_number) {
        this.games_number = games_number;
    }

    public void addCharacter(Character character) {
        if(!all_characters.contains(character))
            all_characters.add(character);
        else
            System.out.println("This character has already been introduced.");
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("account=").append(account);
        sb.append(", all_characters=").append(all_characters);
        sb.append(", games_number=").append(games_number);
        sb.append('}');
        return sb.toString();
    }
}

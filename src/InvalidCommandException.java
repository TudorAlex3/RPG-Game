public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("! This command does not exist !");
    }
}

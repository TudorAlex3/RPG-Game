public class RandomInteger {
    public int RandomNumber(int min, int max) {
        return (int) (Math.random() * (max-min)) + min;
    }
}

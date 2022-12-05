
public class Main {

    public static void main(String[] args) {

        Hive hive = new Hive(10, 2, 3, 5, 2);
        for (int i = 0; i < 100; i++){
            hive.playHive();
        }
    }
}
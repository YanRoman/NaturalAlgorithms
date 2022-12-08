import java.util.Random;

public class Test
{
    public static void main(String[] args) {
        Random random = new Random();

        double max = 40.108;
        double min = 20.108;

        System.out.println(random.nextDouble(max - min + 1) + min);

    }
}

public class Test {
    public static void main(String[] args) {
        double lowerBoundPoint = -100;
        double higherBoundPoint = 100;

        for (int i = 0; i < 100; i++){
            System.out.println(Math.random() * (higherBoundPoint - lowerBoundPoint + 1) + lowerBoundPoint);
        }

    }
}

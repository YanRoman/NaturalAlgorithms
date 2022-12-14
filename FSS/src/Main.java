public class Main {
    public static void main(String[] args) {

        int populationSize = 10;
        double lowerBoundPoint = -100;
        double higherBoundPoint = 100;
        double stepStart = 0.5;
        double stepFinal = 0.1;
        double maxWeight = 100;

        Aquarium aquarium = new Aquarium(maxWeight, stepStart, stepFinal, populationSize,
                lowerBoundPoint, higherBoundPoint);

        aquarium.play(200);
    }
}
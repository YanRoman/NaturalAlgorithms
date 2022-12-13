import java.util.List;

public class Main {
    public static void main(String[] args) {
        int populationSize = 5;
        double lowerBoundPoint = -100;
        double higherBoundPoint = 100;
        double maxWeight = 50;

        double individualStepStart = 10;
        double individualStepFinish = 0.1;
        Aquarium aquarium = new Aquarium(populationSize, lowerBoundPoint, higherBoundPoint, maxWeight,
                                         individualStepStart, individualStepFinish);
        aquarium.play(50);

        List<Fish> population = aquarium.getPopulation();

        System.out.println("ITOG<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        for (Fish fish : population){
            System.out.println(fish);
        }

        for (Fish fish : population){
            System.out.println(fish.func(fish.getPosition()[0]));
        }
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Aquarium {
    private final List<Fish> population = new ArrayList<>();
    private final double individualStepStart;
    private final double individualStepFinish;
    private final double lowerBoundPoint, higherBoundPoint;

    public Aquarium(int populationSize, double lowerBoundPoint, double higherBoundPoint, double maxWeight,
                    double individualStepStart, double individualStepFinish){
        this.individualStepStart = individualStepStart;
        this.individualStepFinish = individualStepFinish;
        this.lowerBoundPoint = lowerBoundPoint;
        this.higherBoundPoint = higherBoundPoint;

        for (int i = 0; i < populationSize; i++){
            population.add(new Fish(maxWeight, lowerBoundPoint, higherBoundPoint, individualStepStart));
        }
        System.out.println("INIT FISH<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        for (Fish fish : population){
            System.out.println(fish);
        }
    }

    public void play(){
        for (int i = 0; i < 100; i++) {

            individualStageSwim();
            feeding();
            CollectiveInstinctiveSwim();
            CollectiveVolitiveSwim();

            for (Fish fish : population){
                fish.setIndividualStep(fish.getIndividualStep() - (individualStepStart - individualStepFinish / 100));
                fish.setPosition(fish.getSwimStatePos()[3]);
            }

            System.out.println("1 ITER<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            for (Fish fish : population){
                System.out.println(fish);
            }
        }



    }

    public void individualStageSwim(){
        for (Fish fish : population) {
            fish.move(lowerBoundPoint, higherBoundPoint);
        }
    }

    public void feeding(){
        double maxDifFunc = 0;
        for (Fish fish : population){
            if (fish.getDifFunc() > maxDifFunc){
                maxDifFunc = fish.getDifFunc();
            }
        }
        for (Fish fish : population) {
            fish.setWeight(fish.getWeight() + fish.getDifFunc()/maxDifFunc);
        }
    }

    public void CollectiveInstinctiveSwim(){
        double sumDufFunc = 0;
        for (Fish fish : population){
            sumDufFunc += fish.getDifFunc();
        }

        double x = 0;
        for (Fish fish : population){
            Vector<Double> swimStatePos0 = fish.getSwimStatePos()[0];
            Vector<Double> swimStatePos1 = fish.getSwimStatePos()[1];

            x += (swimStatePos1.get(0) - swimStatePos0.get(0)) * fish.getDifFunc();
        }
        double y = 0;
        for (Fish fish : population){
            Vector<Double> swimStatePos0 = fish.getSwimStatePos()[0];
            Vector<Double> swimStatePos1 = fish.getSwimStatePos()[1];

            y += (swimStatePos1.get(1) - swimStatePos0.get(1)) * fish.getDifFunc();
        }

        double m1 = x/sumDufFunc;
        double m2 = y/sumDufFunc;

        for (Fish fish : population){
            Vector<Double>[] swimStatePos = fish.getSwimStatePos();

            Vector<Double> newPos = new Vector<>();
            newPos.add(swimStatePos[1].get(0) + m1);
            newPos.add(swimStatePos[1].get(1) + m2);

            swimStatePos[2] = newPos;
            fish.setSwimStatePos(swimStatePos);
        }
    }
    private void CollectiveVolitiveSwim(){
        double sumWeight = 0;
        for (Fish fish : population){
            sumWeight += fish.getWeight();
        }
        double x = 0;
        for (Fish fish : population){
            Vector<Double> swimStatePos = fish.getSwimStatePos()[2];
            x += swimStatePos.get(0) * fish.getWeight();
        }
        double y = 0;
        for (Fish fish : population){
            Vector<Double> swimStatePos = fish.getSwimStatePos()[2];
            x += swimStatePos.get(1) * fish.getWeight();
        }

        double barycentreX = x/sumWeight;
        double barycentreY = y/sumWeight;

        for (Fish fish : population){
            Vector<Double>[] swimStatePos = fish.getSwimStatePos();
            double collStep = fish.getIndividualStep() * 2;

            double euclidDist = Math.sqrt(Math.pow(Math.abs(swimStatePos[2].get(0) - barycentreX), 2)
                    + Math.pow(Math.abs(swimStatePos[2].get(1) - barycentreY), 2));

            Vector<Double> newPos = new Vector<>();
            newPos.add(swimStatePos[2].get(0) + collStep * Math.random()
                    * (swimStatePos[2].get(0) - barycentreX) / euclidDist);

            newPos.add(swimStatePos[2].get(1) + collStep * Math.random()
                    * (swimStatePos[2].get(1) - barycentreY) / euclidDist);

            swimStatePos[3] = newPos;
            fish.setSwimStatePos(swimStatePos);
        }
    }

    public List<Fish> getPopulation() {
        return population;
    }
}

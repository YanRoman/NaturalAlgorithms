

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    private final List<Fish> population = new ArrayList<>();
    private final double individualStepStart;
    private final double individualStepFinish;
    private double globalSumWeight;

    public Aquarium(int populationSize, double lowerBoundPoint, double higherBoundPoint, double maxWeight,
                    double individualStepStart, double individualStepFinish){
        this.individualStepStart = individualStepStart;
        this.individualStepFinish = individualStepFinish;

        for (int i = 0; i < populationSize; i++){
            population.add(new Fish(maxWeight, lowerBoundPoint, higherBoundPoint, individualStepStart));
        }
        globalSumWeight = getSumWeight();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>INIT");
        for (Fish fish : population){
            System.out.println(fish);
        }
    }

    public void play(double counter){
        for (int i = 0; i < counter; i++) {

            individualStageSwim();
            feeding();
            CollectiveInstinctiveSwim();
            CollectiveVolitiveSwim();

            System.out.println("1 ITER<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            for (Fish fish : population){
                System.out.println(fish);
            }
            for (Fish fish : population){
                fish.setIndividualStep(fish.getIndividualStep() - (individualStepStart / counter));

                Point [] position = fish.getPosition();
                position[0] = position[3];
                fish.setDifFunc(1);
            }
        }
    }
    public void individualStageSwim(){
        for (Fish fish : population) {
            fish.move();
        }
    }
    public void feeding(){
        double maxDifFunc = getMaxDifFunc();
        for (Fish fish : population) {
            fish.setWeight(fish.getWeight() + fish.getDifFunc()/maxDifFunc);
        }
    }
    public void CollectiveInstinctiveSwim(){
        double sumDifFunc = getSumDifFunc();
        if (sumDifFunc == 0) return;
        Point m = new Point();
        for (Fish fish : population){
            m = m.sumPoints(fish.getPosition()[1].differencePoints(fish.getPosition()[0]).
                    multiplyDoublePoint(fish.getDifFunc()));
        }
        m = m.decideDoublePoint(sumDifFunc);

        for (Fish fish : population){
            Point [] position = fish.getPosition();
            position[2] = position[1].sumPoints(m);
            fish.setPosition(position);
        }
    }
    private void CollectiveVolitiveSwim(){
        double sumWeight = getSumWeight();

        Point barycentre = new Point();
        for (Fish fish : population){
            barycentre = barycentre.sumPoints(fish.getPosition()[2].multiplyDoublePoint(fish.getWeight()));
            //System.out.println("POSITION = " + fish.getPosition()[2]);
           // System.out.println("WEIGHT = " + fish.getWeight());
        }
        barycentre = barycentre.decideDoublePoint(sumWeight);
        System.out.println(barycentre);

        for (Fish fish : population){
            Point [] position = fish.getPosition();
            double collStep = fish.getIndividualStep() * 2;

           // System.out.println("POS[2] = " + position[2]);


           // System.out.println();
           // System.out.println("sumWeight: " + sumWeight);
           // System.out.println("gloablSumWeight: " + globalSumWeight);

            Point point = position[2].differencePoints(barycentre);
            if (sumWeight > globalSumWeight){

             //   System.out.println("POINT: " + point);
                point = point.decideDoublePoint(position[2].getDistance(barycentre));
               // System.out.println("POINT: " + point);
                point = point.multiplyDoublePoint(Math.random()).multiplyDoublePoint(collStep);
               // System.out.println("POINT: " + point);
                position[3] = position[2].differencePoints(point);
               // System.out.println("POS[3] = " + position[3]);
            } else {

                point = point.decideDoublePoint(position[2].getDistance(barycentre));
                point = point.multiplyDoublePoint(Math.random()).multiplyDoublePoint(collStep);
                position[3] = position[2].sumPoints(point);

            }

            globalSumWeight = sumWeight;
            fish.setPosition(position);
        }
    }
    public double getSumDifFunc(){
        double sumDifFunc = 0;
        for (Fish fish : population){
            sumDifFunc += fish.getDifFunc();
        }
        return sumDifFunc;
    }
    public double getMaxDifFunc(){
        double maxDifFunc = 0;
        for (Fish fish : population){
            if (fish.getDifFunc() > maxDifFunc){
                maxDifFunc = fish.getDifFunc();
            }
        }
        return maxDifFunc;
    }
    public double getSumWeight(){
        double sumWeight = 0;
        for (Fish fish : population){
            sumWeight += fish.getWeight();
        }
        return sumWeight;
    }
    public List<Fish> getPopulation() {
        return population;
    }

}

/**
 * 953,975
 * 1071,975
 * 939,375
 * 1573,5
 * 494,15
 * 5032,975
 *
 * 1501,6
 * 2356,5
 * -158,5
 * 1674,175
 * 2411,575
 * 7785,35
 */
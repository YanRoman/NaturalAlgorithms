import java.util.Vector;

public class Aquarium {

    private final Vector<Fish> population = new Vector<>();
    private final double lowerBoundPoint;
    private final double higherBoundPoint;
    private double oldWeight;
    private final double stepStart;
    private final double stepFinal;


    public Aquarium(double maxWeight, double stepStart, double stepFinal, int populationSize,
                    double lowerBoundPoint, double higherBoundPoint){

        this.stepStart = stepStart;
        this.stepFinal = stepFinal;
        this.lowerBoundPoint = lowerBoundPoint;
        this.higherBoundPoint = higherBoundPoint;

        for (int i = 0; i < populationSize; i++){
            population.add(new Fish(lowerBoundPoint, higherBoundPoint, maxWeight, stepStart));
        }

        System.out.println("INIT------------------------------------------------------------");
        for (Fish fish : population){
            System.out.println(fish);
        }
    }

    public void play(int iterationCount){
        for (int i = 0; i < iterationCount; i++){

            for (Fish fish : population){
                oldWeight += fish.getWeight();
            }

            individualSwim();

            System.out.println("individualSwim <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            for (Fish fish : population){
                System.out.println(fish);
            }


            double maxDifFunc = f(population.get(0).getPosition()[0]) - f(population.get(0).getPosition()[1]);
            for (Fish fish : population){
                if (f(fish.getPosition()[0]) - f(fish.getPosition()[1]) > maxDifFunc){
                    maxDifFunc = f(fish.getPosition()[0]) - f(fish.getPosition()[1]);
                }
            }

            if (maxDifFunc == 0){
                continue;
            }

            feeding(maxDifFunc);

            System.out.println("feeding <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            for (Fish fish : population){
                System.out.println(fish);
            }

            collectiveInstinctiveSwim();

            System.out.println("collectiveInstinctiveSwim <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            for (Fish fish : population){
                System.out.println(fish);
            }

            CollectiveVolitiveSwim();

            System.out.println("CollectiveVolitiveSwim <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            for (Fish fish : population){
                System.out.println(fish);
            }


            for (Fish fish : population){
                fish.setStep(fish.getStep() - (stepStart - stepFinal)/iterationCount);
                double[][] position = fish.getPosition();
                position[0] = position[3];
                fish.setPosition(position);
            }
            System.out.println("===========================================================================================");
        }
    }


    public void individualSwim(){
        for (Fish fish : population){
            double rand = (Math.random() * 2 - 1) * fish.getStep();
            double[][] position = fish.getPosition();
            position[1][0] = position[0][0] + rand;
            position[1][1] = position[0][1] + rand;
            if (f(position[1]) >= f(position[0]) || !isInRegion(position[1])){
                position[1] = position[0];
            }
         //   fish.setPosition(position);
        }
    }

    public boolean isInRegion(double[] position){
        return position[0] >= lowerBoundPoint && position[0] <= higherBoundPoint && position[1]
                >= lowerBoundPoint && position[1] <= higherBoundPoint;
    }

    public void feeding(double maxDifFunc){
                for (Fish fish : population){
                    fish.setWeight(fish.getWeight() + (f(fish.getPosition()[0]) - f(fish.getPosition()[1]))/maxDifFunc);
                }
    }

    public void collectiveInstinctiveSwim(){

        double[] numerator = new double[2];
        double sumDifF = 0;
        for (Fish fish : population){
            double[][] position = fish.getPosition();
            double difFunc = f(fish.getPosition()[0]) - f(fish.getPosition()[1]);

            double x = (position[1][0] - position[0][0]) * difFunc;
            double y = (position[1][1] - position[0][1]) * difFunc;

            numerator[0] += x;
            numerator[1] += y;
            sumDifF += difFunc;
        }

        double[] m = new double[2];
            m[0] = numerator[0] / sumDifF;
            m[1] = numerator[1] / sumDifF;

        for (Fish fish : population){
            double[][] position = fish.getPosition();
            position[2][0] = position[1][0] + m[0];
            position[2][1] = position[1][1] + m[1];
        }
    }

    private void CollectiveVolitiveSwim(){

        double[] numerator = new double[2];
        double sumWeight = 0;
        for (Fish fish : population){
            double[][] position = fish.getPosition();
            double x = position[2][0]  * fish.getWeight();
            double y = position[2][1]  * fish.getWeight();

            numerator[0] += x;
            numerator[1] += y;
            sumWeight += fish.getWeight();
        }

        System.out.println(sumWeight);
        double[] barycentre = new double[2];
            barycentre[0] = numerator[0] / sumWeight;
            barycentre[1] = numerator[1] / sumWeight;

        double newWeight = 0;
        for (Fish fish : population){
            newWeight += fish.getWeight();
        }

        for (Fish fish : population){
            double[][] position = fish.getPosition();
            double colStep = fish.getStep() * 2;
            double dist = Math.sqrt(Math.pow(Math.abs(position[2][0] - barycentre[0]), 2)
                    + Math.pow(Math.abs(position[2][1] - barycentre[1]), 2));

            double rand = Math.random();

                if (newWeight > oldWeight){
                    position[3][0] = position[2][0] + colStep * rand * position[2][0] - barycentre[0] / dist;
                    position[3][1] = position[2][1] + colStep * rand * position[2][1] - barycentre[1] / dist;
                } else {
                    position[3][0] = position[2][0] - colStep * rand * position[2][0] - barycentre[0] / dist;
                    position[3][1] = position[2][1] - colStep * rand * position[2][1] - barycentre[1] / dist;
                }
        }
    }




    public double f(double[] point){
        return (Math.pow(point[0], 2) + Math.pow(point[1], 2));
    }
}

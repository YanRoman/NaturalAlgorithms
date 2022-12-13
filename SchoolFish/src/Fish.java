import java.util.Arrays;
import java.util.Vector;

public class Fish {
    private Vector<Double> position;
    private Vector<Double>[] swimStatePos = new Vector[4];
    private double weight;
    private double individualStep;
    private double difFunc;

    public Fish(double maxWeight, double lowerBoundPoint, double higherBoundPoint, double individualStepStart){
        individualStep = individualStepStart;
        weight = maxWeight/2;
        position = new Vector<>();

        for (int i = 0; i < 2; i++){
            position.add(lowerBoundPoint + Math.random() * (higherBoundPoint - lowerBoundPoint + 1));
        }
        swimStatePos[0] = position;
    }

    public void move(double lowerBoundPoint, double higherBoundPoint){
        double rand = (Math.random() * 2 - 1) * individualStep;
        if (func(position.get(0) + rand, position.get(1) + rand) < func(position.get(0), position.get(1)) &&
        position.get(0) + rand >= lowerBoundPoint && position.get(0) + rand <= higherBoundPoint &&
                position.get(1) + rand >= lowerBoundPoint && position.get(1) + rand <= higherBoundPoint){


            difFunc = Math.abs(func(position.get(0), position.get(1)) -
                    func(position.get(0) + rand, position.get(1) + rand));
            Vector<Double> newPos = new Vector<>();
            newPos.add(position.get(0) + rand);
            newPos.add(position.get(1) + rand);
            swimStatePos[1] =  newPos;
        } else {
            swimStatePos[1] = position;
        }
    }

    public double func(double x, double y){
        return (Math.pow(x, 2) + Math.pow(y, 2));
    }

    public Vector[] getSwimStatePos() {
        return swimStatePos;
    }

    public void setSwimStatePos(Vector[] swimStatePos) {
        this.swimStatePos = swimStatePos;
    }

    public Vector<Double> getPosition() {
        return position;
    }

    public void setPosition(Vector<Double> position) {
        this.position = position;
    }

    public double getWeight() {
        return weight;
    }

    public double getIndividualStep() {
        return individualStep;
    }

    public double getDifFunc() {
        return difFunc;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setIndividualStep(double individualStep) {
        this.individualStep = individualStep;
    }

    public void setDifFunc(double difFunc) {
        this.difFunc = difFunc;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "position=" + position +
                ", swimStatePos=" + Arrays.toString(swimStatePos) +
                ", weight=" + weight +
                ", individualStep=" + individualStep +
                ", difFunc=" + difFunc +
                '}';
    }
}

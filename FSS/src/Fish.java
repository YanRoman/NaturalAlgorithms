import java.util.Arrays;

public class Fish {
    private double step;
    private double weight;
    private double[][] position = new double[4][2];



    public Fish(double lowerBoundPoint, double higherBoundPoint, double maxWeight, double stepStart){
        step = stepStart;
        weight = maxWeight/2;
        position[0][0] = Math.random() * (higherBoundPoint - lowerBoundPoint + 1) + lowerBoundPoint;
        position[0][1] = Math.random() * (higherBoundPoint - lowerBoundPoint + 1) + lowerBoundPoint;
    }






    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double[][] getPosition() {
        return position;
    }

    public void setPosition(double[][] position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "position=" + Arrays.deepToString(position) +
                ", weight=" + weight +
                ", step=" + step +
                '}';
    }
}

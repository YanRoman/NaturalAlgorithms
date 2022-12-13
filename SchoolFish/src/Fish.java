import java.util.Arrays;

public class Fish {
    private Point [] position = new Point[4];
    private double weight;
    private double individualStep;
    private double difFunc;
    private double lowerBoundPoint, higherBoundPoint;

    public Fish(double maxWeight, double lowerBoundPoint, double higherBoundPoint, double individualStepStart){
        this.lowerBoundPoint = lowerBoundPoint;
        this.higherBoundPoint = higherBoundPoint;
        individualStep = individualStepStart;
        weight = maxWeight/2;
        position[0] = new Point(lowerBoundPoint, higherBoundPoint);
    }

    public void move(){
        double rand = (Math.random() * 2 - 1) * individualStep;

        if (func(position[0].sumDoublePoint(rand)) < func(position[0]) &&
                position[0].sumDoublePoint(rand).inRange(lowerBoundPoint, higherBoundPoint)){

            difFunc = Math.abs(func(position[0]) - func(position[0].sumDoublePoint(rand)));
            position[1] = position[0].sumDoublePoint(rand);
        } else {
            position[1] = position[0];
        }
    }

    public double func(Point point){
        return (Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2));
    }

    public Point[] getPosition() {
        return position;
    }

    public void setPosition(Point[] position) {
        this.position = position;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getIndividualStep() {
        return individualStep;
    }

    public void setIndividualStep(double individualStep) {
        this.individualStep = individualStep;
    }

    public double getDifFunc() {
        return difFunc;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "position=" + Arrays.toString(position) +
                ", weight=" + weight +
                ", individualStep=" + individualStep +
                ", difFunc=" + difFunc +
                '}';
    }
}

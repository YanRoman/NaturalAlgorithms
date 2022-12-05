import java.util.Vector;

class Particle{
    //Мерность графика
    private final int DIMENSION;
    //Position
    private final Vector<Double> position = new Vector<>();
    //Minimum Position
    private Vector<Double> localMinPosition = new Vector<>();
    //Vector
    private final Vector<Double> vector = new Vector<>();
    //local min func
    private double localMinFunc;
    public Particle(int numberOfCoordinates){
        DIMENSION = numberOfCoordinates;
        for (int i = 0; i < numberOfCoordinates; i++){
            position.add(Math.random() * 10 - 5);
            vector.add(Math.random() * 1);
            localMinPosition.add(position.get(i));
        }
        localMinFunc = func(position);
        System.out.println("INIT POSITION: [" + position.get(0) + "; " + position.get(1) + "]");
        System.out.println("INIT VECTOR: [" + vector.get(0) + "; " + vector.get(1) + "]");
        System.out.println("INIT LOCAL MIN: " + localMinFunc);
        System.out.println("=================================================================");

    }
    public void changeVector(Vector<Double> globalMinPosition){
        double c = 1.78;
        double r1 = Math.random() * 1;
        double r2 = Math.random() * 1;

        for(int i = 0; i < DIMENSION; i++){
            this.vector.set(i,(vector.get(i)) + (c * r1 * localMinPosition.get(i)) -
                    (c * r1 * position.get(i)) + (c * r2 * globalMinPosition.get(i))-
                    (c * r2 * position.get(i)));
        }
        System.out.println("===============CHANGE VECTOR===============");
        System.out.println("NEW VECTOR: [" + vector.get(0) + "; " + vector.get(1) + "]");
    }
    public void move(){
        for (int i = 0; i < DIMENSION; i++){
            position.set(i, position.get(i) + vector.get(i));

            if (func(position) < localMinFunc){
                localMinFunc = func(position);
                localMinPosition = position;
            }
        }
        System.out.println("===============MOVING PARTICLE===============");

        System.out.println("NEW POSITION: [" + position.get(0) + "; " + position.get(1) + "]");
    }
    public double func(Vector<Double> position){
        double sum = 0;
        for (int i = 0; i < DIMENSION; i++){
            sum += Math.pow(position.get(i), 2);
        }
        return sum;
    }
    public Vector<Double> getLocalMinPosition() {
        return localMinPosition;
    }

    public double getLocalMinFunc() {
        return localMinFunc;
    }
}


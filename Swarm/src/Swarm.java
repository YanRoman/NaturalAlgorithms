import java.util.ArrayList;
import java.util.Vector;

class Swarm{
    //global min position
    private Vector<Double> globalMinPosition = new Vector<>();
    //global min func
    private double globalMinFunc;
    //swarm
    private final ArrayList<Particle> swarmList = new ArrayList<>();
    //Constructor
    public Swarm(int numberOfParticles, int numberOfCoordinates){
        for (int i = 0; i < numberOfParticles; i++){
            swarmList.add(new Particle(numberOfCoordinates));
        }

        globalMinPosition=swarmList.get(0).getLocalMinPosition();
        globalMinFunc = swarmList.get(0).getLocalMinFunc();
    }
    public void playSwarm(){
        //Глобальный минимум
        for (Particle particle : swarmList) {
            if (particle.getLocalMinFunc() < globalMinFunc) {
                globalMinFunc = particle.getLocalMinFunc();

                for (int j = 0; j < 2; j++){
                    globalMinPosition.set(j, particle.getLocalMinPosition().get(j));
                }
            }
        }
        System.out.println("NEW GLOBAL MINIMUM POSITION: [" + globalMinPosition.get(0) + "; " + globalMinPosition.get(1) + "]");
        System.out.println("NEW GLOBAL MINIMUM FUNC: " + globalMinFunc);
        for (Particle particle : swarmList) {

            particle.changeVector(globalMinPosition);
            particle.move();

            System.out.println("===============================================");
        }

        System.out.println("Global min pos: " + globalMinPosition);
    }
    public ArrayList<Particle> getSwarmList(){
        return swarmList;
    }
    public double getGlobalMinFunc(){
        return globalMinFunc;
    }
    public Vector<Double> getGlobalMinPosition(){
        return globalMinPosition;
    }
}

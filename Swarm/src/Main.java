public class Main {

    public static void main(String[] args) {
        Swarm swarm = new Swarm(100, 2);

        for(int i = 0; i < 100; i++){
            System.out.println(i + "ITERATION<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            swarm.playSwarm();
        }

        System.out.println("GLOBAL MINIMUM FUNC: " + swarm.getGlobalMinFunc());
        System.out.println("GLOBAL MINIMUM POSITION: " + swarm.getGlobalMinPosition());
    }
}

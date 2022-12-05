import lombok.Data;

import java.util.*;

@Data
public class Hive {
    private double maxFunc;
    private int numberScouts;
    private int numberBestDots;
    private int numberOtherDots;
    private int numberBestBees;
    private int numberOtherBees;
    private List<Bee> scouts;
    public List<Bee> bestBees;
    public List<Bee> otherBees;
    private List<Dot> bestDots;
    private List<Dot> otherDots;

    public Hive(int numberScouts, int numberBestDots, int numberOtherDots, int numberBestBees, int numberOtherBees){
        this.numberScouts = numberScouts;
        this.numberBestDots = numberBestDots;
        this.numberOtherDots = numberOtherDots;
        this.numberBestBees = numberBestBees;
        this.numberOtherBees = numberOtherBees;

        scouts = new ArrayList<>();
        for (int i = 0; i < numberScouts; i++){
            scouts.add(new Bee());
        }
        Collections.sort(scouts);
        maxFunc = scouts.get(0).getFuncValue();

        System.out.println("----SCOUTS-----");
        for (Bee scout : scouts){
            System.out.println(scout);
        }
    }
    public void playHive(){

        bestDots = new ArrayList<>();
        for (int i = 0; i < numberBestDots; i++){
            bestDots.add(new Dot(scouts.get(i)));
        }

        otherDots = new ArrayList<>();
        for (int i = 0; i < numberOtherDots; i++){
            otherDots.add(new Dot(scouts.get(i + numberBestDots - 1)));
        }

        bestBees = new ArrayList<>();
        for (Dot dot : bestDots){
            for (int i = 0; i < numberBestBees; i++){
                bestBees.add(new Bee(dot));
            }
        }

        otherBees = new ArrayList<>();
        for (Dot dot : otherDots){
            for (int i = 0; i < numberOtherBees; i++){
                otherBees.add(new Bee(dot));
            }
        }

        System.out.println("=====BEST BEES=====");
        for (Bee bee : bestBees){
            System.out.println(bee);
        }

        System.out.println("=====OTHER BEES=====");
        for (Bee bee : otherBees){
            System.out.println(bee);
        }

        scouts = new ArrayList<>();
        scouts.addAll(bestBees);
        scouts.addAll(otherBees);
        Collections.sort(scouts);
        if (scouts.get(0).getFuncValue() > maxFunc){
            maxFunc = scouts.get(0).getFuncValue();
        }
        System.out.println("----SCOUTS-----");
        for (Bee scout : scouts){
            System.out.println(scout);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + maxFunc);
    }
}

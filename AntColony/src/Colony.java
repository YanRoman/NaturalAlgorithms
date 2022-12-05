import java.util.*;

public class Colony {
    //  Значимость феромона
    private static final int ALPHA = 1;
    //  Значимость расстояния
    private static final int BETA = 1;

    public static void main(String[] args) {
        List<Dot> dots = Arrays.asList(
                new Dot(55, 37),
                new Dot(82, 76),
                new Dot(81, 82),
                new Dot(43, 131),
                new Dot(52, 41),
                new Dot(200, 500)
        );
        for (int i = 0; i < 20; i++){
            colony(dots);
        }
    }

    public static Map<Dot, Double> getDesires(List<Dot> dots, List<Dot> passedDots, Dot start){
        Map<Dot, Double> desires = new HashMap<>();
        for (Dot dot : dots){
            if (!passedDots.contains(dot)){
                double desire = Math.pow(dot.getPheromone(), ALPHA) * Math.pow(start.getDistance(dot), BETA);
                desires.put(dot, desire);
            }
        }
        return desires;
    }
    public static double getSumDesires(Map<Dot, Double> desires){
        double sumDesires = 0;
        for (Double value : desires.values()){
            sumDesires += value;
        }
        return sumDesires;
    }
    public static Map<Dot, Double> getProbabilities(Map<Dot, Double> desires){
        Map<Dot, Double> probabilities = new HashMap<>();
        double sumDesires = getSumDesires(desires);

        for (Dot dot : desires.keySet()){
            probabilities.put(dot, desires.get(dot)/sumDesires);
        }
        return probabilities;
    }

    public static void colony(List<Dot> dots){
        Map<List<Dot>, Integer> roads = new HashMap<>();
        for (int ant = 0; ant < 7; ant++){
            Dot start = dots.get(0);
            Dot end = dots.get(dots.size()-1);
            List<Dot> passedDots = new ArrayList<>();
            passedDots.add(start);
            int distance = 0;

            while (start != end){
                //  Ищем желание муравья перейти во все доступные точки и сумму всех желаний
                Map<Dot, Double> desires = getDesires(dots, passedDots, start);

                //  Ищем вероятность перехода во все доступные точки
                Map<Dot, Double> probabilities = getProbabilities(desires);


                Dot newStart = null;
                double tempSum = 0;
                for (Dot dot : probabilities.keySet()){
                    tempSum+=probabilities.get(dot);
                    if (Math.random() * 1 <= tempSum){
                        newStart = dot;
                        break;
                    }
                }

                assert newStart != null;
                distance+=newStart.getDistance(start);
                start = newStart;
                passedDots.add(start);
            }

            roads.put(passedDots, distance);
        }


        for (Dot dot : dots){
            dot.setPheromone(dot.getPheromone() * 0.64);
        }

        //Добавление феромона
        for (List<Dot> passedDots : roads.keySet()){
            for (Dot dot : passedDots){
                dot.addPheromone((double) 4 / roads.get(passedDots));
            }
        }

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (List<Dot> passedDots : roads.keySet()){
            System.out.println(passedDots.toString() + " LENGTH=" + roads.get(passedDots));
        }
    }


}
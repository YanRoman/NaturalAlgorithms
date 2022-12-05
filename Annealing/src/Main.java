import java.util.ArrayList;
public class Main {
    public static double SCALE = Math.pow(10,3);
    public static int getS(ArrayList<City> destinations){
        int S = 0;
        for (int i = 0; i < destinations.size() - 1; i++){
            S += destinations.get(i).getDistance(destinations.get(i+1));
        }

        System.out.println("S= " + S);
        return S;
    }


    public static void simulation(ArrayList<City> destinations, int S, double T,
                                  int minS, double minT, int i)
    {

        if (T < 1){
            System.out.println("min S: " + minS);
            System.out.println("T on min S: " + minT);

            return;
        }

        int tempIndex1 = (int) (Math.random() * destinations.size());
        int tempIndex2 = (int) (Math.random() * destinations.size());
        while (Math.abs(tempIndex2 - tempIndex1) < 2 ||
                tempIndex1 == (destinations.size() - 1) ||
                tempIndex2 == (destinations.size() - 1) ||
                tempIndex1 == 0 ||
                tempIndex2 == 0){
            tempIndex1 = (int) (Math.random() * destinations.size());
            tempIndex2 = (int) (Math.random() * destinations.size());
        }

        ArrayList<City> tempDestinations = new ArrayList<>(destinations);

        tempDestinations.set(tempIndex1, destinations.get(tempIndex2));
        tempDestinations.set(tempIndex2, destinations.get(tempIndex1));
        int tempS = getS(tempDestinations);

        int deltaS = tempS - S;
        if (deltaS < 0){
            destinations = tempDestinations;
        } else{
            double h = 100 * (1 / Math.pow(2.71, (deltaS/T)));
            double hh = Math.random() * 100;
            if (h < hh){
                destinations = tempDestinations;
            }
        }
        T = Math.ceil(T / 1.1 * SCALE) / SCALE;
        System.out.println("T: " + T);
        System.out.println("Iteration: " + i);
        if (tempS < minS){
            minS = tempS;
            minT = T;
        }
        i++;
        simulation(destinations, tempS, T, minS, minT, i);
    }




    public static void ArrayToString(ArrayList<City> destinations){
        for (int i = 0; i < destinations.size(); i++){
            City city = destinations.get(i);

            System.out.println(i + " ----- x = " + city.getX() +
                    " y = " + city.getY());
        }
    }


    public static void main(String[] args) {
        ArrayList<City> destinations = new ArrayList<City>();
        destinations.add(new City(55, 37)); //Moscow
        destinations.add(new City(59, 19)); //Peterburg
        destinations.add(new City(55, 82)); //Novosibirsk
        destinations.add(new City(43, 131));//Vladivostok
        destinations.add(new City(52, 41)); //Tambov
        destinations.add(new City(42, 43)); //Kislovodsk
        destinations.add(destinations.get(0));


        ArrayToString(destinations);


        simulation(destinations, getS(destinations), 100, 1000, 100, 0);

    }
}

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Dot {
    private List<Double> x = new ArrayList<>();
    private List<Double> y = new ArrayList<>();
    public Dot(Bee bee){
        x.add(bee.getX() - 10);
        x.add(bee.getX() + 10);

        y.add(bee.getY() - 10);
        y.add(bee.getY() + 10);
    }
}

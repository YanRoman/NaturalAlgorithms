import lombok.Data;
@Data
public class Bee implements Comparable<Bee> {
    private double x;
    private double y;
    private double funcValue;
    public Bee(){
        x = Math.random() * 200;
        y = Math.random() * 200;
        funcValue = func();
    }

    public Bee(Dot dot){
        x = Math.random() * dot.getX().get(1) - dot.getX().get(0);
        y = Math.random() * dot.getY().get(1) - dot.getY().get(0);
        funcValue = func();
    }

    public double func(){
        return -(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public int compareTo(Bee b) {
        return (int) (b.getFuncValue() - this.getFuncValue());
    }
}

public class Test {
    public static void main(String[] args) {
        Point point = new Point();
        point.setX(-115);
        point.setY(-115);
        System.out.println(func(point));

        Point point1 = new Point();
        point.setX(0);
        point.setY(0);
        System.out.println(func(point));

        Point point2 = new Point();
        point.setX(-5370.83938649875);
        point.setY(-5370.83938649875);
        System.out.println(func2(point2));
    }
    public static double func(Point point){
        return (Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2));
    }
    public static double func2(Point point){
        return -(0.1 * Math.pow(point.getX(), 2) + 0.1 * Math.pow(point.getY(), 2)) + 10;
    }
}

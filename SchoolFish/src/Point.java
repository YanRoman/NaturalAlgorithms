


public class Point {
    private double x;
    private double y;

    public Point(){
        x = 0;
        y = 0;
    }
    public Point(double lowerBoundPoint, double higherBoundPoint){
        x = lowerBoundPoint + Math.random() * higherBoundPoint - lowerBoundPoint + 1;
        y = lowerBoundPoint + Math.random() * higherBoundPoint - lowerBoundPoint + 1;
    }


    public Point sumPoints(Point point){
        double x = this.x + point.x;
        double y = this.y + point.y;
        Point newPoint = new Point();
        newPoint.setX(x);
        newPoint.setY(y);
        return newPoint;
    }
    public Point differencePoints(Point point){
        double x = this.x - point.x;
        double y = this.y - point.y;
        Point newPoint = new Point();
        newPoint.setX(x);
        newPoint.setY(y);
        return newPoint;
    }
    public Point multiplyDoublePoint(double num){
        double tempX = x * num;
        double tempY = y * num;
        Point newPoint = new Point();
        newPoint.setX(tempX);
        newPoint.setY(tempY);
        return newPoint;
    }
    public Point decideDoublePoint(double num){
        double tempX = x / num;
        double tempY = y / num;
        Point newPoint = new Point();
        newPoint.setX(tempX);
        newPoint.setY(tempY);
        return newPoint;
    }
    public Point sumDoublePoint(double num){
        double tempX = x + num;
        double tempY = y + num;
        Point newPoint = new Point();
        newPoint.setX(tempX);
        newPoint.setY(tempY);
        return newPoint;
    }

    public boolean inRange(double lowerBoundPoint, double higherBoundPoint){
        return x <= higherBoundPoint && x>= lowerBoundPoint &&
                y <= higherBoundPoint && y >= lowerBoundPoint;
    }
    public double getDistance(Point point){
        return Math.sqrt(Math.pow(Math.abs(this.x - point.getX()), 2) + Math.pow(Math.abs(this.y - point.getY()), 2));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

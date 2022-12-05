
class City {
    private int x;
    private int y;

    public City(){
        this.x = (int)(Math.random() * 10);
        this.y = (int)(Math.random() * 10);
    }
    public City(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDistance(City city){
        int xDistance = Math.abs((getX() - city.getX()));
        int yDistance = Math.abs((getY() - city.getY()));
        return Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
    }
}


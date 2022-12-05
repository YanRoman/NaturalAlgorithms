class Dot {
    private final int x;
    private final int y;
    private double pheromone;

    public Dot(){
        x = (int) (Math.random() * 10);
        y = (int) (Math.random() * 10);
        pheromone = 1;
    }
    public Dot(int x, int y){
        this.x = x;
        this.y = y;
        pheromone = 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getPheromone(){
        return pheromone;
    }
    public void addPheromone(double pheromone){
        this.pheromone+=pheromone;
    }

    public void setPheromone(double pheromone){
        this.pheromone = pheromone;
    }

    @Override
    public String toString() {
        return "Dot{" +
                "x=" + x +
                ", y=" + y +
                ", pheromone=" + pheromone +
                '}';
    }

    public double getDistance(Dot dot){
        int xDistance = Math.abs((getX() - dot.getX()));
        int yDistance = Math.abs((getY() - dot.getY()));
        return Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
    }
}



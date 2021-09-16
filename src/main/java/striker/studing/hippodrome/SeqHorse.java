package striker.studing.hippodrome;

public class SeqHorse implements Horse {
    private String name;
    private double speed;
    private double distance;

    public SeqHorse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    @Override
    public void move(){
        distance += speed * Math.random();
    }

    @Override
    public void print(){
        for (int i = 0; i < distance; i++) {
            System.out.print('.');
        }
        System.out.println(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void setDistance(double distance) {
        this.distance = distance;
    }
}

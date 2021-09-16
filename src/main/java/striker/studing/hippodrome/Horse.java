package striker.studing.hippodrome;

public interface Horse {
    void move();
    void print();
    String getName();
    double getSpeed();
    double getDistance();
    void setName(String name);
    void setSpeed(double speed);
    void setDistance(double distance);
}

public class Ride {
    public static double MINIMUM_COST_PER_KM;
    public static int COST_PER_TIME;
    public String type;
    public double distance;
    public int time;


    public Ride(double distance, int time, String type) {
        this.distance = distance;
        this.time = time;
        this.type = type;
    }

    public Ride(double distance, int time) {
        this.distance = distance;
        this.time = time;
    }
}

public class InvoiceService {
    private double MINIMUM_COST_PER_KM = 10;
    private int COST_PER_TIME = 1;
    private double MINIMUM_FARE = 5;
    private RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();

    }

    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;
        if (totalFare < MINIMUM_FARE) {
            return MINIMUM_FARE;
        }
        return Math.max(totalFare, MINIMUM_FARE);
    }


    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            checkClass(ride.type);
            totalFare += this.calculateFare(ride.distance, ride.time);
        }

        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId, rides);
    }

    public void checkClass(String type) {
        if (type.equalsIgnoreCase("Normal")) {
            MINIMUM_COST_PER_KM = 10;
            COST_PER_TIME = 1;
            MINIMUM_FARE = 5;
        }
        if (type.equalsIgnoreCase("Premium")) {
            MINIMUM_COST_PER_KM = 15;
            COST_PER_TIME = 2;
            MINIMUM_FARE = 20;
        }

    }


    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRide(userId));
    }
}

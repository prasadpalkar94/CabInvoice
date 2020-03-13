public enum CabRide {
    PREMIUM(10, 1, 5), NORMAL(15, 2, 20);
    public final double farePerKm;
    public final double farePerMinute;
    public final double minimumFare;

    CabRide(double farePerKm, double farePerMinute, double minimumFare) {
        this.farePerKm = farePerKm;
        this.farePerMinute = farePerMinute;
        this.minimumFare = minimumFare;
    }

    public double calcForClass(Ride ride) {
        double totalFare = ride.distance * ride.MINIMUM_COST_PER_KM + ride.time * ride.COST_PER_TIME;
        return totalFare;
    }
}

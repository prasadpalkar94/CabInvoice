import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService = null;

    @Before
    public void setUp() throws Exception {
        invoiceService = new InvoiceService();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double check = invoiceService.calculateFare(distance, time);
        Assert.assertEquals(25.0, check, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double check = invoiceService.calculateFare(distance, time);
        Assert.assertEquals(5, check, 1);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] ride1 = {new Ride(2.0, 5), new Ride(0.1, 1)};
        InvoiceSummary summary = invoiceService.calculateFare(ride1);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);

    }

    @Test
    public void givenUserIdAndRide_ShouldReturnInvoiceSummary() {
        String userId = "a@b.com";
        Ride[] ride1 = {new Ride(2.0, 5), new Ride(0.1, 1)};
        invoiceService.addRides(userId, ride1);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void checkUserIdForNormalOrPremium() {
        String userId = "a@b.com";
        Ride[] ride1 = {new Ride(2.0, 5, "Premium"), new Ride(0.1, 1, "Normal")};

        invoiceService.addRides(userId, ride1);

        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }
}

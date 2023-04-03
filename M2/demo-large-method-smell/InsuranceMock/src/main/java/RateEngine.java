public class RateEngine {
    public void calculateRates(Client client) {
        printCalculatingHeader(client.getFirstName(), client.getLastName());
        locationAdjustment(client.getAddress());
        genderAdjustment(client.getGender());
    }

    private void printCalculatingHeader(String fName, String lName) {
        System.out.printf("Calculating rates for: %s, %s\n", lName, fName);
    }

    private void locationAdjustment(String location) {
        System.out.println("Adjusting rate for location: ");
        System.out.println(location);
    }

    private void genderAdjustment(String gender) {
        System.out.println("Adjusting rate for client gender: " + gender);
    }
}

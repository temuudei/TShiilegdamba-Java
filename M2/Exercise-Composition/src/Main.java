import java.util.Scanner;

public class Main {
    static Scanner console = new Scanner(System.in);
    public static void main(String[] args) {
        Artifact artifact = new Artifact();
        String userInput = "";

        artifact.setName(promptUserForString("Enter the name of the artifact: "));

        artifact.setYearOfDiscovery(promptUserForInt("Enter the year of the discovery: "));

        artifact.setDiscoverer(new Person());
        artifact.getDiscoverer().setFirstName(promptUserForString("Enter the first name of the discoverer: "));

        artifact.getDiscoverer().setLastName(promptUserForString("Enter the last name of the discoverer"));

        userInput = console.nextLine();
        artifact.getDiscoverer().setPrimarySpecialty(promptUserForString("Enter the primary specialty of the discoverer"));

        System.out.println("Artifact: " + artifact.getName());
        userInput = promptUserForString("Is discoverer the curator? (y/n)");
        if (userInput.equalsIgnoreCase("y")) {
            System.out.printf("Discoverer: %s, %s and %s",
                    artifact.getDiscoverer().getFirstName(),
                    artifact.getDiscoverer().getLastName(),
                    artifact.getDiscoverer().getPrimarySpecialty());
        }
        else {
            artifact.setCurator(new Person());
            artifact.getCurator().setFirstName(promptUserForString("Enter the first name of the curator: "));

            artifact.getCurator().setLastName(promptUserForString("Enter the last name of the curator: "));

            artifact.getCurator().setPrimarySpecialty(promptUserForString("Enter the specialty of the curator: "));

            System.out.printf("Curator: %s, %s and %s",
                    artifact.getCurator().getFirstName(),
                    artifact.getCurator().getLastName(),
                    artifact.getCurator().getPrimarySpecialty());
        }
    }

    private static String promptUserForString(String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }

    public static int promptUserForInt(String prompt) {
        boolean isValid = false;
        int result = -1;

        while (!isValid) {
            System.out.println(prompt);
            try {
                result = Integer.parseInt(console.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
        return result;
    }
}
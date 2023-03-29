import java.util.Random;
import java.util.Scanner;

public class StorageLockers {
    static Scanner console = new Scanner(System.in);
    static int[] lockers = new int[5];
    static String[] lockerPins = new String[5];
    static String[] menuOptions = {"Rent a Locker", "Access a Locker", "Release a Locker", "Exit the program"};
    static String[] menuSecondOptions = {"Access a Locker", "Release a Locker", "Exit the program"};
    static Random rand = new Random();
    static final int upperBound = 10000;
    static int randomPin = 0;
    static int lockerIndex = 0;
    static String lockerPinIndivid = "";
    static int lockerNumber = 0;
    static String pinNumb = "";
    static boolean isLockerReleased = false;

    public static void main(String[] args) {
        System.out.println("Welcome to Our Locker System!");
        int menuChoice = 0;

        while(true) {
            // Generating random PIN numbers 0-9999
            randomPin = rand.nextInt(upperBound);
            // If the locker array is not full, then it displays the full menu
            if (lockers[lockers.length - 1] != 5 || isLockerReleased == true) {
                displayChoices(menuOptions);
                menuChoice = promptUserForInt("What would you like to do next?");
                if (menuChoice == 4) {
                    System.out.println("Thanks for coming, goodbye!");
                    break;
                }
                showMenuOptions(menuChoice);
                isLockerReleased = false;
            }
            // If the locker array is full, then it only shows 3 options from the menu
            else {
                System.out.println("Locker is full");
                displayChoices(menuSecondOptions);
                menuChoice = promptUserForInt("What would you like to do next?");
                if (menuChoice == 3) {
                    System.out.println("Thanks for coming, goodbye!");
                    break;
                }
                showMenuOptions(menuChoice + 1);
            }
        }
    }
    // This method shows the options from the menu
    public static void showMenuOptions(int menuChoice) {
        if (menuChoice == 1) {
            fillArrayData();
        } else if (menuChoice == 2) {
            getOptions(menuChoice);
        } else if (menuChoice == 3) {
            getOptions(menuChoice);
        } else {
            System.out.println("Please only choose from the options below:");
        }
    }
    // This method assigns the locker numbers as well as PIN numbers
    public static void fillArrayData() {
        for (int i = 0; i < lockerPins.length; i++) {
            if (lockerPins[i] == null || lockers[i] == 0) {
                lockers[i] = i + 1;
                lockerIndex = lockers[i];
                // It also adds leading zeros to the PIN number
                lockerPins[i] = getLeadingNumbers("" + randomPin);
                lockerPinIndivid = lockerPins[i];
                break;
            }
        }
        System.out.println("Your locker numbers is: " + lockerIndex);
        System.out.println("You PIN number is " + lockerPinIndivid);
    }
    // This method shows the menu options of the array
    public static void displayChoices(String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i + 1 + ": " + choices[i]);
        }
    }
    //This method asks user for a user input and converts it to integer if successful
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
    // This method adds leading 00 to the PIN numbers
    public static String getLeadingNumbers(String randomPin) {
        int randomPinNumb = Integer.parseInt(randomPin);
        String newRandomPin = "";

        if (randomPinNumb < 10) {
            newRandomPin = "000" + randomPin;
        } else if (randomPinNumb >= 10 && randomPinNumb < 100) {
            newRandomPin = "00" + randomPin;
        } else if (randomPinNumb >= 100 && randomPinNumb < 1000) {
            newRandomPin = "0" + randomPin;
        } else {
            newRandomPin = "" + randomPin;
        }
        return newRandomPin;
    }
    // This method asks from a user input
    private static String promptUserForString(String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }

    // This method shows more options when the user enters any of the option from the menu
    public static void getOptions(int menuChoice) {
        boolean isLockerInData = false;
        boolean isValidPin = false;
        boolean isValidLockerNumb = false;
        do {
            lockerNumber = promptUserForInt("Enter your locker number or press 0 to go back");
            if (lockerNumber < 0) {
                System.out.println("Locker number has to be greater than 0.");
            } else if (lockerNumber > 5) {
                System.out.println("Locker number cannot be greater than 5.");
            } else if (lockerNumber == 0) {
                break;
            }
            // If the user entered the correct locker number
            for (int i = 0; i < lockers.length; i++) {
                if (lockers[i] == lockerNumber) {
                    isValidLockerNumb = true;
                    isLockerInData = true;
                }
            }

            if (isValidLockerNumb == true) {
                System.out.println("Correct locker number");
                do {
                    pinNumb = promptUserForString("Enter your pin number");
                    // If the user wants to access to their locker
                    if (menuChoice == 2) {
                        for (int i = 0; i < lockerPins.length; i++) {
                            if (pinNumb.equals(lockerPins[i])) {
                                System.out.println("Correct pin bye!");
                                isValidLockerNumb = false;
                                isValidPin = true;
                                break;
                            }
                            isValidPin = false;
                        }
                        // If the user wants to release their locker
                    } else if (menuChoice == 3) {
                        for (int i = 0; i < lockerPins.length; i++) {
                            if (pinNumb.equals(lockerPins[i])) {
                                String lastInput = promptUserForString("Are you sure? (y/n)");
                                // Locker is released if the user enters y
                                if (lastInput.equalsIgnoreCase("y")) {
                                    System.out.println("Your locker is released, goodbye!");
                                    lockers[lockerNumber - 1] = 0;
                                    isLockerReleased = true;
                                    lockerPins[i] = null;
                                    isValidPin = true;
                                    isValidLockerNumb = false;
                                    break;
                                    // Locker is not released if the user enters n
                                } else if (lastInput.equalsIgnoreCase("n")) {
                                    System.out.println("Locker is not released");
                                    isValidPin = true;
                                }
                            }
                        }
                    }
                    if (isValidPin == false) {
                        System.out.println("Not correct. Try again");
                    }
                } while (isValidPin != true);
            } else if (isLockerInData == false && lockerNumber > 0 && lockerNumber < 6) {
                System.out.println("The locker number you entered is empty. Please rent a locker first.");
            }
        } while (isValidPin == false);
    }
}
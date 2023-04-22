import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {
    Flights flights = new Flights();
    Tickets tickets = new Tickets();
    AdminMenu adminMenu = new AdminMenu();
    PassengersMenu passengersMenu = new PassengersMenu();
    Passengers passengers = new Passengers();
    Menu menu = new Menu();
    Admins admins = new Admins();
    Scanner input = new Scanner(System.in);

    /**
     * Checking the written state of the entries >>
     * @param input flights' information
     * @param command get from user to check is number or letter.
     * @return true if the format was correct otherwise false
     */
    public boolean checkInput(String input, int command) {
        boolean bool;
        char[] newInput = input.toCharArray();

        if (command == 3 || command == 2) {

            if (newInput[0] >= 'a' && newInput[0] <= 'z')
                return false;
            for (int i = 1; i < newInput.length; i++) {
                if (newInput[i] >= 'a' && newInput[i] <= 'z')
                    bool = true;
                else
                    return false;
            }
        } else {
            for (char c : newInput) {
                if (c >= '0' && c <= '9')
                    bool = true;
                else
                    return false;

            }
        }
        return true;
    }

    public String checkData(String data, int command) {
        boolean bool;
        bool = checkInput(data, command);

        while (!bool) {
            System.out.println("============================================================================================");
            System.out.print("<< Please try again >>\t");

            data = input.next();
            bool = checkInput(data, command);
        }
        return data;
    }

    /**
     * check the valid format of date >>
     * @param data the date of flight.
     * @return true if the format was correct otherwise false
     */
    public boolean checkDate(String data) {

        Matcher matcher = Pattern.compile("\\d{4}/\\d{2}/\\d{2}").matcher(data);

        if (matcher.find()) {
            String[] digits = data.split("/");
            int[] convertedDigits = new int[digits.length];

            for (int i = 0; i < digits.length; i++)
                convertedDigits[i] = Integer.parseInt(digits[i]);

            boolean yearCheck = convertedDigits[0] > 1401 && convertedDigits[0] < 1410;
            boolean monthCheck = convertedDigits[1] > 0 && convertedDigits[1] < 13;
            boolean dayCheck = convertedDigits[2] > 0 && convertedDigits[2] < 31;

            if ((convertedDigits[1] > 0 && convertedDigits[1] < 7))
                dayCheck = convertedDigits[2] > 0 && convertedDigits[2] < 32;

            return monthCheck && dayCheck && yearCheck;
        }
        return false;

    }

    /**
     * check the valid format of time >>
     * @param data the time of flight.
     * @return true if the format was correct otherwise false.
     */

    public boolean checkTime(String data) {
        Matcher matcher = Pattern.compile("\\d{2}:\\d{2}").matcher(data);
        if (matcher.find()) {
            String[] digits = data.split(":");
            int[] convertedDigits = new int[digits.length];

            for (int i = 0; i < digits.length; i++)
                convertedDigits[i] = Integer.parseInt(digits[i]);

            boolean hourCheck = convertedDigits[0] >= 0 && convertedDigits[0] < 24;
            boolean minCheck = convertedDigits[1] >= 0 && convertedDigits[1] < 60;

            return hourCheck && minCheck;
        }
        return false;
    }


}

public class Main {
    public static void main(String[] args) {
        System.out.println("\n---------------------------------------------------------------------------------------------------");
       System.out.print("\t\t\t\t\t\t\tWELCOME TO AIRLINE RESERVATION SYSTEM\n");
        System.out.println("---------------------------------------------------------------------------------------------------");
       Database database = new Database();
       database.menu.mainMenu(database);

    }
}
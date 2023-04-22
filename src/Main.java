public class Main {
    /**
     * <h1><span style = "font-family:arial" > Airlines Reservation System </h1>
     * @author <h2><span stule = "font-style: italic"> Mahya Kargar </h2></b></span><hr>
     * @since 21 April 2023
     *
     */
    public static void main(String[] args) {
        System.out.println("\n---------------------------------------------------------------------------------------------------");
       System.out.print("\t\t\t\t\t\t\tWELCOME TO AIRLINE RESERVATION SYSTEM\n");
        System.out.println("---------------------------------------------------------------------------------------------------");
       Database database = new Database();
       database.menu.mainMenu(database);

    }
}
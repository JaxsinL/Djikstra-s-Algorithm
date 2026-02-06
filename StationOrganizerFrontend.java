////////////////////////////////////////////////////////////////////////////////
// --== CS400 Project One File Header ==--
// Name: Shunjie Zhu
// CSL Username: shunjie
// Email: szhu252@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: Worked with my blue team's DW
////////////////////////////////////////////////////////////////////////////////
import java.util.List;
import java.util.Scanner;

/**
 * This class is an implementation of the interface IStationOrganizerFrontend. It contains
 * methods to display the main menu, search for a station's information, find the shortest
 * distance between two stations, add a new station or delete an old station.
 * 
 * @author zhushunjie
 *
 */
public class StationOrganizerFrontend implements IStationOrganizerFrontend {
  private Scanner input; // Scanner object that is declared as private variable
  private ITrainStationBackend backend; // Object that accesses to the backend methods

  /**
   * The constructor that the implementation this interface will provide. It takes the
   * Scanner that will read user input as a parameter as well as the backend of
   * placeholder.
   * 
   * @param userInputScanner
   * @param backend
   */
  public StationOrganizerFrontend(Scanner userInputScanner,
      TrainStationBackendFD backend) {
    this.input = userInputScanner;
    this.backend = backend;

  }

  /**
   * The constructor that the implementation this interface will provide. It takes the
   * Scanner that will read user input as a parameter as well as the backend.
   * 
   * @param userInputScanner
   * @param backend
   */
  public StationOrganizerFrontend(Scanner userInputScanner, TrainStation backend) {
    this.input = userInputScanner;
    this.backend = backend;

  }

  /**
   * This method starts the command loop for the frontend, and will terminate when the
   * user exists the app.
   */
  @Override
  public void runCommandLoop() {
    // Welcome message at top
    System.out.println("Welcome to the shortest train station path finder!\n"
        + "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n");

    // The main loop. This loop will continue to work until the user enters '6' to exit

    boolean run = true;// check if the loop should be run or exited
    while (run) {
      displayMainMenu();

      String userChoice;
      if (input.hasNext()) {
        userChoice = input.next();
      } else {
        break;
      }


      // The command is based on the userChoice, which is picked from integer 1 to 6.
      // 1) Search by station Name
      // 2) Search by the shortest Distance between the two stations your provided
      // 3) Number of Stations
      // 4) Search by station ID
      // 5) Remove Station
      // 6) Exit Application
      if (userChoice.trim().equals("1")) {
        stationSearch();

      } else if (userChoice.trim().equals("2")) {
        shortesePathSearch();

      } else if (userChoice.trim().equals("3")) {
        numOfStation();

      } else if (userChoice.trim().equals("4")) {
        idSearch();

      } else if (userChoice.trim().equals("5")) {
        removeStation();

      } else if (userChoice.trim().equals("6")) {
        System.out.println("Goodbye!");
        run = false;// exit application; don't run loop again
      } else {// when user inputs invalid option
        System.out.println("Invalid option chosen! Please try again\n");
      }



    }

  }



  // to help make it easier to test the functionality of this program,
  // the following helper methods will help support runCommandLoop():

  /**
   * This method prints out the main menu to display.
   */
  @Override
  public void displayMainMenu() { // prints command options to System.out
    System.out.println("You are in the Main Menu: ");
    System.out.println("          1) Search by station Name");
    System.out.println(
        "          2) Search by the shortest Distance between the two stations your provided");
    System.out.println("          3) Number of Stations");
    System.out.println("          4) Search by station ID");
    System.out.println("          5) Remove Station");
    System.out.println("          6) Exit Application\n");


  }

  /**
   * Reads station name from System.in, displays all the information of the station
   */
  @Override
  public void stationSearch() {
    System.out.println("You are in the Search by station Name Menu:");
    System.out.println("          Enter station name to look up: ");

    // take in name to search
    String name = input.next();

    // Check if the station is existed
    if (backend.SearchName(name) != null) {
      IStation station = backend.SearchName(name);

      // print out the information of the existed station
      System.out.println("The " + station.getStationName() + " station is existed, Name: "
          + station.getStationName() + ", ID: " + station.getID() + ", latitude: "
          + station.getLatitude() + ", longitude: " + station.getLongitude() + "\n");

    } else { // if station not found
      System.out.println("The station is not found, try another\n");
      return; // go back to main menu
    }



  }

  /**
   * Reads the two station names from System.in, show the shortest distance between two
   * stations the user inputs
   */
  @Override
  public void shortesePathSearch() {
    System.out.println(
        "You are in the Search by the shortest Distance between the two stations your provided Menu:");

    // Input the source
    System.out.println("         Enter the starting station: ");
    String source = input.next();

    // Input the destination
    System.out.println("         Enter the destination: ");
    String destination = input.next();

    try {
      if (backend.ShortestDistance(source, destination) != -1) {
        System.out.println("The shortest path between the two stations you give is: "
            + backend.ShortestDistance(source, destination) + "km\n");

      } else {
        System.out.println("The path is not found, try another\n");
        return; // go back to main menu
      }
    } catch (NullPointerException n) {
      System.out.println("The path is not found, try another\n");

    }


  }

  /**
   * Out print the total number of the stations we have
   */
  @Override
  public void numOfStation() { // displays the total number of stations we have

    System.out.println("You are in the Search by Number of Stations Menu:\n"
        + "          The total stations we have in the graph: " + backend.NumStation()
        + "\n");
  }

  /**
   * Reads station id from System.in, displays all the information of the station
   */
  @Override
  public void idSearch() {
    System.out.println("You are in the Search by station ID Menu:");
    System.out.println("          Enter station ID to look up: ");

    // take in id to search
    String id = input.next();

    // Check if the station is existed
    if (backend.SearchID(id) != null) {
      IStation station = backend.SearchID(id);

      // print out the information of the existed station
      System.out.println("Name: " + station.getStationName() + ", ID: " + station.getID()
          + ", latitude: " + station.getLatitude() + ", longitude: "
          + station.getLongitude() + "\n");

    } else { // if station not found
      System.out.println("The station is not found, try another\n");
      return; // go back to main menu
    }
  }


  /**
   * Reads the station ID from System.in, removes the station and every path connected it
   */
  @Override
  public void removeStation() {
    System.out.println("You are in the Remove Station Menu:");
    System.out.println("          Enter the ID of a station you want to remove: ");

    // take in id to search
    String id = input.next();

    IStation station = backend.SearchID(id); // save the information of the station first

    // Check if the station is existed
    if (backend.removeByID(id) == true) {

      // print out the information of the existed station
      System.out.println("Name: " + station.getStationName() + ", ID: " + station.getID()
          + ", latitude: " + station.getLatitude() + ", longitude: "
          + station.getLongitude()
          + ". This station has been removed and every path connected to it has been removed.\n");

    } else { // if station not found
      System.out.println("The station is not found, try another\n");
      return; // go back to main menu
    }

  }



}

import java.util.List;

public interface IStationOrganizerFrontend {

  /**
   * The constructor that the implementation this interface will provide. It takes the
   * Scanner that will read user input as a parameter as well as the backend.
   */
  // IStationOrganizerFrontend(Scanner userInputScanner, ITrainStationBackend backend)

  /**
   * This method starts the command loop for the frontend, and will terminate when the
   * user exists the app.
   */
  public void runCommandLoop();

  // to help make it easier to test the functionality of this program,
  // the following helper methods will help support runCommandLoop():

  public void displayMainMenu(); // prints command options to System.out

  public void stationSearch(); // reads station name from System.in, displays all the
                               // information of the station

  public void shortesePathSearch(); // reads the two station names from System.in, show
                                    // the shortest distance between two
                                    // stations the user inputs

  public void numOfStation(); // displays the total number of stations we have

  public void idSearch(); // reads station id from System.in, displays all the
                          // information of the station


  public void removeStation(); // reads the station ID from System.in, removes the station
                               // and every path connected it


}

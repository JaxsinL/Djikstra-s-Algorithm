////////////////////////////////////////////////////////////////////////////////
// --== CS400 Project One File Header ==--
// Name: Shunjie Zhu
// CSL Username: shunjie
// Email: szhu252@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: Worked with my blue team's DW
////////////////////////////////////////////////////////////////////////////////

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is tried to run the main method to station mapper app.
 * 
 * @author zhushunjie
 *
 */
public class StationMapper {

  public static void main(String[] args) throws FileNotFoundException {
    // load the stations from the data file
    StationLoader loaderTest = new StationLoader();
    List<IStation> stationList = new ArrayList<IStation>();
    try {
      stationList = loaderTest.loadStations("TrainNetwork.gv");

    } catch (FileNotFoundException e) {
      System.out.println("file is not found");
    }

    // instantiate the backend
    TrainStation backend = new TrainStation(stationList);
    

    // instantiate the scanner for user input (to be used by the front end)
    Scanner userInputScanner = new Scanner(System.in);

    // instantiate the front end and pass references to the scanner, and backend
    // validator to it
    IStationOrganizerFrontend frontend =
        new StationOrganizerFrontend(userInputScanner, backend);
    // start the input loop of the front end
    frontend.runCommandLoop();
  }

}

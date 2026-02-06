////////////////////////////////////////////////////////////////////////////////
// --== CS400 Project One File Header ==--
// Name: Shunjie Zhu
// CSL Username: shunjie
// Email: szhu252@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: Worked with my blue team's DW
////////////////////////////////////////////////////////////////////////////////

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class check the correctness for StationOrganizerFrontend using j-unit test
 * 
 * @author zhushunjie
 *
 */
public class FrontendDeveloperTests {
  private PrintStream saveSystemOut; // store standard io references to restore after test
  private PrintStream saveSystemErr;
  private InputStream saveSystemIn;
  private ByteArrayOutputStream redirectedOut; // where output is written to during the
                                               // test
  private ByteArrayOutputStream redirectedErr;

  /**
   * sets the values to be tested
   * 
   * @param programInput series of input separated by line
   */
  public void setupInput(String programInput) {
    // backup standard io before redirecting for tests
    saveSystemOut = System.out;
    saveSystemErr = System.err;
    saveSystemIn = System.in;
    // create alternative location to write output, and to read input from
    System.setOut(new PrintStream(redirectedOut = new ByteArrayOutputStream()));
    System.setErr(new PrintStream(redirectedErr = new ByteArrayOutputStream()));
    System.setIn(new ByteArrayInputStream(programInput.getBytes()));
  }

  /**
   * run command loop with values from setup with placeholder
   */
  public static void run() {
    Scanner scnr = new Scanner(System.in);
    TrainStationBackendFD backendFD = new TrainStationBackendFD();

    StationOrganizerFrontend test = new StationOrganizerFrontend(scnr, backendFD);// with
                                                                                  // placeholder

    test.runCommandLoop();
    scnr.close();
  }


  /**
   * Captured text that was printed to System.out and System.err durring test.
   * 
   * @return text printed to system.out and system.err
   */
  public String checkOutput() {
    try {
      return redirectedOut.toString() + redirectedErr.toString();
    } finally {

      // restore standard io to their pre-test states
      System.out.close();
      System.setOut(saveSystemOut);
      System.err.close();
      System.setErr(saveSystemErr);
      System.setIn(saveSystemIn);
    }
  }

  /**
   * tests if main menu's exit works correctly Command: 6) Exit Application command)
   */
  @Test
  public void testExitApplication() {
    setupInput("6\n"); // Set the 6 as the command

    run();
    String output = checkOutput();


    assertTrue(output
        .contains("Welcome to the shortest train station path finder!\n"
            + "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n")
        && output.contains("You are in the Main Menu: ") && output.contains("Goodbye!"));
  }

  /**
   * tests if the numOfStation() print correctly Command: 3) Number of Stations command
   */
  @Test
  public void testNumOfStation() {
    setupInput("3\n"); // Set the 3 as the command
    run();
    String output = checkOutput();


    assertTrue(output.contains("You are in the Search by Number of Stations Menu:")
        && output.contains("The total stations we have in the graph: 2500"));
  }

  /**
   * tests if testStationSearch() command works correctly Command: 1) Search by station
   * Name)
   */
  @Test
  public void testStationSearch() {
    setupInput("1\nNY");// Set the 1 as the command

    run();
    String output = checkOutput();

    assertTrue(output.contains(
        "The NY station is existed, Name: NY, ID: 999, latitude: 18, longitude: 33"));
  }

  /**
   * tests if testIdSearch() command works correctly Command: 4) Search by station ID
   */
  @Test
  public void testIdSearch() {
    setupInput("4\n999"); // Set the 4 as the command

    run();
    String output = checkOutput();


    assertTrue(output
        .contains("You are in the Search by station ID Menu:\n"
            + "          Enter station ID to look up:")
        && output.contains("Name: NY, ID: 999, latitude: 18, longitude: 33"));
  }

  /**
   * Tests if testShortesePathSearch() works correctly Command: 2) Search by the shortest
   * Distance between the two stations your provided
   */
  @Test
  public void testShortesePathSearch() {
    setupInput("2\nNY\nNJ"); // Set the 2 as the command

    run();
    String output = checkOutput();

    assertTrue(output.contains(
        "You are in the Search by the shortest Distance between the two stations your provided Menu:\n")
        && output
            .contains("The shortest path between the two stations you give is: 400km"));

  }

  /**
   * Tests if testremoveStation() works correctly Command: 5) Remove Station
   */
  @Test
  public void testremoveStation() {
    setupInput("5\n999"); // Set the 5 as the command

    run();
    String output = checkOutput();

    assertTrue(output.contains("         Enter the ID of a station you want to remove: ")
        && output.contains(
            "Name: NY, ID: 999, latitude: 18, longitude: 33. This station has been removed"
                + " and every path connected to it has been removed."));

  }

  /**
   * Tests whether the name and ID of stations loaded is accurate
   * 
   */
  @Test
  public void CodeReviewOfDataWranger1() {
    StationLoader loaderTest = new StationLoader();

    List<IStation> stationList = new ArrayList<IStation>();

    try {
      stationList = loaderTest.loadStations("TrainNetwork.gv");

    } catch (FileNotFoundException e) {

    }

    // Test if the name of Station is correct
    assertEquals(stationList.get(0).getStationName(), "Château-Arnoux Mairie");
    assertEquals(stationList.get(5).getStationName(), "Reze");

    // Test if the output of ID is correct
    assertEquals(stationList.get(2).getID(), "7");
    assertEquals(stationList.get(5).getID(), "80");

  }

  /**
   * Tests whether the destinations of the stations loaded is accurate
   */
  @Test
  public void CodeReviewOfDataWranger2() {
    StationLoader loaderTest = new StationLoader();

    List<IStation> stationList = new ArrayList<IStation>();

    try {
      stationList = loaderTest.loadStations("TrainNetwork.gv");

    } catch (FileNotFoundException e) {

    }

    // Test if all destinations from La Crau are correct
    assertEquals(stationList.get(2).getDestinations().toString(),
        "[Aire-sur-l'Adour, Château-Arnoux Mairie , Lunel, Château-Arnoux—St-Auban]");

    // Test if all destinations from Château-Arnoux—St-Auban are correct
    assertEquals(stationList.get(4).getDestinations().toString(),
        "[Château-Arnoux Mairie, Aire-sur-l'Adour, La Crau, Lunel, Reze]");


  }

  /**
   * Tests if the app return the correct number of station, and return true by searching
   * station's name or ID.
   */
  @Test
  public void IntegrationTest1() {
    StationLoader loaderTest1 = new StationLoader();
    List<IStation> stationList = new ArrayList<IStation>();

    try {
      stationList = loaderTest1.loadStations("TrainNetwork.gv");

    } catch (FileNotFoundException e) {
      System.out.println("file is not found");
    }

    // instantiate the backend
    TrainStation backend = new TrainStation(stationList);

    // Test the size of the stations
    assertEquals(backend.NumStation(), 6);

    // Test if the name and ID of stations works correctly
    assertEquals(backend.SearchName("Château-Arnoux Mairie").getStationName(),
        "Château-Arnoux Mairie");

    assertEquals(backend.SearchID("1").getID(), "1");

    assertEquals(backend.SearchName("Aire-sur-l'Adour").getID(), "8");

    assertEquals(backend.SearchID("3").getStationName(), "Château-Arnoux—St-Auban");

    // Test the function of Frontend is work
    setupInput("6\n"); // Set the 6 as the command

    run();
    String output = checkOutput();


    assertTrue(output
        .contains("Welcome to the shortest train station path finder!\n"
            + "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n")
        && output.contains("You are in the Main Menu: ") && output.contains("Goodbye!"));


  }

  /**
   * Tests if the Station app works SearchID and removeByID correctly
   */
  @Test
  public void Integration2() {
    StationLoader loaderTest2 = new StationLoader();
    List<IStation> stationList = new ArrayList<IStation>();


    try {
      stationList = loaderTest2.loadStations("TrainNetwork.gv");

    } catch (FileNotFoundException e) {
      System.out.println("file is not found");
    }

    IStation test1 = stationList.get(0);// original data in stationList
    IStation test2 = new Station("NewYork", "100", "33", "180");

    // instantiate the backend
    TrainStation backend = new TrainStation(stationList);


    // Return false if we have the data
    assertEquals(backend.addStation(test1), false);

    // Return true if we add a new data
    assertEquals(backend.addStation(test2), true);

    assertEquals(backend.ShortestDistance("Reze", "Aire-sur-l'Adour"), 1108);

    // Return true if we remove the data correctly
    assertEquals(backend.removeByID("80"), true);

    // Return false if we remove the data which does not existed
    assertEquals(backend.removeByID("84"), false);

    // Return the shortest distance between source and destination
    assertEquals(backend.ShortestDistance("Lunel", "La Crau"), 1111);

    assertEquals(backend.ShortestDistance("Château-Arnoux—St-Auban", "Aire-sur-l'Adour"),
        1111);

    // Test the function of Frontend work correctly
    setupInput("4\n1"); // Set the 6 as the command

    run();
    String output = checkOutput(); // invalid id, it should not print a station


    assertTrue(output
        .contains("You are in the Search by station ID Menu:\n")
        && output.contains("The station is not found, try another"));


  }



}

////////////////////////////////////////////////////////////////////////////////
// --== CS400 Project One File Header ==--
// Name: Shunjie Zhu
// CSL Username: shunjie
// Email: szhu252@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: Worked with my blue team's DW
////////////////////////////////////////////////////////////////////////////////

/**
 * Frontend's Placeholder class for the implementation of backend.
 * 
 * @author zhushunjie
 *
 */
public class TrainStationBackendFD implements ITrainStationBackend {
  private IStation source;
  private IStation destination;

  /**
   * Minimum functionality needed for testing.
   */
  @Override
  public IStation SearchName(String normalizeName) {

    IStation ny = new Station("NY", "999", "33", "18");

    if (normalizeName.equals("NY")) {
      return ny;
    }
    return null;
  }

  /**
   * Minimum functionality needed for testing.
   */
  @Override
  public int ShortestDistance(String source, String destination) {

    if (source.equals("NY") && destination.equals("NJ")) {
      return 400;
    }
    
    if (source.equals("madison") && destination.equals("NY")) {
      return 1200;
    }

    return -1;
  }

  /**
   * Minimum functionality needed for testing.
   */
  @Override
  public int NumStation() {
    return 2500;
  }


  /**
   * Minimum functionality needed for testing.
   */
  @Override
  public boolean addStation(IStation station) {

    IStation ny = new Station("NY", "999", "33", "18");

    if (station.getID().equals(ny.getID())) {
      System.out.println("The station is existed already!");
      return false;
    }

    return true;
  }


  /**
   * Minimum functionality needed for testing.
   */
  public IStation SearchID(String ID) {

    IStation ny = new Station("NY", "999", "33", "18");
    IStation madison = new Station("madison", "500", "6.89", "10");

    if (ID.equals("999")) {
      return ny;
    }
    if (ID.equals("500")) {
      return madison;
    }
    
    return null;
  }

  /**
   * Minimum functionality needed for testing.
   */
  @Override
  public boolean removeByID(String id) {

    if (id.equals("999")) {
      return true;
    }

    return false;
  }


}

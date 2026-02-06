////////////////////////////////////////////////////////////////////////////////
// --== CS400 Project One File Header ==--
// Name: Shunjie Zhu
// CSL Username: shunjie
// Email: szhu252@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: Worked with my blue team's DW
////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines each Station's data attributes and is implemented by classes that
 * represent a name of the station and their associated data.
 * 
 * @author zhushunjie
 *
 */
public class Station implements IStation {
  private String name;
  private String ID;
  private String Longitude;
  private String Latitude;
  private List<String> destinations;

  /**
   * This is the constructor for the data type Station, which includes four variables:
   * name, ID, Longitude, and Latitude
   * 
   * @param name      name of Station
   * @param ID        ID of Station
   * @param Longitude longitude of Station's location
   * @param Latitude  latitude of Station's location
   */
  public Station(String name, String ID, String Longitude, String Latitude) {
    this.name = name;
    this.ID = ID;
    this.Longitude = Longitude;
    this.Latitude = Latitude;
    this.destinations = new ArrayList<>();
  }

  /**
   * Returns the name of the station.
   * 
   * @return name of the station
   */
  @Override
  public String getStationName() {
    return this.name;
  }

  /**
   * Returns a string that includes the ID as a single string
   * 
   * @return ID of the station
   */
  @Override
  public String getID() {
    return this.ID;
  }

  /**
   * Returns a string that includes the Longitude as a single string
   * 
   * @return Longitude of the station
   */
  @Override
  public String getLongitude() {
    return this.Longitude;
  }

  /**
   * Returns a string that includes the Latitude as a single string
   * 
   * @return Latitude of the station
   */
  @Override
  public String getLatitude() {
    return this.Latitude;
  }


  /**
   * Returns a list of the destinations
   *
   * @return Latitude of the station
   */
  @Override
  public List<String> getDestinations() {

    return destinations;
  }

  /**
   * Method to add destinations to the station
   *
   */
  @Override
  public void addConnection(String to) {
    destinations.add(to);

  }

}

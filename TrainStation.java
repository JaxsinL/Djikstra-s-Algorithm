////////////////////////////////////////////////////////////////////////////////
// --== CS400 Project One File Header ==--
// Name: Shunjie Zhu
// CSL Username: shunjie
// Email: szhu252@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: Help BE to fix his code
////////////////////////////////////////////////////////////////////////////////

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * This is the Backend project for the backend. Shunjie(Frontend) helps to write his code
 * 
 * @author zhushunjie
 *
 */
public class TrainStation implements ITrainStationBackend {
  private GraphADT<IStation, Double> graph; // access to graph
  private StationLoader loader;
  private List<IStation> allStation;


  private IStation findStationByName(List<IStation> allStation, String name)
      throws IllegalArgumentException {
    for (IStation station : allStation) {
      if (station.getStationName().equals(name)) {
        return station;
      }
    }
    throw new IllegalArgumentException();
  }

  /**
   * constructor of BackEnd use to create database by read the txt.file to put the data in
   * that file into all Station
   */
  public TrainStation(List<IStation> allStation) {
    this.allStation = allStation;

    // add all the stations to the backend
    // for (IStation station : allStation) {
    // graph.insertVertex(station);
    // }

    /////////////////////////////////
    graph = new CS400Graph<>();
    for (IStation station : allStation) {
      graph.insertVertex(station);
      for (String destination : station.getDestinations()) {
        try {
          IStation desStation = findStationByName(allStation, destination);
          // graph.insertVertex(desStation);
          double latA = Double.valueOf(station.getLatitude());
          double lonA = Double.valueOf(station.getLongitude());
          double latB = Double.valueOf(desStation.getLatitude());
          double lonB = Double.valueOf(desStation.getLatitude());
          boolean temp = graph.insertEdge(station, desStation,
              getDistanceFromLatLonInKm(latA, lonA, latB, lonB));
        } catch (IllegalArgumentException e) {
        }
      }
    }
  }



  public double getDistanceFromLatLonInKm(double latA, double lonA, double latB,
      double lonB) {
    final double RADIUS = 63710;
    double temp = Math.cos(Math.toRadians(latA)) * Math.cos(Math.toRadians(latB))
        * Math.cos(Math.toRadians((latB) - (latA)))
        + Math.sin(Math.toRadians(latA)) * Math.sin(Math.toRadians(latB));
    return temp * RADIUS * Math.PI / 180;
  }

  @Override
  public int ShortestDistance(String source, String destination) {
    IStation start = null;
    IStation end = null;
    if (allStation == null) {
      return -1;
    }
    for (IStation e : allStation) {
      if (e.getStationName().equals(source)) {
        start = e;
      }
      if (e.getStationName().equals(destination)) {
        end = e;
      }
    }
    double pathCost = graph.getPathCost(start, end);
    return (int)pathCost;
  }

  @Override
  public boolean addStation(IStation station) {

    if (graph.insertVertex(station)) {
      allStation.add(station);
      return true;
    }
    return false;

  }

  @Override
  public int NumStation() {

    return graph.getVertexCount();
  }

  @Override
  public IStation SearchID(String ID) {
    if (ID == null)
      throw new NullPointerException("Invalid input");
    if (allStation == null) {
      return null;
    }
    for (IStation a : allStation) {
      if (a.getID().equals(ID))
        return a;
    }
    return null;
  }


  @Override
  public boolean removeByID(String id) {
    if (id == null)
      throw new NullPointerException("Cannot remove null vertex");
    if (allStation == null)
      return false;
    for (IStation e : allStation) {
      if (e.getID().equals(id)) {
        graph.removeVertex(e);
        allStation.remove(e);
        return true;
      }
    }

    return false;
  }

  @Override
  public IStation SearchName(String normalizeName) {
    if (normalizeName == null)
      throw new NullPointerException("Invalid input");
    for (IStation a : allStation) {
      if (a.getStationName().equals(normalizeName))
        return a;
    }
    return null;
  }

}

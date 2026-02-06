
public interface ITrainStationBackend {
  public IStation SearchName(String normalizeName); // return a value type Station by search its name

  public int ShortestDistance(String source, String destination);// Find the shortest
                                                                 // distance from source
                                                                 // to destination(这两个属于Station里面的name)

  public boolean addStation(IStation station);

  public int NumStation(); // return the total number of station we have from DW
  
  public IStation SearchID(String ID); // return a value type Station by search its ID


  public boolean removeByID(String id);//remove the station by search its ID
}

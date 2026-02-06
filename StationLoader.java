import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StationLoader {


  public List<IStation> loadStations(String filename) throws FileNotFoundException {
    List<IStation> stationList = new LinkedList<IStation>();


    File file = new File(filename);
    Scanner scnr = new Scanner(file);
    // scnr.nextLine();
    String line = "";

    while (scnr.hasNextLine()) {
      String temp = scnr.nextLine();
      if (temp.contains("{"))
        temp = scnr.nextLine();
      if (temp.contains("}"))
        break;

      line += temp + "\n";

    }

    String[] linesSplit = line.split("\n" + "\n" + "\n");
    String[] edgeData;
    String[] nodeData;
    int counter = 0;
    while (true) {
      if (!linesSplit[counter].strip().strip().equals("")) {
        edgeData = linesSplit[counter].strip().split("\n");
        break;
      }
      counter++;
    }
    counter++;
    while (true) {
      if (!linesSplit[counter].strip().strip().equals("")) {
        nodeData = linesSplit[counter].strip().split("\n");
        break;
      }
      counter++;
    }

    for (String s : edgeData)
      s.replace(";", "");
    for (String s : nodeData)
      s.replace(";", "");


    // parse nodes


    for (String s : nodeData) {
      try {
        String[] node = s.split(" \\[");
        String name = node[0].replace("\"", "").strip();
        String[] data = node[1].split(",");
        String ID = data[0].split("=")[1].strip().replace("\"", "");
        String latitude = data[1].split("=")[1].strip().replace("\"", "").replace("]", "")
            .replace(";", "");
        String longitude = data[2].split("=")[1].strip().replace("\"", "")
            .replace("]", "").replace(";", "");
        stationList.add(new Station(name, ID, latitude, longitude));
      } catch (IndexOutOfBoundsException e) {

      }
    }
    // parse edges
    for (String s : edgeData) {
      try {
        String[] edge = s.split("--");
        String from = edge[0].replace("\"", "").strip();
        String to = edge[1].replace("\"", "").strip().replace(";", "");

        for (IStation station : stationList)
          if (station.getStationName().equals(from))
            station.addConnection(to);

        for (IStation station : stationList)
          if (station.getStationName().equals(to))
            station.addConnection(from);


      } catch (IndexOutOfBoundsException e) {
      }
    }



    return stationList;


  }
}

import java.util.List;

/**                                                                                                                                                                            
 * This interface defines getter methods for each station's data attributes and                                                                                                
 * is implemented by classes that represent a name of the station and their                                                                                                    
 * associated data.                                                                                                                                                            
 */
public interface IStation{
        /**                                                                                                                                                                    
         * Returns the name of the station.                                                                                                                                    
         *                                                                                                                                                                     
         * @return name of the station                                                                                                                                         
         */
        String getStationName();

        /**                                                                                                                                                                    
         * Returns a string that includes the ID as a single string                                                                                                         
         *                                                                                                                                                                     
         * @return ID of the station                                                                                                                                                   
         */
        String getID();

        /**                                                                                                                                                                   
         * Returns a string that includes the Longitude as a single string                                                                           
         *                                                                                                                                                                     
         * @return Longitude of the station                                                                                                                                                                
         */
        String getLongitude();

        /**                                                                                                                                                                     
         * Returns a string that includes the Latitude as a single string                                                                                                            
         *                                                                                                                                                                     
         * @return Latitude of the station                                                                                                                                                              
         */
        String getLatitude();
        
        List<String> getDestinations();

        /**                                                                                                                                                                     
         * Method to add destinations to the station                                                                                                            
         *                                                                                                                                                                                                                                                                                                                              
         */

        void addConnection(String to);




}                
        
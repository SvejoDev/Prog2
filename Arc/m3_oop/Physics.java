package m3_oop;

/**
 * Klassen består av ett antal hjälpmetoder för att förenkla 
 * fysikaliska beräkningar.
 *
 * @author Henrik Bygren
 * @version 1.0
 * @since 2017-02-14
 */
public final class Physics {
     	private Physics(){}
     	
     	/**
     	 * Beräknar sträckan vid konstant hastighet
     	 *
     	 * @param  velocity Hastigheten
     	 * @param  time	  Färdtiden
     	 * @return Sträckan
     	 */
     	public static double distance(double velocity, double time){
           return velocity*time;
     	}
     	
     	/**
     	 * Beräknar medelhastigheten.
     	 *
     	 * @param  distance Sträcka som tillryggalagts
     	 * @param  time Tiden för färden
     	 * @return Medelhastigheten
     	 */
     	public static double velocity(double distance, double time){
           return distance/time;
     	}
     	
     	/**
     	 * Beräknar tiden vid rörelse med konstant hastighet
     	 *
     	 * @param distance Sträckan
     	 * @param velocity Medelhastigheten
     	 * @return tiden som färden tagit
     	 */
     	public static double time(double distance, double velocity){
           return distance/velocity;
     	}
     	
     	/**
     	 * Beräknar tiden vid rörelse med konstant hastighet
     	 *
     	 * @param current Strömmen
     	 * @param resistance Resistans
     	 * @return Spänningen
     	 */
     	public static double voltage(double current, double resistance){
           return current * resistance;
     	}
     	
     	/**
     	 * Beräknar tiden vid rörelse med konstant hastighet
     	 *
     	 * @param voltage Spänningen
     	 * @param resistance Resistans
     	 * @return Strömmen
     	 */
     	public static double current(double voltage, double resistance){
           return voltage / resistance;
     	}
     	
     	/**
     	 * Beräknar tiden vid rörelse med konstant hastighet
     	 *
     	 * @param voltage Spänningen
     	 * @param current Strömmen
     	 * @return Resistans
     	 */
     	public static double resistance(double voltage, double current){
           return voltage / current;
     	}
}
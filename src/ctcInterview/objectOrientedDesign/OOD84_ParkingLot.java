package ctcInterview.objectOrientedDesign;

public class OOD84_ParkingLot {
	
	/*
	 * 1. Questions
	 *   - Free/Paid/Both?
	 *   - Do I need to take a ticket?
	 *     + Does it monitor the number of cars and parking time?
	 *   - How many floors?
	 *     + Is each floor different?
	 *   - Reserved spaces? Handicap spaces? what are the special spaces?
	 *   
	 * 2. Objects
	 *   - Gate
	 *     + Ticket
	 *   
	 *   - Monitor (enable human interactions also)
	 *   
	 *   - Parking lot
	 *     + Level
	 * 
	 * 3. Relationships
	 *   - Monitor and Gate: bi direction
	 *     + Gate tells monitor when a car arrive
	 *     + Gate gives ticket and store data in monitor
	 *   
	 *   - Monitor -> parking lot: 
	 *     + Can have many parking lots
	 */

}

class Level {
	
//	return this so we can do chaining
//	Level firstLevel = new Level();
//	firstLevel
//	  .addCompactSpace(10)
//	  .addLargeSpace(5);
	
	public Level addCompactSpace(int numOfSpaces) {
		// add spaces
		return this;
	}
	
	public Level addLargeSpace(int numOfSpaces) {
		// add spaces
		return this;
	}
	
}
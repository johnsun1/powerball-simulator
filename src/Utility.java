import java.util.ArrayList;
import java.util.Random;

public class Utility {
	
	private Integer NUM_DRAWS;
	
	private Integer TICKET_LENGTH;
	
	private Integer prizePool;
	
	private Random rand;
	
	private ArrayList<Integer> numbers;
	private ArrayList<Integer> powerball;
	
	public Utility() {
		TICKET_LENGTH = 5;
		NUM_DRAWS = 0;
		prizePool = 0;
		rand = new Random();
		resetArrayLists();
	}
	
	public Integer getNumDraws() {
		return NUM_DRAWS;
	}
	
	public Integer getPrizePool() {
		return prizePool;
	}
	
	/**
	 * Populate the number pool
	 */
	public void populateNumberPool() {
		for (int i = 1; i < 70; i++) {
			numbers.add(i);
		}
	}
	
	/**
	 * Populate the pool of powerball numbers
	 */
	public void populatePowerball() {
		for (int i = 1; i < 27; i++) {
			powerball.add(i);
		}
	}
	
	/**
	 * Reset number pools
	 */
	public void resetArrayLists() {
		numbers = new ArrayList<Integer>();
		powerball = new ArrayList<Integer>();
		
		populateNumberPool();
		populatePowerball();
	}
	
	/**
	 * Generate a random powerball lottery ticket
	 * @param s the type of ticket being generated; s for state, p for player
	 * @return a random powerball lottery ticket
	 */
	public ArrayList<Integer> generateTicket(String s) {
		Integer randomIndex = 0;
		
		ArrayList<Integer> randomTicket = new ArrayList<Integer>();

		//Add numbers from 1 to 69
		for (int i = 0; i < TICKET_LENGTH; i++) {
			randomIndex = 1+rand.nextInt(numbers.size()-1);
			randomTicket.add(numbers.get(randomIndex));
			numbers.remove(randomIndex); //To prevent duplicate picks
		}
		
		randomIndex = 1+rand.nextInt(25);
		//Add 1 powerball number from 1 to 26
		randomTicket.add(powerball.get(randomIndex));
		
		//Reset number pools and array lists
		resetArrayLists();
		
		//Increase prize pool by $2 (if player) or increase NUM_DRAWS by 1 (if generating winning ticket)
		if (s != "s") {
			prizePool += 2;
		} else {
			NUM_DRAWS += 1;
		}
		
		return randomTicket;
	}
	
	/**
	 * For testing purposes
	 */
	public void printPowerball() {
		for (Integer i : powerball) {
			System.out.println(i);
		}
	}
	
	/**
	 * For testing purposes
	 */
	public void printNumbers() {
		for (Integer i : numbers) {
			System.out.println(i);
		}
	}
	
	/**
	 * Tests if two lottery tickets are equal
	 * @param t1 ticket 1
	 * @param t2 ticket 2
	 * @return true or false
	 */
	public boolean testTicket(ArrayList<Integer> t1, ArrayList<Integer> t2) {
		return t1.equals(t2);
	}
	
	public boolean testTickets(ArrayList<ArrayList<Integer>> ts, ArrayList<Integer> t) {
		Boolean res = false;
		for (ArrayList<Integer> p : ts) {
			res = testTicket(p, t);
		}
		return res;
	}
	
	/**
	 * Generate i powerball tickets
	 * @param i number of powerball tickets to generate
	 * @return arraylist of lottery tickets
	 */
	public ArrayList<ArrayList<Integer>> generateTickets(int i) {
		ArrayList<ArrayList<Integer>> tickets = new ArrayList<ArrayList<Integer>>();
		for (int l = 0; l < i; l++) {
			tickets.add(generateTicket("p"));
		}
		return tickets;
	}
}

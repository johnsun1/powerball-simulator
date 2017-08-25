import java.util.ArrayList;

public class Main {
	private static int NUM_TICKETS = 1000000;
	
	public static void main(String [] args) {
		Utility util = new Utility();
		ArrayList<ArrayList<Integer>> tickets = util.generateTickets(NUM_TICKETS);
		ArrayList<Integer> winningTicket = util.generateTicket("s");
		Boolean matched = false;
		
		//System.out.println(tickets);
		
		//Check for winning tickets in the first run
		matched = util.testTickets(tickets, winningTicket);
		System.out.println("Winning ticket was " + winningTicket);
		System.out.println("The prize pool is now $" + util.getPrizePool() + "\n");
		
		//Continually generate player tickets, generate a winning ticket,
		//check the pool of player tickets for winning tickets
		//repeat if no winning tickets are found
		while (!matched) {
			tickets = util.generateTickets(NUM_TICKETS);
			//System.out.println(tickets);
			winningTicket = util.generateTicket("s");
			matched = util.testTickets(tickets, winningTicket);
			System.out.println("Winning ticket was " + winningTicket);
			System.out.println("The prize pool is now " + util.getPrizePool());
			
			if (!matched) {
				System.out.println("No winners this time. \n");
			}
		}
		
		System.out.println("We have a winner on draw number " + util.getNumDraws() + " with a winning ticket of " + winningTicket.toString());
		System.out.println("The prize is " + util.getPrizePool());
	}
}
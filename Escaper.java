import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Escaper {
    public static void main(String[] args) {
        // Assuming no error in argument number
	System.out.println("\nInput file given: " + args[0]);
	Parking myPark = parseFile(args[0]); // Get parking
	bfs(myPark);			     // Solve parking
    }

    public static Parking parseFile(String file) {
	Scanner in = null;
	try {
	    // Cheking if file is OK to read from
	    in = new Scanner((new FileReader(file)));
	} catch (IOException e) {
	    System.out.println("Wrong input file: " + e.getMessage());
	}
	// \\A in the beginning of the file. Interprets the rest as one big string.
	String text = in.useDelimiter("\\A").next();
	Pattern carPattern = Pattern.compile("\\Q[(\\E[0-4],[0-4]\\Q), (\\E[0-4],[0-4]\\Q)]\\E");
	Matcher matcher = carPattern.matcher(text);

	List<Car> carList = new ArrayList();
	boolean goal = true;
	int carCount = 0;
	while (matcher.find()) {
	    // Goal car first. Should be set up like this in input.txt
	    Car tempCar = extractCar(matcher.group(), goal, carCount);
	    carList.add(tempCar);
	    goal = false;
	    ++carCount;
	}
	Parking myPark = new Parking(carList);
	return myPark;
    }

    public static Car extractCar(String ugly, boolean goal, int carCount) {
	String carId;
	int[] coords = new int[4];
	int index = 0;
	for (int i = 0; i < ugly.length(); i++){
	    char c = ugly.charAt(i);
	    if (Character.isDigit(c)) {
		coords[index] = Character.getNumericValue(c);
		index++;
	    }
	}
	if (carCount == 0) {
	    carId = "GG";
	} else {
	    carId = "c" + carCount;
	}

	Car car = new Car(coords[0], coords[1], coords[2], coords[3], carId, goal);
	return car;
    }

    public static void bfs(Parking park) {
	Queue<Parking> toCheck = new LinkedList();
	List<String[][]> visited = new ArrayList();

	// Check if goal
	visited.add(park.parking);
	toCheck.add(park);

	escape:
	while(toCheck.size() != 0) {
	    Parking treating = toCheck.remove();
	    for (int i = 0; i < treating.moves.size(); i++) {
		Move nextMove = treating.moves.get(i);
		Parking newParking = new Parking(nextMove);
		if (isInList(visited, newParking.parking) == false) {
		    if (checkSolved(newParking.parking)) {
			followPath(newParking, 0);
		    	System.out.println("\nDone.\n");
			break escape;
		    }
		    visited.add(newParking.parking);
		    toCheck.add(newParking);
		}
	    }
	}

    }

    public static boolean isInList(List<String[][]> list, String[][] candidate) {
	// Comparing arrays, since the methods .equals() and
	// .contains() do not work well with arrays
	for (String[][] array : list) {
	    if (Arrays.deepEquals(array, candidate)) {
		return true;
	    }
	}
	return false;
    }

    public static boolean checkSolved(String[][] parking) {
	// Condition for the puzzle to be solved. Can be
	// anything... Testing the string in the exit case is simple
    	return parking[Parking.exitX][Parking.exitY] == null ? false :
	    parking[Parking.exitX][Parking.exitY].equals("GG");
    }

    public static void followPath(Parking solution, int step) {
	// Recursive function that goes all the way up to the starting
	// point and then indicates the moves to do to reach the
	// solution.
	Move movement = solution.comingFrom;
	if (movement != null) {
	    // If not in the initial state, keep going up
	    followPath(movement.predParking, ++step);
	    System.out.print("Étape suivante: ");
	    Car toMove = movement.carList.get(movement.index);
	    String id = toMove.carId;

	    System.out.println("déplacer voiture " + id);
	    System.out.print(movement.predParking.carList.get(movement.index));
	    System.out.print(" -> ");
	    System.out.println(toMove);

	    System.out.println("Le Parking doit se trouver dans cet état:");
	    solution.printGrid();
	} else {
	    // Printing some info at the beginning about the first
	    // state parking
	    System.out.println(solution);
	    System.out.println("Une façon de sortir du Parking en " + step + " mouvements a été trouvée");
	}
    }
}

import java.util.*;


public class Parking {
    // Parking properties
    static int SIZE = 5;
    public static int exitX = 2;
    public static int exitY = 4;
    List<Car> carList = new ArrayList();
    public List<Move> moves = new ArrayList();
    public String[][] parking = new String[SIZE][SIZE];
    public Move comingFrom = null;

    public Parking (List<Car> carList) {
	// Default ctor
	this.carList = carList;
	updateGrid();
    }

    public Parking (Move move) {
	// Overloaded ctor if parking changes state
	this.comingFrom = move;
	this.carList = move.carList;
	Car toChange = carList.get(move.index);
	if (move.axis.equals("y")) {
	    toChange.y1 += move.inc;
	    toChange.y2 += move.inc;
	} else {
	    toChange.x1 += move.inc;
	    toChange.x2 += move.inc;
	}
	updateGrid();
    }

    public void addCar(Car car) {
	// Fill carList. No need if carList is public
	carList.add(car);
    }


    public String toString() {
	// Basic info to begin the program with.
	String rep = "Le parking a une dimension " + SIZE + " fois " + SIZE + "\n";
	rep = rep.concat("Il contient 1 Goal car et " + (carList.size() - 1) + " autres voitures\n");
	for (int i = 0; i < carList.size(); i++) {
	    Car car = carList.get(i);
	    if (i == 0) {
		rep = rep.concat("La voiture Goal se trouve en position: ");
	    } else {
		rep = rep.concat("La voiture " + i + " se trouve en position: ");
	    }
	    rep = rep.concat(car.toString() + "\n");
	}
	return rep;
    }

    public void printGrid() {
	// Sends out the grid.
	printGridPlus();
	for (int i = 0; i < SIZE; i++) {
	    System.out.print("|");
	    for (int j = 0; j < SIZE; j++) {
		if (j != 0) {
		    System.out.print(" ");
		}
		if (parking[i][j] != null) {
		    System.out.print(" " + parking[i][j]);
		} else {
		    System.out.print("   ");
		}
	    }
	    if (i != exitX) {
		System.out.print("|");
	    }
	    if (i != SIZE - 1) {
		printGridNextLine();
	    }
	}
	printGridPlus();
	System.out.println("");
    }

    public void printGridPlus() {
	// Helper method
	System.out.print("\n+");
	for (int i = 0; i < SIZE; i++) {
	    System.out.print("---+");
	}
	System.out.println("");
    }


    public void printGridNextLine() {
	// Helper method
	System.out.print("\n+");
	for (int k = 0; k < SIZE; k++) {
	    System.out.print("   +");
	}
	System.out.println("");
    }

    public List<Car> copyList() {
	// Deep copy of carList
    	List<Car> newList = new ArrayList();
    	for (Car c : carList) {
    	    newList.add(new Car(c.x1, c.y1, c.x2, c.y2, c.carId, c.goal));
    	}
	return newList;
    }

    public void updateGrid() {
	// Redraw every car in the grid and check for possible moves
	// where there are whitespaces
    	for (int i = 0; i < carList.size(); i++) {
    	    Car car = carList.get(i);
    	    parking[car.x1][car.y1] = car.carId;
    	    parking[car.x2][car.y2] = car.carId;
	}
	for (int i = 0; i < carList.size(); i++) {
	    Car car = carList.get(i);
	    if (car.horizontal) {
		if (inboundsAndFree(car.x1, car.y1 - 1)) {
		    moves.add(new Move(copyList(), i, "y", -1, this));
		}
		if (inboundsAndFree(car.x2, car.y2 + 1)) {
		    moves.add(new Move(copyList(), i, "y", 1, this));
		}
	    } else {
		if (inboundsAndFree(car.x1 - 1, car.y1)) {
		    moves.add(new Move(copyList(), i, "x", -1, this));
		}
		if (inboundsAndFree(car.x2 + 1, car.y2)) {
		    moves.add(new Move(copyList(), i, "x", 1, this));
		}
	    }
	}
    }


    public boolean inboundsAndFree(int x, int y) {
	// Helper method to check if a case (x, y) is within the grid
	// and there is nothing on it.
	if ((x >= 0 && x < SIZE) &&
	    (y >= 0 && y < SIZE)) {
	    if (parking[x][y] == null) {
		return true;
	    }
	}
	return false;
    }
}

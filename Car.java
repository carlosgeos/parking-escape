public class Car {
    public String carId;
    public int x1, y1, x2, y2;
    public boolean horizontal = false;
    public boolean goal = false;

    public Car (int x1, int y1, int x2, int y2, String carId, boolean goal) {
	this.goal = goal;
	this.carId = carId;

	this.x1 = x1;
	this.y1 = y1;
	this.x2 = x2;
	this.y2 = y2;
	if (x1 == x2) {
	    this.horizontal = true;
	    if (y2 < y1) {
		this.y2 = y1;
		this.y1 = y2;
	    }
	} else if (x2 < x1) {
	    this.x2 = x1;
	    this.x1 = x2;
	}
    }


    public boolean isGoal() {
	return goal;
    }

    public String toString() {
	// Coords
	return String.format("[(%d,%d), (%d,%d)]", this.x1, this.y1, this.x2, this.y2);
    }
}

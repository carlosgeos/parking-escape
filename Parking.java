public class Parking {
    // Parking properties
    static int SIZE = 5;
    int _carCount;
    public Parking (int carCount) {
	this._carCount = carCount;
	System.out.println("Parking constructed");
	System.out.println("This parking has " + carCount + " cars");

    }

    public static void describeMe() {

    }
}

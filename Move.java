import java.util.*;


public class Move {
    public List<Car> carList;
    public Parking predParking;
    public int index;
    public String axis;
    public int inc;

    public Move (List<Car> carList, int index, String axis, int inc, Parking predParking) {
	this.carList = carList;
	this.index = index;
	this.axis = axis;
	this.inc = inc;
	this.predParking = predParking;
    }
}

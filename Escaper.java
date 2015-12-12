import java.io.*;
import java.util.*;
import java.util.regex.*;



public class Escaper {

    public static void main(String[] args) {
        // Assuming no error in argument number
	System.out.println("Input file given: " + args[0]);
	Parking myPark = parseFile(args[0]);

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

	System.out.println(text);
	Pattern carPattern = Pattern.compile("\\Q[(\\E[0-4],[0-4]\\Q), (\\E[0-4],[0-4]\\Q)]\\E");
	Matcher matcher = carPattern.matcher(text);
	if (matcher.find()) {
	    matcher.find();
	    System.out.println(matcher.group(0)); //prints /{item}/
	} else {
	    System.out.println("Match not found");
	}
	int carCount = matcher.groupCount() - 1;
	//System.out.println(m.regionStart());


	// String word = in.next(carPattern);
	// System.out.println(word);

	// while (in.hasNext()) {
	//     System.out.println(word);
	//     word = in.next(carPattern);

	// }
	Parking myPark = new Parking(carCount);



	return myPark;
    }
}

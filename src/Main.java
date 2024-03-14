import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static class SquareOutOfBoundsException extends Exception {
        public SquareOutOfBoundsException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        boolean fileNotFound = true;
        Scanner in = new Scanner(System.in);
        String fileName = "";
        Scanner fileScan = null;
        int tooBigToBeSquaredCount = 0;

        do {
            System.out.println("Please enter a file from which to get data.");
            fileName = in.nextLine();
            try {
                fileScan = new Scanner(new File(fileName));
                fileNotFound = false;
            } catch (FileNotFoundException e) {
                fileNotFound = true;
                System.out.println("That file could not be found!");
            }
        } while(fileNotFound);

        Scanner lineScan;
        while (fileScan.hasNext()) {
            String line = fileScan.nextLine();
            lineScan = new Scanner(line);
            while (lineScan.hasNext()) {
                try {
                    String item = lineScan.next();
                    short num = Short.parseShort(item);
                    System.out.print(squareNumAndPrint(num) + "\t");
                } catch (NumberFormatException e) {
                    System.out.print(0 + "\t");
                } catch (SquareOutOfBoundsException e) {
                    System.out.print(Short.MAX_VALUE + "\t");
                    tooBigToBeSquaredCount++;
                }
            }
            System.out.println();
        }

        System.out.println("Count of numbers too big to be squared: " + tooBigToBeSquaredCount);
    }

    public static short squareNumAndPrint(short num) throws SquareOutOfBoundsException {
        int square = (int) num * (int) num;
        if (square > Short.MAX_VALUE || square < Short.MIN_VALUE) {
            throw new SquareOutOfBoundsException("Square of " + num + " is out of bounds for short data type.");
        }
        return (short) square;
    }
}
